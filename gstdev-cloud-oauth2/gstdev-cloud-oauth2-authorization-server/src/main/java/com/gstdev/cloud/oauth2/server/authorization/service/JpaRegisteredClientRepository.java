// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.server.authorization.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gstdev.cloud.oauth2.server.authorization.domain.OAuth2RegisteredClient;
import com.gstdev.cloud.oauth2.server.authorization.domain.dto.OAuth2ClientDTO;
import com.gstdev.cloud.oauth2.server.authorization.mapstruct.OAuth2ClientMapper;
import com.gstdev.cloud.oauth2.server.authorization.repository.OAuth2RegisteredClientRepository;
import com.gstdev.cloud.web.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Slf4j
//@Service
//@AllArgsConstructor
@AutoConfigureAfter(OAuth2RegisteredClientRepository.class)
public class JpaRegisteredClientRepository implements OAuth2ClientService {

//  private final OAuth2ClientRepository oAuth2ClientRepository;
  @Resource
  private OAuth2ClientMapper oAuth2ClientMapper;
  @Autowired
  private PasswordEncoder passwordEncoder;

  private final OAuth2RegisteredClientRepository oAuth2RegisteredClientRepository;

  public JpaRegisteredClientRepository(OAuth2RegisteredClientRepository oAuth2RegisteredClientRepository) {
    Assert.notNull(oAuth2RegisteredClientRepository, "clientRepository cannot be null");
    this.oAuth2RegisteredClientRepository = oAuth2RegisteredClientRepository;
  }


  @PostConstruct
  public void initaa() {
    System.out.println("aaaaaaaaaaaa");
    log.info("sssdsadsadsasssdsadsadsasssdsadsadsasssdsadsadsasssdsadsadsasssdsadsadsasssdsadsadsasssdsadsadsa");
            /*
         客户端在数据库中记录的区别
         ------------------------------------------
         id：仅表示客户端在数据库中的这个记录
         client_id：唯一标示客户端；请求token时，以此作为客户端的账号
         client_name：客户端的名称，可以省略
         client_secret：密码
         */
    String clientId_1 = "my_client";
    String clientId_2 = "micro_service";
    // 查询客户端是否存在
//    RegisteredClient registeredClient_1 = registeredClientRepository.findByClientId(clientId_1);
//    RegisteredClient registeredClient_2 = registeredClientRepository.findByClientId(clientId_2);
    RegisteredClient registeredClient_1 = this.findByClientId(clientId_1);
    RegisteredClient registeredClient_2 = this.findByClientId(clientId_2);

    // ---------- 2、添加客户端
    // 数据库中没有
    if (registeredClient_1 == null) {
      registeredClient_1 = this.createRegisteredClientAuthorizationCode(clientId_1);
//      registeredClientRepository.save(registeredClient_1);
      this.save(registeredClient_1);
    }
    // 数据库中没有
    if (registeredClient_2 == null) {
      registeredClient_2 = this.createRegisteredClient(clientId_2);
//      registeredClientRepository.save(registeredClient_2);
      this.save(registeredClient_2);
    }
  }

  @Override
  public void save(RegisteredClient registeredClient) {
    Assert.notNull(registeredClient, "registeredClient cannot be null");
    this.oAuth2RegisteredClientRepository.save(OAuth2RegisteredClient.fromRegisteredClient(registeredClient));
  }

  @Override
  public void saveClient(OAuth2ClientDTO client) {
    OAuth2RegisteredClient oAuth2Client = client.toClient();
    oAuth2Client.setClientIdIssuedAt(Instant.now());
    this.oAuth2RegisteredClientRepository.save(oAuth2Client);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void update(OAuth2ClientDTO client) {
    String id = client.getId();
    OAuth2RegisteredClient flush = this.oAuth2RegisteredClientRepository.findById(id).orElseThrow(() -> new BadRequestException("不存在"));
    OAuth2RegisteredClient source = client.toClient();
    // 忽略密码更新
    source.setClientSecret(null);
    oAuth2ClientMapper.merge(source, flush);
    this.oAuth2RegisteredClientRepository.saveAndFlush(flush);
  }


  @Override
  public RegisteredClient findById(String id) {
    Assert.hasText(id, "id cannot be empty");
    return this.oAuth2RegisteredClientRepository.findById(id).map(OAuth2RegisteredClient::toRegisteredClient).orElse(null);
  }

  @Override
  public RegisteredClient findByClientId(String clientId) {
    Assert.hasText(clientId, "clientId cannot be empty");
    return this.oAuth2RegisteredClientRepository.findByClientId(clientId).map(OAuth2RegisteredClient::toRegisteredClient).orElse(null);
  }

  /**
   * Page of OAuth2Client.
   *
   * @param pageable the pageable
   * @return the page
   */
  @Override
  public Page<OAuth2RegisteredClient> page(Pageable pageable) {
    return this.oAuth2RegisteredClientRepository.findAll(pageable);
  }

//  @Override
//  public OAuth2RegisteredClient findClientById(String id) {
//    return this.oAuth2RegisteredClientRepository.searchOAuth2ClientById(id);
//  }

  @Override
  public void removeByClientId(String id) {
    this.oAuth2RegisteredClientRepository.deleteById(id);
  }

  /**
   * 定义客户端（令牌申请方式：授权码模式）
   *
   * @param clientId 客户端ID
   * @return
   */
  private RegisteredClient createRegisteredClientAuthorizationCode(final String clientId) {
    // JWT（Json Web Token）的配置项：TTL、是否复用refrechToken等等
    TokenSettings tokenSettings = TokenSettings.builder()
      // 令牌存活时间：2小时
      .accessTokenTimeToLive(Duration.ofHours(2))
      // 令牌可以刷新，重新获取
      .reuseRefreshTokens(true)
      // 刷新时间：30天（30天内当令牌过期时，可以用刷新令牌重新申请新令牌，不需要再认证）
      .refreshTokenTimeToLive(Duration.ofDays(30))
      .idTokenSignatureAlgorithm(SignatureAlgorithm.ES256)
      .build();
    // 客户端相关配置
    ClientSettings clientSettings = ClientSettings.builder()
      // 是否需要用户授权确认
      .requireAuthorizationConsent(false)
      .requireProofKey(false)
      .build();

    return RegisteredClient
      // 客户端ID和密码
      .withId(UUID.randomUUID().toString())
      //.withId(id)
      .clientId(clientId)
      //.clientSecret("{noop}123456")
      .clientSecret(passwordEncoder.encode("black123"))
      // 客户端名称：可省略
      .clientName("my_client_name")
      // 授权方法
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      // 授权模式
      // ---- 【授权码模式】
      .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
      // ---------- 刷新令牌（授权码模式）
      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
      /* 回调地址：
       * 授权服务器向当前客户端响应时调用下面地址；
       * 不在此列的地址将被拒统；
       * 只能使用IP或域名，不能使用localhost
       */
      .redirectUri("http://127.0.0.1:8000/login/oauth2/code/myClient")
      .redirectUri("http://127.0.0.1:8000")
      // 授权范围（当前客户端的授权范围）
      .scope("read")
      .scope("write")
      // JWT（Json Web Token）配置项
      .tokenSettings(tokenSettings)
      // 客户端配置项
      .clientSettings(clientSettings)
      .build();
  }

  /**
   * 定义客户端（令牌申请方式：客户端模式）
   *
   * @param clientId 客户端ID
   * @return
   */
  private RegisteredClient createRegisteredClient(final String clientId) {
    // JWT（Json Web Token）的配置项：TTL、是否复用refrechToken等等
    TokenSettings tokenSettings = TokenSettings.builder()
      // 令牌存活时间：1年
      .accessTokenTimeToLive(Duration.ofDays(365))
      // 令牌不可以刷新
      //.reuseRefreshTokens(false)
      .build();
    // 客户端相关配置
    ClientSettings clientSettings = ClientSettings.builder()
      // 是否需要用户授权确认
      .requireAuthorizationConsent(false)
      .build();

    return RegisteredClient
      // 客户端ID和密码
      .withId(UUID.randomUUID().toString())
      //.withId(id)
      .clientId("micro_service")
      //.clientSecret("{noop}123456")
      .clientSecret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"))
      // 客户端名称：可省略
      .clientName("micro_service")
      // 授权方法
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      // 授权模式
      // ---- 【客户端模式】
      .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
      /* 回调地址：
       * 授权服务器向当前客户端响应时调用下面地址；
       * 不在此列的地址将被拒统；
       * 只能使用IP或域名，不能使用localhost
       */
      .redirectUri("http://127.0.0.1:8001")
      .redirectUri("http://127.0.0.1:8002")
      // 授权范围（当前客户端的角色）
      .scope("all")
      // JWT（Json Web Token）配置项
      .tokenSettings(tokenSettings)
      // 客户端配置项
      .clientSettings(clientSettings)
      .build();
  }


//
//  private RegisteredClient toObject(Client client) {
//    Set<String> clientAuthenticationMethods = StringUtils.commaDelimitedListToSet(
//      client.getClientAuthenticationMethods());
//    Set<String> authorizationGrantTypes = StringUtils.commaDelimitedListToSet(
//      client.getAuthorizationGrantTypes());
//    Set<String> redirectUris = StringUtils.commaDelimitedListToSet(
//      client.getRedirectUris());
//    Set<String> postLogoutRedirectUris = StringUtils.commaDelimitedListToSet(
//      client.getPostLogoutRedirectUris());
//    Set<String> clientScopes = StringUtils.commaDelimitedListToSet(
//      client.getScopes());
//
//    RegisteredClient.Builder builder = RegisteredClient.withId(client.getId())
//      .clientId(client.getClientId())
//      .clientIdIssuedAt(client.getClientIdIssuedAt())
//      .clientSecret(client.getClientSecret())
//      .clientSecretExpiresAt(client.getClientSecretExpiresAt())
//      .clientName(client.getClientName())
//      .clientAuthenticationMethods(authenticationMethods ->
//        clientAuthenticationMethods.forEach(authenticationMethod ->
//          authenticationMethods.add(resolveClientAuthenticationMethod(authenticationMethod))))
//      .authorizationGrantTypes((grantTypes) ->
//        authorizationGrantTypes.forEach(grantType ->
//          grantTypes.add(resolveAuthorizationGrantType(grantType))))
//      .redirectUris((uris) -> uris.addAll(redirectUris))
//      .postLogoutRedirectUris((uris) -> uris.addAll(postLogoutRedirectUris))
//      .scopes((scopes) -> scopes.addAll(clientScopes));
//
//    Map<String, Object> clientSettingsMap = parseMap(client.getClientSettings());
//    builder.clientSettings(ClientSettings.withSettings(clientSettingsMap).build());
//
//    Map<String, Object> tokenSettingsMap = parseMap(client.getTokenSettings());
//    builder.tokenSettings(TokenSettings.withSettings(tokenSettingsMap).build());
//
//    return builder.build();
//  }
//
//  private OAuth2RegisteredClient toEntity(RegisteredClient registeredClient) {
//    List<String> clientAuthenticationMethods = new ArrayList<>(registeredClient.getClientAuthenticationMethods().size());
//    registeredClient.getClientAuthenticationMethods().forEach(clientAuthenticationMethod ->
//      clientAuthenticationMethods.add(clientAuthenticationMethod.getValue()));
//
//    List<String> authorizationGrantTypes = new ArrayList<>(registeredClient.getAuthorizationGrantTypes().size());
//    registeredClient.getAuthorizationGrantTypes().forEach(authorizationGrantType ->
//      authorizationGrantTypes.add(authorizationGrantType.getValue()));
//
//    OAuth2RegisteredClient entity = new OAuth2RegisteredClient();
//    entity.setId(registeredClient.getId());
//    entity.setClientId(registeredClient.getClientId());
//    entity.setClientIdIssuedAt(registeredClient.getClientIdIssuedAt());
//    entity.setClientSecret(registeredClient.getClientSecret());
//    entity.setClientSecretExpiresAt(registeredClient.getClientSecretExpiresAt());
//    entity.setClientName(registeredClient.getClientName());
//    entity.setClientAuthenticationMethods(StringUtils.collectionToCommaDelimitedString(clientAuthenticationMethods));
//    entity.setAuthorizationGrantTypes(StringUtils.collectionToCommaDelimitedString(authorizationGrantTypes));
//    entity.setRedirectUris(StringUtils.collectionToCommaDelimitedString(registeredClient.getRedirectUris()));
//    entity.setPostLogoutRedirectUris(StringUtils.collectionToCommaDelimitedString(registeredClient.getPostLogoutRedirectUris()));
//    entity.setScopes(StringUtils.collectionToCommaDelimitedString(registeredClient.getScopes()));
//    entity.setClientSettings(writeMap(registeredClient.getClientSettings().getSettings()));
//    entity.setTokenSettings(writeMap(registeredClient.getTokenSettings().getSettings()));
//
//    return entity;
//  }
//
//  private Map<String, Object> parseMap(String data) {
//    try {
//      return this.objectMapper.readValue(data, new TypeReference<Map<String, Object>>() {
//      });
//    } catch (Exception ex) {
//      throw new IllegalArgumentException(ex.getMessage(), ex);
//    }
//  }
//
//  private String writeMap(Map<String, Object> data) {
//    try {
//      return this.objectMapper.writeValueAsString(data);
//    } catch (Exception ex) {
//      throw new IllegalArgumentException(ex.getMessage(), ex);
//    }
//  }
//
//  private static AuthorizationGrantType resolveAuthorizationGrantType(String authorizationGrantType) {
//    if (AuthorizationGrantType.AUTHORIZATION_CODE.getValue().equals(authorizationGrantType)) {
//      return AuthorizationGrantType.AUTHORIZATION_CODE;
//    } else if (AuthorizationGrantType.CLIENT_CREDENTIALS.getValue().equals(authorizationGrantType)) {
//      return AuthorizationGrantType.CLIENT_CREDENTIALS;
//    } else if (AuthorizationGrantType.REFRESH_TOKEN.getValue().equals(authorizationGrantType)) {
//      return AuthorizationGrantType.REFRESH_TOKEN;
//    }
//    return new AuthorizationGrantType(authorizationGrantType);              // Custom authorization grant type
//  }
//
//  private static ClientAuthenticationMethod resolveClientAuthenticationMethod(String clientAuthenticationMethod) {
//    if (ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue().equals(clientAuthenticationMethod)) {
//      return ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
//    } else if (ClientAuthenticationMethod.CLIENT_SECRET_POST.getValue().equals(clientAuthenticationMethod)) {
//      return ClientAuthenticationMethod.CLIENT_SECRET_POST;
//    } else if (ClientAuthenticationMethod.NONE.getValue().equals(clientAuthenticationMethod)) {
//      return ClientAuthenticationMethod.NONE;
//    }
//    return new ClientAuthenticationMethod(clientAuthenticationMethod);      // Custom client authentication method
//  }
}
