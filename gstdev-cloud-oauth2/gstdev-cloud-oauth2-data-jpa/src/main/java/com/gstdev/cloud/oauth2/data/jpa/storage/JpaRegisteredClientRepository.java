package com.gstdev.cloud.oauth2.data.jpa.storage;

import com.gstdev.cloud.oauth2.data.jpa.converter.HerodotusToOAuth2RegisteredClientConverter;
import com.gstdev.cloud.oauth2.data.jpa.converter.OAuth2ToHerodotusRegisteredClientConverter;
import com.gstdev.cloud.oauth2.data.jpa.entity.HerodotusRegisteredClient;
import com.gstdev.cloud.oauth2.data.jpa.jackson2.OAuth2JacksonProcessor;
import com.gstdev.cloud.oauth2.data.jpa.service.HerodotusRegisteredClientService;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>Description: 基于Jpa 的 RegisteredClient服务 </p>
 *
 * @author : cc
 * @date : 2022/2/25 21:27
 */
public class JpaRegisteredClientRepository implements RegisteredClientRepository {

  private final HerodotusRegisteredClientService herodotusRegisteredClientService;
  private final Converter<HerodotusRegisteredClient, RegisteredClient> herodotusToOAuth2Converter;
  private final Converter<RegisteredClient, HerodotusRegisteredClient> oauth2ToHerodotusConverter;

  public JpaRegisteredClientRepository(HerodotusRegisteredClientService herodotusRegisteredClientService, PasswordEncoder passwordEncoder) {
    this.herodotusRegisteredClientService = herodotusRegisteredClientService;
    OAuth2JacksonProcessor jacksonProcessor = new OAuth2JacksonProcessor();
    this.herodotusToOAuth2Converter = new HerodotusToOAuth2RegisteredClientConverter(jacksonProcessor);
    this.oauth2ToHerodotusConverter = new OAuth2ToHerodotusRegisteredClientConverter(jacksonProcessor, passwordEncoder);
  }

  @PostConstruct
  public void init() {
    System.out.println("aaaaaaaaaaaa");
    List<RegisteredClient> registeredClients = this.registryCloents();
    for (RegisteredClient registeredClient : registeredClients) {
      RegisteredClient registeredClientId = this.findByClientId(registeredClient.getClientId());
      if (registeredClientId == null) {
        this.save(registeredClient);
      }
    }
  }

  @Override
  public void save(RegisteredClient registeredClient) {
    this.herodotusRegisteredClientService.save(toEntity(registeredClient));
  }

  @Override
  public RegisteredClient findById(String id) {
    HerodotusRegisteredClient herodotusRegisteredClient = this.herodotusRegisteredClientService.findById(id);
    if (ObjectUtils.isNotEmpty(herodotusRegisteredClient)) {
      return toObject(herodotusRegisteredClient);
    }
    return null;
  }

  @Override
  public RegisteredClient findByClientId(String clientId) {
    return this.herodotusRegisteredClientService.findByClientId(clientId).map(this::toObject).orElse(null);
  }

  public void remove(String id) {
    this.herodotusRegisteredClientService.deleteById(id);
  }

  private RegisteredClient toObject(HerodotusRegisteredClient herodotusRegisteredClient) {
    return herodotusToOAuth2Converter.convert(herodotusRegisteredClient);
  }

  private HerodotusRegisteredClient toEntity(RegisteredClient registeredClient) {
    return oauth2ToHerodotusConverter.convert(registeredClient);
  }


  public List<RegisteredClient> registryCloents() {

             /*
         客户端在数据库中记录的区别
         ------------------------------------------
         id：仅表示客户端在数据库中的这个记录
         client_id：唯一标示客户端；请求token时，以此作为客户端的账号
         client_name：客户端的名称，可以省略
         client_secret：密码
         */
    //    /** 此处保留作为client入库代码案例
//     RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
//     .clientId("messaging-client")
//     .clientSecret("secret")
//     .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//     .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//     .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//     .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//     .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
//     .redirectUri("http://127.0.0.1:8080/authorized")
//     .scope(OidcScopes.OPENID)
//     .scope("message.read")
//     .scope("message.write")
//     .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//     .build();
    RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
      .clientId("oidc-client")
      .clientSecret("{noop}secret")
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
      .redirectUri("http://127.0.0.1:8080/login/oauth2/code/oidc-client")
      .postLogoutRedirectUri("http://127.0.0.1:8080/")
      .scope(OidcScopes.OPENID)
      .scope(OidcScopes.PROFILE)
      .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
      .build();
        /*
          如果使用明文，客户端认证时会自动升级加密方式，换句话说直接修改客户端密码，所以直接使用 bcrypt 加密避免不必要的麻烦
          官方ISSUE： https://github.com/spring-projects/spring-authorization-server/issues/1099
         */
//      String encodeSecret = passwordEncoder().encode(clientSecret);
//     @formatter:off
    RegisteredClient codeRegisteredClient = RegisteredClient
      .withId(UUID.randomUUID().toString())
      .clientId("code-client")
      .clientName("Code Client")
//      .clientSecret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"))
      .clientSecret("123456")
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
      .redirectUri("http://127.0.0.1:8080/authorized")
      .redirectUri("http://www.baidu.com")
      .scope("project:read")
      .scope("project:write")
      .clientSettings(ClientSettings.builder()
        .requireAuthorizationConsent(false)
        .requireProofKey(false)
        .build()
      )

      .tokenSettings(TokenSettings.builder()
        .accessTokenTimeToLive(Duration.ofHours(1))
        .refreshTokenTimeToLive(Duration.ofHours(3))
        .idTokenSignatureAlgorithm(SignatureAlgorithm.ES256)
        .reuseRefreshTokens(true)
        .build()
      )
      .build();

    RegisteredClient credentialsRegisteredClient = RegisteredClient
      .withId(UUID.randomUUID().toString())
      .clientId("credentials-client")
      .clientName("Credentials Client")
//      .clientSecret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"))
      .clientSecret("123456")
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
      .clientSettings(ClientSettings.builder()
        .requireAuthorizationConsent(false)
        .requireProofKey(false)
        .build()
      )
      .tokenSettings(TokenSettings.builder()
        .accessTokenTimeToLive(Duration.ofHours(720))
        .refreshTokenTimeToLive(Duration.ofHours(720))
        .idTokenSignatureAlgorithm(SignatureAlgorithm.ES256)
        .reuseRefreshTokens(true)
        .build()
      )
      .build();

    RegisteredClient passwordRegisteredClient = RegisteredClient
      .withId(UUID.randomUUID().toString())
      .clientId("password-client")
      .clientName("Password Client")
//      .clientSecret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"))
      .clientSecret("123456")
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      .authorizationGrantType(AuthorizationGrantType.PASSWORD)
      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
      .clientSettings(ClientSettings.builder()
        .requireAuthorizationConsent(false)
        .requireProofKey(false)
        .build()
      )

      .tokenSettings(TokenSettings.builder()
        .accessTokenTimeToLive(Duration.ofHours(720))
        .refreshTokenTimeToLive(Duration.ofHours(720))
        .idTokenSignatureAlgorithm(SignatureAlgorithm.ES256)
        .reuseRefreshTokens(true)
        .build()
      )
      .build();


    RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
      .clientId("oauth2-client")
//      .clientSecret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"))
      .clientSecret("123456")
      // 客户端认证基于请求头
      .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
      // 配置授权的支持方式
      .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
      .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
      .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
      .redirectUri("https://www.baidu.com")
      .scope("user")
      .scope("admin")
      // 客户端设置，设置用户需要确认授权
      .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
      // 添加tokenSettings，将accessTokenFormat改为REFERENCE即可获取Opaque Token
      .tokenSettings(TokenSettings.builder()
//        .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
        // 令牌存活时间：2小时
        .accessTokenTimeToLive(Duration.ofHours(2))
        // 令牌可以刷新，重新获取
        .reuseRefreshTokens(true)
        // 刷新时间：30天（30天内当令牌过期时，可以用刷新令牌重新申请新令牌，不需要再认证）
        .refreshTokenTimeToLive(Duration.ofDays(30)).build())
      .build();
    List<RegisteredClient> registryCloents = new ArrayList<>();
    registryCloents.add(createRegisteredClientAuthorizationCode("my_client"));
    registryCloents.add(createRegisteredClient("micro_service"));
    registryCloents.add(oidcClient);
    registryCloents.add(codeRegisteredClient);
    registryCloents.add(credentialsRegisteredClient);
    registryCloents.add(passwordRegisteredClient);
    registryCloents.add(registeredClient);

    return registryCloents;
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
      .clientId(clientId)
      //.clientSecret("{noop}123456")
//      .clientSecret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"))
      .clientSecret("123456")
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
//      .clientSecret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"))
      .clientSecret("123456")
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

//  public static void main(String[] args) {
//    System.out.println(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"));
//  }
}
