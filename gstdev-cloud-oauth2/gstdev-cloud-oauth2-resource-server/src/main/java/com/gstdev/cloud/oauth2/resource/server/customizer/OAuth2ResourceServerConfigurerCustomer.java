package com.gstdev.cloud.oauth2.resource.server.customizer;

import com.gstdev.cloud.base.core.enums.Target;
import com.gstdev.cloud.oauth2.resource.server.properties.OAuth2AuthorizationProperties;
import com.gstdev.cloud.oauth2.core.response.HerodotusAccessDeniedHandler;
import com.gstdev.cloud.oauth2.core.response.HerodotusAuthenticationEntryPoint;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;

/**
 * <p>Description: OAuth2ResourceServerConfigurer 扩展配置</p>
 *
 * @author : cc
 */
public class OAuth2ResourceServerConfigurerCustomer implements Customizer<OAuth2ResourceServerConfigurer<HttpSecurity>> {

  private final JwtDecoder jwtDecoder;
  private final OAuth2AuthorizationProperties authorizationProperties;
//  private final OpaqueTokenIntrospector opaqueTokenIntrospector;

  public OAuth2ResourceServerConfigurerCustomer(OAuth2AuthorizationProperties authorizationProperties, JwtDecoder jwtDecoder, OAuth2ResourceServerProperties resourceServerProperties) {
    this.jwtDecoder = jwtDecoder;
    this.authorizationProperties = authorizationProperties;
//    this.opaqueTokenIntrospector = new HerodotusOpaqueTokenIntrospector(resourceServerProperties);
  }

  private boolean isRemoteValidate() {
    return this.authorizationProperties.getValidate() == Target.REMOTE;
  }

  @Override
  public void customize(OAuth2ResourceServerConfigurer<HttpSecurity> configurer) {
//    if (isRemoteValidate()) {
//      configurer
//        .opaqueToken(opaque -> opaque.introspector(opaqueTokenIntrospector));
//    } else {
    configurer
      .jwt(jwt -> jwt.decoder(this.jwtDecoder)
//                      .jwtAuthenticationConverter(new CustomizeJwtAuthenticationConverter())
      )
      .bearerTokenResolver(new DefaultBearerTokenResolver());
//    }

    configurer
      .accessDeniedHandler(new HerodotusAccessDeniedHandler())
      .authenticationEntryPoint(new HerodotusAuthenticationEntryPoint());
  }

//    public BearerTokenResolver createBearerTokenResolver() {
//        return new HerodotusBearerTokenResolver(this.jwtDecoder, this.opaqueTokenIntrospector, this.isRemoteValidate());
//    }
}
