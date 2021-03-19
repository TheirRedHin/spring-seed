package com.project.base.model;

import com.project.base.util.JsonUtil;

/**
 * 统一API响应结果封装
 */
public class CommonResult<T> {

  private Boolean state;
  private String code;
  private String message;
  private T data;

  public CommonResult(Boolean state, String code, String message, T data) {
    this.state = state;
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public CommonResult() {
  }

  public Boolean getState() {
    return state;
  }

  public CommonResult<T> setState(Boolean state) {
    this.state = state;
    return this;
  }

  public String getCode() {
    return code;
  }

  public CommonResult<T> setCode(String code) {
    this.code = code;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public CommonResult<T> setMessage(String message) {
    this.message = message;
    return this;
  }

  public T getData() {
    return data;
  }

  public CommonResult<T> setData(T data) {
    this.data = data;
    return this;
  }

  @Override
  public String toString() {
    return JsonUtil.toJsonString(this);
  }
}
