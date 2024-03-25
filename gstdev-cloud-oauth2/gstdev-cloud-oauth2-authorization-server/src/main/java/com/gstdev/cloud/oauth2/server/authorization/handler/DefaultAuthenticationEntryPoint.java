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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@Slf4j
public class DefaultAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    log.info("[GstDev Cloud] |- DefaultAuthenticationEntryPoint for [{}]", request.getRequestURI());

    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(authException.getMessage());
    authException.printStackTrace();
    response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
    WebUtils.renderJson(response, errorResponse);
  }
}
