//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev <support@gstdev.com>
//// Copyright (c) 2020-2025 gstdev.com
////
//// ====================================================
//package com.gstdev.cloud.oauth2.core.response;
//
//import cn.herodotus.engine.assistant.definition.domain.Result;
//import cn.herodotus.engine.oauth2.core.exception.SecurityGlobalExceptionHandler;
//import cn.herodotus.engine.oauth2.core.utils.WebUtils;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//import java.io.IOException;
//
///**
// * <p>Description: 自定义未认证处理 </p>
// *
// * @author : gengwei.zheng
// * @date : 2022/3/8 8:55
// */
//public class HerodotusAuthenticationEntryPoint implements AuthenticationEntryPoint {
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        Result<String> result = SecurityGlobalExceptionHandler.resolveSecurityException(authException, request.getRequestURI());
//        response.setStatus(result.getStatus());
//        WebUtils.renderJson(response, result);
//    }
//}
