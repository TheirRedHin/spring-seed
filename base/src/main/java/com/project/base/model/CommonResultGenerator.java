package com.project.base.model;

import com.project.base.enumerate.ResponseEnums;

public class CommonResultGenerator {

  public static <T> CommonResult<T> SUCCESS(T data) {
    return new CommonResult<T>()
        .setState(true)
        .setCode(ResponseEnums.SUCCESS.getCode())
        .setMessage(ResponseEnums.SUCCESS.getMessage())
        .setData(data);
  }

  public static <T> CommonResult<T> FAIL(ResponseEnums responseEnums, T data) {
    return new CommonResult<T>()
        .setState(false)
        .setCode(responseEnums.getCode())
        .setMessage(responseEnums.getMessage())
        .setData(data);
  }

  public static <T> CommonResult<T> getCommonResult(Boolean state, String code, String message,
      T data) {
    return new CommonResult<T>()
        .setState(state)
        .setCode(code)
        .setMessage(message)
        .setData(data);
  }

}
