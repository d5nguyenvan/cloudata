/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
#ifndef cloudata_TYPES_H
#define cloudata_TYPES_H

#include <Thrift.h>
#include <protocol/TProtocol.h>
#include <transport/TTransport.h>



namespace cloudata {

class ThriftHandle {
 public:

  static const char* ascii_fingerprint; // = "EFB929595D312AC8F305D5A794CFEDA1";
  static const uint8_t binary_fingerprint[16]; // = {0xEF,0xB9,0x29,0x59,0x5D,0x31,0x2A,0xC8,0xF3,0x05,0xD5,0xA7,0x94,0xCF,0xED,0xA1};

  ThriftHandle() : id("") {
  }

  virtual ~ThriftHandle() throw() {}

  std::string id;

  struct __isset {
    __isset() : id(false) {}
    bool id;
  } __isset;

  bool operator == (const ThriftHandle & rhs) const
  {
    if (!(id == rhs.id))
      return false;
    return true;
  }
  bool operator != (const ThriftHandle &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const ThriftHandle & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

class ThriftColumnInfo {
 public:

  static const char* ascii_fingerprint; // = "AB879940BD15B6B25691265F7384B271";
  static const uint8_t binary_fingerprint[16]; // = {0xAB,0x87,0x99,0x40,0xBD,0x15,0xB6,0xB2,0x56,0x91,0x26,0x5F,0x73,0x84,0xB2,0x71};

  ThriftColumnInfo() : columnName(""), numOfVersion(""), columnType("") {
  }

  virtual ~ThriftColumnInfo() throw() {}

  std::string columnName;
  std::string numOfVersion;
  std::string columnType;

  struct __isset {
    __isset() : columnName(false), numOfVersion(false), columnType(false) {}
    bool columnName;
    bool numOfVersion;
    bool columnType;
  } __isset;

  bool operator == (const ThriftColumnInfo & rhs) const
  {
    if (!(columnName == rhs.columnName))
      return false;
    if (!(numOfVersion == rhs.numOfVersion))
      return false;
    if (!(columnType == rhs.columnType))
      return false;
    return true;
  }
  bool operator != (const ThriftColumnInfo &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const ThriftColumnInfo & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

class ThriftTableSchema {
 public:

  static const char* ascii_fingerprint; // = "7C47590FF98310A192E67ECF9E2BE693";
  static const uint8_t binary_fingerprint[16]; // = {0x7C,0x47,0x59,0x0F,0xF9,0x83,0x10,0xA1,0x92,0xE6,0x7E,0xCF,0x9E,0x2B,0xE6,0x93};

  ThriftTableSchema() : tableName(""), description(""), numOfVersion(""), owner("") {
  }

  virtual ~ThriftTableSchema() throw() {}

  std::string tableName;
  std::string description;
  std::string numOfVersion;
  std::string owner;
  std::vector<ThriftColumnInfo>  columns;

  struct __isset {
    __isset() : tableName(false), description(false), numOfVersion(false), owner(false), columns(false) {}
    bool tableName;
    bool description;
    bool numOfVersion;
    bool owner;
    bool columns;
  } __isset;

  bool operator == (const ThriftTableSchema & rhs) const
  {
    if (!(tableName == rhs.tableName))
      return false;
    if (!(description == rhs.description))
      return false;
    if (!(numOfVersion == rhs.numOfVersion))
      return false;
    if (!(owner == rhs.owner))
      return false;
    if (!(columns == rhs.columns))
      return false;
    return true;
  }
  bool operator != (const ThriftTableSchema &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const ThriftTableSchema & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

class ThriftCellValue {
 public:

  static const char* ascii_fingerprint; // = "7F21FB535884165D6350077C7B970E93";
  static const uint8_t binary_fingerprint[16]; // = {0x7F,0x21,0xFB,0x53,0x58,0x84,0x16,0x5D,0x63,0x50,0x07,0x7C,0x7B,0x97,0x0E,0x93};

  ThriftCellValue() : data(""), timestamp(""), deleted(0) {
  }

  virtual ~ThriftCellValue() throw() {}

  std::string data;
  std::string timestamp;
  bool deleted;

  struct __isset {
    __isset() : data(false), timestamp(false), deleted(false) {}
    bool data;
    bool timestamp;
    bool deleted;
  } __isset;

  bool operator == (const ThriftCellValue & rhs) const
  {
    if (!(data == rhs.data))
      return false;
    if (!(timestamp == rhs.timestamp))
      return false;
    if (!(deleted == rhs.deleted))
      return false;
    return true;
  }
  bool operator != (const ThriftCellValue &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const ThriftCellValue & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

class ThriftCell {
 public:

  static const char* ascii_fingerprint; // = "90D6EE2C45FD925C6D61315DFCCE78D9";
  static const uint8_t binary_fingerprint[16]; // = {0x90,0xD6,0xEE,0x2C,0x45,0xFD,0x92,0x5C,0x6D,0x61,0x31,0x5D,0xFC,0xCE,0x78,0xD9};

  ThriftCell() : cellKey("") {
  }

  virtual ~ThriftCell() throw() {}

  std::string cellKey;
  std::vector<ThriftCellValue>  values;

  struct __isset {
    __isset() : cellKey(false), values(false) {}
    bool cellKey;
    bool values;
  } __isset;

  bool operator == (const ThriftCell & rhs) const
  {
    if (!(cellKey == rhs.cellKey))
      return false;
    if (!(values == rhs.values))
      return false;
    return true;
  }
  bool operator != (const ThriftCell &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const ThriftCell & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

class ThriftRow {
 public:

  static const char* ascii_fingerprint; // = "3BB06B7E989815D8A14528A649595B56";
  static const uint8_t binary_fingerprint[16]; // = {0x3B,0xB0,0x6B,0x7E,0x98,0x98,0x15,0xD8,0xA1,0x45,0x28,0xA6,0x49,0x59,0x5B,0x56};

  ThriftRow() : rowKey("") {
  }

  virtual ~ThriftRow() throw() {}

  std::string rowKey;
  std::map<std::string, std::vector<ThriftCell> >  cells;

  struct __isset {
    __isset() : rowKey(false), cells(false) {}
    bool rowKey;
    bool cells;
  } __isset;

  bool operator == (const ThriftRow & rhs) const
  {
    if (!(rowKey == rhs.rowKey))
      return false;
    if (!(cells == rhs.cells))
      return false;
    return true;
  }
  bool operator != (const ThriftRow &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const ThriftRow & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

class ThriftIOException : public ::apache::thrift::TException {
 public:

  static const char* ascii_fingerprint; // = "EFB929595D312AC8F305D5A794CFEDA1";
  static const uint8_t binary_fingerprint[16]; // = {0xEF,0xB9,0x29,0x59,0x5D,0x31,0x2A,0xC8,0xF3,0x05,0xD5,0xA7,0x94,0xCF,0xED,0xA1};

  ThriftIOException() : message("") {
  }

  virtual ~ThriftIOException() throw() {}

  std::string message;

  struct __isset {
    __isset() : message(false) {}
    bool message;
  } __isset;

  bool operator == (const ThriftIOException & rhs) const
  {
    if (!(message == rhs.message))
      return false;
    return true;
  }
  bool operator != (const ThriftIOException &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const ThriftIOException & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

} // namespace

#endif
