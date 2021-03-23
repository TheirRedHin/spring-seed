package com.project.base.handler;

import com.project.base.enumerate.ResponseEnums;
import com.project.base.exception.BaseException;
import com.project.base.model.CommonResultGenerator;
import com.project.base.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 全局异常处理
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

  private static final String LOG_EXCEPTION_FORMAT = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";
  private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /**
   * 业务异常
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(BaseException.class)
  public String runtimeExceptionHandler(BaseException ex) {
    return resultFormat(ex);
  }

  /**
   * 运行时异常
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(RuntimeException.class)
  public String runtimeExceptionHandler(RuntimeException ex) {
    return resultFormat(ResponseEnums.RUNTIME_EXCEPTION, ex);
  }

  /**
   * 空指针异常
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(NullPointerException.class)
  public String nullPointerExceptionHandler(NullPointerException ex) {
    System.err.println("NullPointerException:");
    return resultFormat(ResponseEnums.NULL_POINTER_EXCEPTION, ex);
  }

  /**
   * 类型转换异常
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(ClassCastException.class)
  public String classCastExceptionHandler(ClassCastException ex) {
    return resultFormat(ResponseEnums.CLASS_CAST_EXCEPTION, ex);
  }

  /**
   * IO异常
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(IOException.class)
  public String ioExceptionHandler(IOException ex) {
    return resultFormat(ResponseEnums.IO_EXCEPTION, ex);
  }

  /**
   * 未知方法异常
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(NoSuchMethodException.class)
  public String noSuchMethodExceptionHandler(NoSuchMethodException ex) {
    return resultFormat(ResponseEnums.NO_SUCH_METHOD_EXCEPTION, ex);
  }

  /**
   * 数组越界异常
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(IndexOutOfBoundsException.class)
  public String indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
    return resultFormat(ResponseEnums.INDEX_OUT_OF_BOUNDS_EXCEPTION, ex);
  }

  /**
   * 400错误
   *
   * @param ex
   * @return
   */
  @ExceptionHandler({HttpMessageNotReadableException.class})
  public String requestNotReadable(HttpMessageNotReadableException ex) {
    System.out.println("400..requestNotReadable");
    return resultFormat(ResponseEnums.HTTP_MESSAGE_NOT_READABLE_EXCEPTION, ex);
  }

  /**
   * 400错误
   *
   * @param ex
   * @return
   */
  @ExceptionHandler({TypeMismatchException.class})
  public String requestTypeMismatch(TypeMismatchException ex) {
    System.out.println("400..TypeMismatchException");
    return resultFormat(ResponseEnums.TYPE_MISMATCH_EXCEPTION, ex);
  }

  /**
   * 400错误
   *
   * @param ex
   * @return
   */
  @ExceptionHandler({MissingServletRequestParameterException.class})
  public String requestMissingServletRequest(MissingServletRequestParameterException ex) {
    System.out.println("400..MissingServletRequest");
    return resultFormat(ResponseEnums.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION, ex);
  }

  /**
   * 405错误
   *
   * @param ex
   * @return
   */
  @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
  public String request405(HttpRequestMethodNotSupportedException ex) {
    return resultFormat(ResponseEnums.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION, ex);
  }

  /**
   * 406错误
   *
   * @param ex
   * @return
   */
  @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
  public String request406(HttpMediaTypeNotAcceptableException ex) {
    System.out.println("406...");
    return resultFormat(ResponseEnums.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE_EXCEPTION, ex);
  }

  /**
   * 500错误
   *
   * @param ex
   * @return
   */
  @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
  public String server500(RuntimeException ex) {
    System.out.println("500...");
    return resultFormat(ResponseEnums.HTTP_MESSAGE_NOT_WRITABLE_EXCEPTION, ex);
  }

  /**
   * 栈溢出
   *
   * @param ex
   * @return
   */
  @ExceptionHandler({StackOverflowError.class})
  public String requestStackOverflow(StackOverflowError ex) {
    return resultFormat(ResponseEnums.STACK_OVERFLOW_ERROR, ex);
  }

  /**
   * 除数不能为0
   *
   * @param ex
   * @return
   */
  @ExceptionHandler({ArithmeticException.class})
  public String arithmeticException(ArithmeticException ex) {
    return resultFormat(ResponseEnums.ARITHMETIC_EXCEPTION, ex);
  }


  /**
   * 其他错误
   *
   * @param ex
   * @return
   */
  @ExceptionHandler({Exception.class})
  public String exception(Exception ex) {
    return resultFormat(ResponseEnums.EXCEPTION, ex);
  }

  /**
   * 代码异常处理
   *
   * @param responseEnums
   * @param ex
   * @param <T>
   * @return
   */
  private <T extends Throwable> String resultFormat(ResponseEnums responseEnums, T ex) {
    String errorJson = JsonUtil.toJsonString(CommonResultGenerator.fail(responseEnums, ""));
    log.error(errorJson);
    ex.printStackTrace();
    return errorJson;
  }

  /**
   * 业务异常处理
   *
   * @param ex
   * @param <T>
   * @return
   */
  private <T extends Throwable> String resultFormat(BaseException ex) {
    String errorJson = JsonUtil.toJsonString(
            CommonResultGenerator.getCommonResult(false, ex.getCode(), ex.getMessage(), ""));
    log.error(errorJson);
    ex.printStackTrace();
    return errorJson;
  }

}
