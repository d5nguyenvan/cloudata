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
package org.cloudata.core.tabletserver.action;

import java.util.ArrayList;
import java.util.List;

import org.cloudata.core.tabletserver.action.TabletSplitAction.TabletSplitFinishAction;


public class BatchUploadAction extends TabletAction {
  static final String ACTION_TYPE = "BatchUploadAction";
  static List<String> checkActionTypes = new ArrayList<String>();
  static {  
    //checkActionTypes.add(TabletStopAction.ACTION_TYPE);
    checkActionTypes.add(TabletSplitAction.ACTION_TYPE);
    checkActionTypes.add(TabletSplitFinishAction.ACTION_TYPE);    
    checkActionTypes.add(TabletLockAction.ACTION_TYPE);
    //checkActionTypes.add(MinorCompactionAction.ACTION_TYPE);
    //checkActionTypes.add(MajorCompactionAction.ACTION_TYPE);
  }
  
  private String actionId;
  
  public BatchUploadAction() {
    actionId = ACTION_TYPE + "_" + System.nanoTime();
  }
  
  public BatchUploadAction(String actionId) {
    this.actionId = actionId;
  }
  
  public boolean isThreadMode() {
    return false;
  }

  public boolean isWaitingMode(ActionChecker actionChecker) {
    return false;
  }

  public void run() {
  }

  @Override
  public String getActionKey() {
    return actionId;
  }

  public int hashCode() {
    return actionId.hashCode();
  }
  
  public List<String> getRunCheckActionTypes() {
    return checkActionTypes;
  }
  
  public String getActionType() {
    return ACTION_TYPE;
  }
}
