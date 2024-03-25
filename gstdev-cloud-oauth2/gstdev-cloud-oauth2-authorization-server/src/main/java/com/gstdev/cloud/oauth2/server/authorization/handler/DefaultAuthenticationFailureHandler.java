// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.server.authorization.handler;

import com.gstdev.cloud.commons.domain.ErrorResponse;
import com.gstdev.cloud.commons.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

/**
 * 认证失败处理器
 *
 */
@Slf4j
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {

  /**
   * MappingJackson2HttpMessageConverter 是 Spring 框架提供的一个 HTTP 消息转换器，用于将 HTTP 请求和响应的 JSON 数据与 Java 对象之间进行转换
   */
  private final HttpMessageConverter<Object> accessTokenHttpResponseConverter = new MappingJackson2HttpMessageConverter();

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    log.info("[GstDev Cloud] |- DefaultAuthenticationFailureHandler for [{}]", request.getRequestURI());
    exception.printStackTrace();
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(exception.getMessage());

    response.setStatus(HttpStatus.BAD_REQUEST.value());
    WebUtils.renderJson(response, errorResponse);

//    OAuth2Error error = ((OAuth2AuthenticationException) exception).getError();
//    ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
//    Result result = Result.failed(error.getErrorCode());
//    accessTokenHttpResponseConverter.write(result, null, httpResponse);
  }
}
