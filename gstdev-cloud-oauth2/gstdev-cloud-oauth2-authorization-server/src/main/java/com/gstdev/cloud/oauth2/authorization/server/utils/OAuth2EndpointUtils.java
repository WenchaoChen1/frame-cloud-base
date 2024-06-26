// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.authorization.server.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.endpoint.PkceParameterNames;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : wenchao.chen
 * @program: frame-cloud-base
 * @description: <p>Description: OAuth 2.0 Endpoint 工具方法类 </p>
 * <p>
 * 新版 spring-security-oauth2-authorization-server 很多代码都是“包”级可访问的，外部无法使用。为了方便扩展将其提取出来，便于使用。
 * <p>
 * 代码内容与原包代码基本一致。
 * @date: 2024/03/25 10:34
 */
public class OAuth2EndpointUtils {
    // 定义常量用于表示访问令牌请求错误的URI
    public static final String ACCESS_TOKEN_REQUEST_ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";

    // 私有构造函数，避免实例化该类
    private OAuth2EndpointUtils() {
    }

    /**
     * 获取请求中的参数
     *
     * @param request HTTP请求
     * @return 包含参数的MultiValueMap
     */
    public static MultiValueMap<String, String> getParameters(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>(parameterMap.size());
        parameterMap.forEach((key, values) -> {
            if (values.length > 0) {
                for (String value : values) {
                    parameters.add(key, value);
                }
            }
        });

        return parameters;
    }

    /**
     * 如果请求与授权码授予请求匹配，则获取请求中的参数
     *
     * @param request    HTTP请求
     * @param exclusions 排除的参数
     * @return 包含参数的Map
     */
    public static Map<String, Object> getParametersIfMatchesAuthorizationCodeGrantRequest(HttpServletRequest request, String... exclusions) {
        if (!matchesAuthorizationCodeGrantRequest(request)) {
            return Collections.emptyMap();
        }
        return getParameters(request, exclusions);
    }

    /**
     * 获取请求中的参数，并排除指定的参数
     *
     * @param request    HTTP请求
     * @param exclusions 排除的参数
     * @return 包含参数的Map
     */
    public static Map<String, Object> getParameters(HttpServletRequest request, String... exclusions) {
        Map<String, Object> parameters = new HashMap<>(getParameters(request).toSingleValueMap());
        for (String exclusion : exclusions) {
            parameters.remove(exclusion);
        }
        return parameters;
    }

    // 以下几个方法用于检查请求是否匹配特定的授权类型
    public static boolean matchesClientCredentialsGrantRequest(HttpServletRequest request) {
        return AuthorizationGrantType.CLIENT_CREDENTIALS.getValue().equals(request.getParameter(OAuth2ParameterNames.GRANT_TYPE));
    }

    public static boolean matchesAuthorizationCodeGrantRequest(HttpServletRequest request) {
        return AuthorizationGrantType.AUTHORIZATION_CODE.getValue().equals(
                request.getParameter(OAuth2ParameterNames.GRANT_TYPE)) &&
                request.getParameter(OAuth2ParameterNames.CODE) != null;
    }

    public static boolean matchesPkceTokenRequest(HttpServletRequest request) {
        return matchesAuthorizationCodeGrantRequest(request) &&
                request.getParameter(PkceParameterNames.CODE_VERIFIER) != null;
    }

    // 以下两个方法用于抛出OAuth2身份验证异常
    public static void throwError(String errorCode, String parameterName) {
        throwError(errorCode, parameterName, ACCESS_TOKEN_REQUEST_ERROR_URI);
    }

    public static void throwError(String errorCode, String parameterName, String errorUri) {
        OAuth2Error error = new OAuth2Error(errorCode, "OAuth 2.0 Parameter: " + parameterName, errorUri);
        throw new OAuth2AuthenticationException(error);
    }

    // 下面几个方法用于检查参数的存在和合法性
    private static boolean checkRequired(MultiValueMap<String, String> parameters, String parameterName, String parameterValue) {
        return !StringUtils.hasText(parameterValue) || parameters.get(parameterName).size() != 1;
    }

    private static boolean checkOptional(MultiValueMap<String, String> parameters, String parameterName, String parameterValue) {
        return StringUtils.hasText(parameterValue) && parameters.get(parameterName).size() != 1;
    }

    public static String checkParameter(MultiValueMap<String, String> parameters, String parameterName, boolean isRequired, String errorCode, String errorUri) {
        String value = parameters.getFirst(parameterName);
        if (isRequired) {
            if (checkRequired(parameters, parameterName, value)) {
                OAuth2EndpointUtils.throwError(errorCode, parameterName, errorUri);
            }
        } else {
            if (checkOptional(parameters, parameterName, value)) {
                OAuth2EndpointUtils.throwError(errorCode, parameterName, errorUri);
            }
        }

        return value;
    }

    public static String checkRequiredParameter(MultiValueMap<String, String> parameters, String parameterName, String errorCode, String errorUri) {
        return checkParameter(parameters, parameterName, true, errorCode, errorUri);
    }

    public static String checkRequiredParameter(MultiValueMap<String, String> parameters, String parameterName, String errorCode) {
        return checkRequiredParameter(parameters, parameterName, errorCode, OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
    }

    public static String checkRequiredParameter(MultiValueMap<String, String> parameters, String parameterName) {
        return checkRequiredParameter(parameters, parameterName, OAuth2ErrorCodes.INVALID_REQUEST);
    }

    public static String checkOptionalParameter(MultiValueMap<String, String> parameters, String parameterName, String errorCode, String errorUri) {
        return checkParameter(parameters, parameterName, false, errorCode, errorUri);
    }

    public static String checkOptionalParameter(MultiValueMap<String, String> parameters, String parameterName, String errorCode) {
        return checkOptionalParameter(parameters, parameterName, errorCode, OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
    }

    public static String checkOptionalParameter(MultiValueMap<String, String> parameters, String parameterName) {
        return checkOptionalParameter(parameters, parameterName, OAuth2ErrorCodes.INVALID_REQUEST);
    }
}
