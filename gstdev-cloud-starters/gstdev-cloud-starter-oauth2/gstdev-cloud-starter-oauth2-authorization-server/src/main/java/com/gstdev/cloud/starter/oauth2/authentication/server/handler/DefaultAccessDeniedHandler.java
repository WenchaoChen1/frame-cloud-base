//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev Cloud <support@gstdev.com>
//// Copyright (c) 2022-2025 gstdev.com
////
//// ====================================================
//
//package com.gstdev.cloud.starter.oauth2.authentication.server.handler;
//
//import com.gstdev.cloud.base.domain.ErrorResponse;
//import com.gstdev.cloud.oauth2.core.utils.WebUtils;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//
//import java.io.IOException;
//
//@Slf4j
//public class DefaultAccessDeniedHandler implements AccessDeniedHandler {
//
//  @Override
//  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//    log.info("[GstDev Cloud] |- DefaultAccessDeniedHandler for [{}]", request.getRequestURI());
//
//    ErrorResponse errorResponse = new ErrorResponse();
//    errorResponse.setMessage(accessDeniedException.getMessage());
//
//    response.setStatus(HttpStatus.FORBIDDEN.value());
//    WebUtils.renderJson(response, errorResponse);
//  }
//}
