//package com.gstdev.cloud.starter.oauth2.authentication.server.service;
//
//import com.gstdev.cloud.commons.utils.RedisUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.core.OAuth2AccessToken;
//import org.springframework.security.oauth2.core.OAuth2RefreshToken;
//import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
//import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
//import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
//import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
//import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
//import org.springframework.util.Assert;
//
//import java.time.temporal.ChronoUnit;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.concurrent.TimeUnit;
//
//public class RedisOAuth2AuthorizationService implements OAuth2AuthorizationService {
//
//  private static final Long TIMEOUT = 10L;
//
//  @Autowired
//  private RedisUtils redisUtils;
//
//  private static final String AUTHORIZATION = "token";
//
//
//  @Override
//  public void save(OAuth2Authorization authorization) {
//    Assert.notNull(authorization, "authorization cannot be null");
//
//    if (isState(authorization)) {
//      String token = authorization.getAttribute("state");
//        redisUtils.set(buildKey(OAuth2ParameterNames.STATE, token), authorization, TIMEOUT, TimeUnit.MINUTES);
//    }
//
//    if (isCode(authorization)) {
//      OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization
//        .getToken(OAuth2AuthorizationCode.class);
//      OAuth2AuthorizationCode authorizationCodeToken = authorizationCode.getToken();
//      long between = ChronoUnit.MINUTES.between(authorizationCodeToken.getIssuedAt(),
//        authorizationCodeToken.getExpiresAt());
//        redisUtils.set(buildKey(OAuth2ParameterNames.CODE, authorizationCodeToken.getTokenValue()),
//        authorization, between, TimeUnit.MINUTES);
//    }
//
//    if (isRefreshToken(authorization)) {
//      OAuth2RefreshToken refreshToken = authorization.getRefreshToken().getToken();
//      long between = ChronoUnit.SECONDS.between(refreshToken.getIssuedAt(), refreshToken.getExpiresAt());
//      redisUtils.set(buildKey(OAuth2ParameterNames.REFRESH_TOKEN, refreshToken.getTokenValue()),
//        authorization, between, TimeUnit.SECONDS);
//    }
//
//    if (isAccessToken(authorization)) {
//      OAuth2AccessToken accessToken = authorization.getAccessToken().getToken();
//      long between = ChronoUnit.SECONDS.between(accessToken.getIssuedAt(), accessToken.getExpiresAt());
//      redisUtils.set(buildKey(OAuth2ParameterNames.ACCESS_TOKEN, accessToken.getTokenValue()), authorization, between, TimeUnit.SECONDS);
//    }
//  }
//
//  @Override
//  public void remove(OAuth2Authorization authorization) {
//    Assert.notNull(authorization, "authorization cannot be null");
//
//    List<String> keys = new ArrayList<>();
//    if (isState(authorization)) {
//      String token = authorization.getAttribute("state");
//      keys.add(buildKey(OAuth2ParameterNames.STATE, token));
//    }
//
//    if (isCode(authorization)) {
//      OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization
//        .getToken(OAuth2AuthorizationCode.class);
//      OAuth2AuthorizationCode authorizationCodeToken = authorizationCode.getToken();
//      keys.add(buildKey(OAuth2ParameterNames.CODE, authorizationCodeToken.getTokenValue()));
//    }
//
//    if (isRefreshToken(authorization)) {
//      OAuth2RefreshToken refreshToken = authorization.getRefreshToken().getToken();
//      keys.add(buildKey(OAuth2ParameterNames.REFRESH_TOKEN, refreshToken.getTokenValue()));
//    }
//
//    if (isAccessToken(authorization)) {
//      OAuth2AccessToken accessToken = authorization.getAccessToken().getToken();
//      keys.add(buildKey(OAuth2ParameterNames.ACCESS_TOKEN, accessToken.getTokenValue()));
//    }
//    redisUtils.del(String.valueOf(keys));
//  }
//
//  @Override
//  public OAuth2Authorization findById(String id) {
//    throw new UnsupportedOperationException();
//  }
//
//  @Override
//  public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
//    Assert.hasText(token, "token cannot be empty");
//    Assert.notNull(tokenType, "tokenType cannot be empty");
//    return (OAuth2Authorization) redisUtils.get(buildKey(tokenType.getValue(), token));
//  }
//
//  private String buildKey(String type, String id) {
//    return String.format("%s::%s::%s", AUTHORIZATION, type, id);
//  }
//
//  private static boolean isState(OAuth2Authorization authorization) {
//    return Objects.nonNull(authorization.getAttribute("state"));
//  }
//
//  private static boolean isCode(OAuth2Authorization authorization) {
//    OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization
//      .getToken(OAuth2AuthorizationCode.class);
//    return Objects.nonNull(authorizationCode);
//  }
//
//  private static boolean isRefreshToken(OAuth2Authorization authorization) {
//    return Objects.nonNull(authorization.getRefreshToken());
//  }
//
//  private static boolean isAccessToken(OAuth2Authorization authorization) {
//    return Objects.nonNull(authorization.getAccessToken());
//  }
//}
