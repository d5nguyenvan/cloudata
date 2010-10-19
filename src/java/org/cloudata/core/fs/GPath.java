/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudata.core.fs;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * 파일 path에 대한 URI
 * @author babokim
 *
 */
public class GPath implements Comparable {

  /** The directory separator, a slash. */
  public static final String SEPARATOR = "/";
  public static final char SEPARATOR_CHAR = '/';
  
  static final boolean WINDOWS
    = System.getProperty("os.name").startsWith("Windows");

  private URI uri;                                // a hierarchical uri

  /** Resolve a child path against a parent path. */
  public GPath(String parent, String child) {
    this(new GPath(parent), new GPath(child));
  }

  /** Resolve a child path against a parent path. */
  public GPath(GPath parent, String child) {
    this(parent, new GPath(child));
  }

  /** Resolve a child path against a parent path. */
  public GPath(String parent, GPath child) {
    this(new GPath(parent), child);
  }

  /** Resolve a child path against a parent path. */
  public GPath(GPath parent, GPath child) {
    // Add a slash to parent's path so resolution is compatible with URI's
    URI parentUri = parent.uri;
    String parentPath = parentUri.getPath();
    if (!(parentPath.equals("/") || parentPath.equals("")))
      try {
        parentUri = new URI(parentUri.getScheme(), parentUri.getAuthority(),
                            parentUri.getPath()+"/", null, null);
      } catch (URISyntaxException e) {
        throw new IllegalArgumentException(e);
      }
    URI resolved = parentUri.resolve(child.uri);
    initialize(resolved.getScheme(), resolved.getAuthority(),
               normalizePath(resolved.getPath()));
  }

  /** Construct a path from a String.  Path strings are URIs, but with
   * unescaped elements and some additional normalization. */
  public GPath(String pathString) {
    // We can't use 'new URI(String)' directly, since it assumes things are
    // escaped, which we don't require of Paths. 
    
    // add a slash in front of paths with Windows drive letters
    if (hasWindowsDrive(pathString, false))
      pathString = "/"+pathString;

    // parse uri components
    String scheme = null;
    String authority = null;

    int start = 0;

    // parse uri scheme, if any
    int colon = pathString.indexOf(':');
    int slash = pathString.indexOf('/');
    if ((colon != -1) &&
        ((slash == -1) || (colon < slash))) {     // has a scheme
      scheme = pathString.substring(0, colon);
      start = colon+1;
    }

    // parse uri authority, if any
    if (pathString.startsWith("//", start) &&
        (pathString.length()-start > 2)) {       // has authority
      int nextSlash = pathString.indexOf('/', start+2);
      int authEnd = nextSlash > 0 ? nextSlash : pathString.length();
      authority = pathString.substring(start+2, authEnd);
      start = authEnd;
    }

    // uri path is the rest of the string -- query & fragment not supported
    String path = pathString.substring(start, pathString.length());

    initialize(scheme, authority, path);
  }
 
  /** Construct a Path from components. */
  public GPath(String scheme, String authority, String path) {
    initialize(scheme, authority, path);
  }

  private void initialize(String scheme, String authority, String path) {
    try {
      this.uri = new URI(scheme, authority, normalizePath(path), null, null)
        .normalize();
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }
  }

  private String normalizePath(String path) {
    // remove double slashes & backslashes
    path = path.replace("//", "/");
    path = path.replace("\\", "/");
    
    // trim trailing slash from non-root path (ignoring windows drive)
    int minLength = hasWindowsDrive(path, true) ? 4 : 1;
    if (path.length() > minLength && path.endsWith("/")) {
      path = path.substring(0, path.length()-1);
    }
    
    return path;
  }

  private boolean hasWindowsDrive(String path, boolean slashed) {
    if (!WINDOWS) return false;
    int start = slashed ? 1 : 0;
    return
      path.length() >= start+2 &&
      (slashed ? path.charAt(0) == '/' : true) &&
      path.charAt(start+1) == ':' &&
      ((path.charAt(start) >= 'A' && path.charAt(start) <= 'Z') ||
       (path.charAt(start) >= 'a' && path.charAt(start) <= 'z'));
  }


  /** Convert this to a URI. */
  public URI toUri() { return uri; }

  /** True if the directory of this path is absolute. */
  public boolean isAbsolute() {
    int start = hasWindowsDrive(uri.getPath(), true) ? 3 : 0;
    return uri.getPath().startsWith(SEPARATOR, start);
  }

  /** Returns the final component of this path.*/
  public String getName() {
    String path = uri.getPath();
    int slash = path.lastIndexOf(SEPARATOR);
    return path.substring(slash+1);
  }

  /** Returns the parent of a path or null if at root. */
  public GPath getParent() {
    String path = uri.getPath();
    int lastSlash = path.lastIndexOf('/');
    int start = hasWindowsDrive(path, true) ? 3 : 0;
    if ((path.length() == start) ||               // empty path
        (lastSlash == start && path.length() == start+1)) { // at root
      return null;
    }
    String parent;
    if (lastSlash==-1) {
      parent = "";
    } else {
      int end = hasWindowsDrive(path, true) ? 3 : 0;
      parent = path.substring(0, lastSlash==end?end+1:lastSlash);
    }
    return new GPath(uri.getScheme(), uri.getAuthority(), parent);
  }

  /** Adds a suffix to the final name in the path.*/
  public GPath suffix(String suffix) {
    return new GPath(getParent(), getName()+suffix);
  }

  public String toString() {
    // we can't use uri.toString(), which escapes everything, because we want
    // illegal characters unescaped in the string, for glob processing, etc.
    StringBuffer buffer = new StringBuffer();
    if (uri.getScheme() != null) {
      buffer.append(uri.getScheme());
      buffer.append(":");
    }
    if (uri.getAuthority() != null) {
      buffer.append("//");
      buffer.append(uri.getAuthority());
    }
    if (uri.getPath() != null) {
      String path = uri.getPath();
      if (path.indexOf('/')==0 &&
          hasWindowsDrive(path, true) &&          // has windows drive
          uri.getScheme() == null &&              // but no scheme
          uri.getAuthority() == null)             // or authority
        path = path.substring(1);                 // remove slash before drive
      buffer.append(path);
    }
    return buffer.toString();
  }

  public boolean equals(Object o) {
    if (!(o instanceof GPath)) {
      return false;
    }
    GPath that = (GPath)o;
    return this.uri.equals(that.uri);
  }

  public int hashCode() {
    return uri.hashCode();
  }

  public int compareTo(Object o) {
    GPath that = (GPath)o;
    return this.uri.compareTo(that.uri);
  }
  
  /** Return the number of elements in this path. */
  public int depth() {
    String path = uri.getPath();
    int depth = 0;
    int slash = path.length()==1 && path.charAt(0)=='/' ? -1 : 0;
    while (slash != -1) {
      depth++;
      slash = path.indexOf(SEPARATOR, slash+1);
    }
    return depth;
  }
  
}
