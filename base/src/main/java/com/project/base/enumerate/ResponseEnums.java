package com.project.base.enumerate;

public enum ResponseEnums {


  // 运行时异常
  RUNTIME_EXCEPTION("001", "运行时异常"),
  // 空指针异常
  NULL_POINTER_EXCEPTION("002", "空指针异常"),
  CLASS_CAST_EXCEPTION("003", "类型转换异常"),
  IO_EXCEPTION("004", "IO异常"),
  NO_SUCH_METHOD_EXCEPTION("005", "未知方法异常"),
  INDEX_OUT_OF_BOUNDS_EXCEPTION("006", "数组越界异常"),
  HTTP_MESSAGE_NOT_READABLE_EXCEPTION("007", "400错误"),
  TYPE_MISMATCH_EXCEPTION("008", "400错误"),
  MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION("009", "400错误"),
  HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("010", "405错误"),
  HTTP_MEDIA_TYPE_NOT_ACCEPTABLE_EXCEPTION("011", "406错误"),
  HTTP_MESSAGE_NOT_WRITABLE_EXCEPTION("012", "500错误"),
  STACK_OVERFLOW_ERROR("013", "栈溢出"),
  ARITHMETIC_EXCEPTION("014", "除数不能为0"),
  EXCEPTION("015", "其他错误"),
  SUCCESS("200", "请求成功"),
  FAIL("400", "失败"),
  UNAUTHORIZED("401", "未认证"),
  NOT_FOUND("404", "接口不存在"),
  INTERNAL_SERVER_ERROR("500", "服务器内部错误");


  private String code;
  private String message;

  private ResponseEnums(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
