// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.authentication.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * @program: frame-cloud-base
 * @description:
 * <p>Description: OAuth 2.0 Endpoint 工具方法类 </p>
 * <p>
 * 新版 spring-security-oauth2-authorization-server 很多代码都是“包”级可访问的，外部无法使用。为了方便扩展将其提取出来，便于使用。
 * <p>
 * 代码内容与原包代码基本一致。
 *
 * @author : wenchao.chen
 * @date: 2024/03/25 10:34
 */
public class OAuth2EndpointUtils {

  public static final String ACCESS_TOKEN_REQUEST_ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";

  private OAuth2EndpointUtils() {}

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

  public static void throwError(String errorCode, String parameterName, String errorUri) {
    throw new OAuth2AuthenticationException(new OAuth2Error(errorCode, "OAuth 2.0 Parameter: " + parameterName, errorUri));
  }
}
