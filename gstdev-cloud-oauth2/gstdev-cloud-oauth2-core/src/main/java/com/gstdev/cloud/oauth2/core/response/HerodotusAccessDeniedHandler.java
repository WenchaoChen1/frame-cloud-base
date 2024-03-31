// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.core.response;

import com.gstdev.cloud.commons.ass.definition.domain.Result;
import com.gstdev.cloud.oauth2.core.exception.SecurityGlobalExceptionHandler;
import com.gstdev.cloud.oauth2.core.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * <p>Description: 访问拒绝处理器 </p>
 *
 * @author : cc
 * @date : 2022/3/8 8:52
 */
public class HerodotusAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result<String> result = SecurityGlobalExceptionHandler.resolveException(accessDeniedException, request.getRequestURI());
        response.setStatus(result.getStatus());
        WebUtils.renderJson(response, result);
    }
}
