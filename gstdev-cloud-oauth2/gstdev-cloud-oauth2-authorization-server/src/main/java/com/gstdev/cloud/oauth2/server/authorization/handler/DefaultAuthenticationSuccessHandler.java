// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.server.authorization.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * 认证成功处理器
 *
 */
@Slf4j
public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  /**
   * MappingJackson2HttpMessageConverter 是 Spring 框架提供的一个 HTTP 消息转换器，用于将 HTTP 请求和响应的 JSON 数据与 Java 对象之间进行转换
   */
//  private final HttpMessageConverter<Object> accessTokenHttpResponseConverter = new MappingJackson2HttpMessageConverter();
//  private Converter<OAuth2AccessTokenResponse, Map<String, Object>> accessTokenResponseParametersConverter = new DefaultOAuth2AccessTokenResponseMapConverter();

  /**
   * 自定义认证成功响应数据结构
   *
   * @param request the request which caused the successful authentication
   * @param response the response
   * @param authentication the <tt>Authentication</tt> object which was created during
   * the authentication process.
   * @throws IOException
   * @throws ServletException
   */
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    log.info("[GstDev Cloud] |- DefaultAuthenticationSuccessHandler for [{}]", request.getRequestURI());

//    OAuth2AccessTokenAuthenticationToken accessTokenAuthentication =
//      (OAuth2AccessTokenAuthenticationToken) authentication;
//
//    OAuth2AccessToken accessToken = accessTokenAuthentication.getAccessToken();
//    OAuth2RefreshToken refreshToken = accessTokenAuthentication.getRefreshToken();
//    Map<String, Object> additionalParameters = accessTokenAuthentication.getAdditionalParameters();
//
//    OAuth2AccessTokenResponse.Builder builder =
//      OAuth2AccessTokenResponse.withToken(accessToken.getTokenValue())
//        .tokenType(accessToken.getTokenType());
//    if (accessToken.getIssuedAt() != null && accessToken.getExpiresAt() != null) {
//      builder.expiresIn(ChronoUnit.SECONDS.between(accessToken.getIssuedAt(), accessToken.getExpiresAt()));
//    }
//    if (refreshToken != null) {
//      builder.refreshToken(refreshToken.getTokenValue());
//    }
//    if (!CollectionUtils.isEmpty(additionalParameters)) {
//      builder.additionalParameters(additionalParameters);
//    }
//    OAuth2AccessTokenResponse accessTokenResponse = builder.build();
//
//    Map<String, Object> tokenResponseParameters = this.accessTokenResponseParametersConverter
//      .convert(accessTokenResponse);
//    ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
//
//    this.accessTokenHttpResponseConverter.write(Result.success(tokenResponseParameters), null, httpResponse);
//  }
  }
}
