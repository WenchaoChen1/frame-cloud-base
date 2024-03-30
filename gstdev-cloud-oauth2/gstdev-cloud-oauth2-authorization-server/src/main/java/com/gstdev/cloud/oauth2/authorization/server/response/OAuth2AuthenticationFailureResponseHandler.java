//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev <support@gstdev.com>
//// Copyright (c) 2020-2025 gstdev.com
////
//// ====================================================
//package com.gstdev.cloud.oauth2.authentication.response;
//
//import com.gstdev.cloud.assistant.definition.domain.Result;
//import com.gstdev.cloud.oauth2.authentication.utils.OAuth2EndpointUtils;
//import com.gstdev.cloud.oauth2.core.exception.SecurityGlobalExceptionHandler;
//import com.gstdev.cloud.oauth2.core.utils.WebUtils;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServletServerHttpResponse;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.OAuth2Error;
//import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
//import org.springframework.security.oauth2.core.http.converter.OAuth2ErrorHttpMessageConverter;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.util.MultiValueMap;
//
//import java.io.IOException;
//
///**
// * <p>Description: 认证失败处理器 </p>
// *
// * @author : cc
// * @date : 2022/2/19 20:48
// */
//public class OAuth2AuthenticationFailureResponseHandler implements AuthenticationFailureHandler {
//
//    private final HttpMessageConverter<OAuth2Error> errorHttpResponseConverter = new OAuth2ErrorHttpMessageConverter();
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        MultiValueMap<String, String> parameters = OAuth2EndpointUtils.getParameters(request);
//        String deviceCode = parameters.getFirst(OAuth2ParameterNames.DEVICE_CODE);
//        // 兼容 Device Grant 错误处理
//        // Device Grant 需要 SAS 原始出错信息，如果采用原有 SecurityGlobalExceptionHandler 处理方式，将导致前端获取到错误的错误信息
//        if (exception instanceof OAuth2AuthenticationException oauth2Exception && StringUtils.isNotBlank(deviceCode)) {
//            OAuth2Error error = oauth2Exception.getError();
//            ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
//            httpResponse.setStatusCode(HttpStatus.BAD_REQUEST);
//            this.errorHttpResponseConverter.write(error, null, httpResponse);
//        } else {
//            Result<String> result = SecurityGlobalExceptionHandler.resolveSecurityException(exception, request.getRequestURI());
//            response.setStatus(result.getStatus());
//            WebUtils.renderJson(response, result);
//        }
//    }
//}
