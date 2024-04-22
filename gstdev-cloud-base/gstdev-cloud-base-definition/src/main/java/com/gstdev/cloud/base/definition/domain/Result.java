// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.base.definition.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.base.definition.constants.DefaultConstants;
import com.gstdev.cloud.base.definition.constants.ErrorCodes;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

@Schema(title = "统一响应返回实体", description = "所有Rest接口统一返回的实体定义", example = "new Result<T>().ok().message(\"XXX\")")
public class Result<T> implements Serializable {

  @Schema(title = "响应时间戳", pattern = DefaultConstants.DATE_TIME_FORMAT)
  @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT)
  private final Date timestamp = new Date();
  @Schema(title = "校验错误信息")
  private final Error error = new Error();
  private Boolean success;
  @Schema(title = "自定义响应编码")
  private int code = 0;
  @Schema(title = "响应返回信息")
  private String message;
  @Schema(title = "请求路径")
  private String path;
  @Schema(title = "响应返回数据")
  private T data;
  @Schema(title = "http状态码")
  private int status;
  @Schema(title = "链路追踪TraceId")
  private String traceId;


  public Result() {
    super();
  }

  private static <T> Result<T> create(Boolean success, String message, String detail, int code, int status, T data, StackTraceElement[] stackTrace) {
    Result<T> result = new Result<>();
    if (StringUtils.isNotBlank(message)) {
      result.message(message);
    }

    if (StringUtils.isNotBlank(detail)) {
      result.detail(detail);
    }

    result.code(code);
    result.setSuccess(success);
    result.status(status);

    if (ObjectUtils.isNotEmpty(data)) {
      result.data(data);
    }

    if (ArrayUtils.isNotEmpty(stackTrace)) {
      result.stackTrace(stackTrace);
    }

    return result;
  }

  public static <T> Result<T> success(String message, int code, int status, T data) {
    return create(Boolean.TRUE, message, null, code, status, data, null);
  }

  public static <T> Result<T> success(String message, int code, T data) {
    return success(message, code, HttpStatus.OK.value(), data);
  }

  public static <T> Result<T> success(String message, T data) {
    return success(message, ErrorCodes.OK.getSequence(), data);
  }

  public static <T> Result<T> success(T data) {
    return success(null, data);
  }

  public static <T> Result<T> success(String message) {
    return success(message, null);
  }

  public static <T> Result<T> success() {
    return success("success!");
  }

  public static <T> Result<T> content(T data) {
    return success("success!", data);
  }

  public static <T> Result<T> failure(String message, String detail, int code, int status, T data, StackTraceElement[] stackTrace) {
    return create(Boolean.FALSE, message, detail, code, status, data, stackTrace);
  }

  public static <T> Result<T> failure(String message, String detail, int code, int status, T data) {
    return failure(message, detail, code, status, data, null);
  }

  public static <T> Result<T> failure(String message, int code, int status, T data) {
    return failure(message, message, code, status, data);
  }

  public static <T> Result<T> failure(String message, String detail, int code, T data) {
    return failure(message, detail, code, HttpStatus.INTERNAL_SERVER_ERROR.value(), data);
  }

  public static <T> Result<T> failure(String message, int code, T data) {
    return failure(message, message, code, data);
  }

  public static <T> Result<T> failure(Feedback feedback) {
    return failure(feedback, null);
  }

  public static <T> Result<T> failure(Feedback feedback, T data) {
    Feedback result = ObjectUtils.isNotEmpty(feedback) ? feedback : ErrorCodes.DISCOVERED_UNRECORDED_ERROR_EXCEPTION;
    Integer code = ErrorCodeMapper.get(result);
    return failure(feedback.getMessage(), code, feedback.getStatus(), data);
  }

  public static <T> Result<T> failure(String message, T data) {
    return failure(message, ErrorCodes.INTERNAL_SERVER_ERROR.getSequence(), data);
  }

  public static <T> Result<T> failure(T data) {
    return failure("", data);
  }

  public static <T> Result<T> failure(String message) {
    return failure(message, null);
  }

  public static <T> Result<T> failure() {
    return failure("操作失败！");
  }

  public static <T> Result<T> empty(String message, int code, int status) {
    return create(null, message, null, code, status, null, null);
  }

  public static <T> Result<T> empty(String message, int code) {
    return empty(message, code, ErrorCodes.NO_CONTENT.getStatus());
  }

  public static <T> Result<T> empty(Feedback feedback) {
    int code = ErrorCodeMapper.get(feedback);
    return empty(feedback.getMessage(), code, feedback.getStatus());
  }

  public static <T> Result<T> empty(String message) {
    return empty(message, ErrorCodes.NO_CONTENT.getSequence());
  }

  public static <T> Result<T> empty() {
    return empty("No related content found!");
  }

  public Result<T> code(int code) {
    this.code = code;
    return this;
  }

  public Result<T> message(String message) {
    this.message = message;
    return this;
  }

  public Result<T> data(T data) {
    this.data = data;
    return this;
  }

  public Result<T> path(String path) {
    this.path = path;
    return this;
  }

  public Result<T> type(Feedback feedback) {
    this.code = ErrorCodeMapper.get(feedback);
    this.message = feedback.getMessage();
    this.status = feedback.getStatus();
    return this;
  }

  public Result<T> status(int httpStatus) {
    this.status = httpStatus;
    return this;
  }

  public Result<T> traceId(String traceId) {
    this.traceId = traceId;
    return this;
  }

  public Result<T> stackTrace(StackTraceElement[] stackTrace) {
    this.error.setStackTrace(stackTrace);
    return this;
  }

  public Result<T> detail(String detail) {
    this.error.setResource(detail);
    return this;
  }

  public Result<T> validation(String message, String code, String field) {
    this.error.setMessage(message);
    this.error.setCode(code);
    this.error.setField(field);
    return this;
  }


//================================  getset ========================================

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public Error getError() {
    return error;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
