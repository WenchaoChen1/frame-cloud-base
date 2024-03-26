// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.commons.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * <p>Description: 错误详情 </p>
 *
 * @author : cc
 * @date : 2021/8/18 15:48
 */
@Schema(title = "响应错误详情", description = "为兼容Validation而增加的Validation错误信息实体")
public class Error implements Serializable {
  @Schema(title = "Exception完整信息", type = "string")
  private String resource;

  @Schema(title = "额外的错误信息，目前主要是Validation的Message")
  private String message;

  @Schema(title = "额外的错误代码，目前主要是Validation的Code")
  private String code;

  @Schema(title = "额外的错误字段，目前主要是Validation的Field")
  private String field;

  @Schema(title = "错误堆栈信息")
  private StackTraceElement[] stackTrace;


  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
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

  public StackTraceElement[] getStackTrace() {
    return stackTrace;
  }

  public void setStackTrace(StackTraceElement[] stackTrace) {
    this.stackTrace = stackTrace;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
