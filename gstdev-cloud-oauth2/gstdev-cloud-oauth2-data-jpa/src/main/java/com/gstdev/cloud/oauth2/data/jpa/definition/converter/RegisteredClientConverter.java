package com.gstdev.cloud.oauth2.data.jpa.definition.converter;

import com.gstdev.cloud.oauth2.core.definition.domain.RegisteredClientDetails;
import com.gstdev.cloud.oauth2.core.utils.OAuth2AuthorizationUtils;
import org.dromara.hutool.core.date.DateUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * <p>Description: 转换为 RegisteredClient 转换器定义</p>
 *
 * @author : cc
 * @date : 2023/5/21 20:36
 */
public interface RegisteredClientConverter<S extends RegisteredClientDetails> extends Converter<S, RegisteredClient> {

  Set<String> getScopes(S details);

  ClientSettings getClientSettings(S details);

  TokenSettings getTokenSettings(S details);

  @Override
  default RegisteredClient convert(S details) {
    Set<String> clientScopes = getScopes(details);
    ClientSettings clientSettings = getClientSettings(details);
    TokenSettings tokenSettings = getTokenSettings(details);

    Set<String> clientAuthenticationMethods = StringUtils.commaDelimitedListToSet(details.getClientAuthenticationMethods());
    Set<String> authorizationGrantTypes = StringUtils.commaDelimitedListToSet(details.getAuthorizationGrantTypes());
    Set<String> redirectUris = StringUtils.commaDelimitedListToSet(details.getRedirectUris());
    Set<String> postLogoutRedirectUris = StringUtils.commaDelimitedListToSet(details.getPostLogoutRedirectUris());

    return RegisteredClient.withId(details.getId())
      .clientId(details.getClientId())
      .clientIdIssuedAt(DateUtil.toInstant(details.getClientIdIssuedAt()))
      .clientSecret(details.getClientSecret())
      .clientSecretExpiresAt(DateUtil.toInstant(details.getClientSecretExpiresAt()))
      .clientName(details.getId())
      .clientAuthenticationMethods(authenticationMethods ->
        clientAuthenticationMethods.forEach(authenticationMethod ->
          authenticationMethods.add(OAuth2AuthorizationUtils.resolveClientAuthenticationMethod(authenticationMethod))))
      .authorizationGrantTypes((grantTypes) ->
        authorizationGrantTypes.forEach(grantType ->
          grantTypes.add(OAuth2AuthorizationUtils.resolveAuthorizationGrantType(grantType))))
      .redirectUris((uris) -> uris.addAll(redirectUris))
      .postLogoutRedirectUris((uris) -> uris.addAll(postLogoutRedirectUris))
      .scopes((scopes) -> scopes.addAll(clientScopes))
      .clientSettings(clientSettings)
      .tokenSettings(tokenSettings)
      .build();
  }
}
