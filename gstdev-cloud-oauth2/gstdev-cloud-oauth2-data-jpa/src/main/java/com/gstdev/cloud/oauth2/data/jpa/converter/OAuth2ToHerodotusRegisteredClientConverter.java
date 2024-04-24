package com.gstdev.cloud.oauth2.data.jpa.converter;

import com.gstdev.cloud.oauth2.data.jpa.definition.converter.AbstractOAuth2EntityConverter;
import com.gstdev.cloud.oauth2.data.jpa.entity.DefaultOauth2RegisteredClient;
import com.gstdev.cloud.oauth2.data.jpa.jackson2.OAuth2JacksonProcessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: RegisteredClient 转 HerodotusRegisteredClient 转换器 </p>
 *
 * @author : cc
 * @date : 2023/5/12 23:56
 */
public class OAuth2ToHerodotusRegisteredClientConverter extends AbstractOAuth2EntityConverter<RegisteredClient, DefaultOauth2RegisteredClient> {

  private final PasswordEncoder passwordEncoder;

  public OAuth2ToHerodotusRegisteredClientConverter(OAuth2JacksonProcessor jacksonProcessor, PasswordEncoder passwordEncoder) {
    super(jacksonProcessor);
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public DefaultOauth2RegisteredClient convert(RegisteredClient registeredClient) {
    List<String> clientAuthenticationMethods = new ArrayList<>(registeredClient.getClientAuthenticationMethods().size());
    registeredClient.getClientAuthenticationMethods().forEach(clientAuthenticationMethod ->
      clientAuthenticationMethods.add(clientAuthenticationMethod.getValue()));

    List<String> authorizationGrantTypes = new ArrayList<>(registeredClient.getAuthorizationGrantTypes().size());
    registeredClient.getAuthorizationGrantTypes().forEach(authorizationGrantType ->
      authorizationGrantTypes.add(authorizationGrantType.getValue()));

    DefaultOauth2RegisteredClient entity = new DefaultOauth2RegisteredClient();
    entity.setId(registeredClient.getId());
    entity.setClientId(registeredClient.getClientId());
    entity.setClientIdIssuedAt(registeredClient.getClientIdIssuedAt());
    entity.setClientSecret(encode(registeredClient.getClientSecret()));
    entity.setClientSecretExpiresAt(registeredClient.getClientSecretExpiresAt());
    entity.setClientName(registeredClient.getClientName());
    entity.setClientAuthenticationMethods(StringUtils.collectionToCommaDelimitedString(clientAuthenticationMethods));
    entity.setAuthorizationGrantTypes(StringUtils.collectionToCommaDelimitedString(authorizationGrantTypes));
    entity.setRedirectUris(StringUtils.collectionToCommaDelimitedString(registeredClient.getRedirectUris()));
    entity.setPostLogoutRedirectUris(StringUtils.collectionToCommaDelimitedString(registeredClient.getPostLogoutRedirectUris()));
    entity.setScopes(StringUtils.collectionToCommaDelimitedString(registeredClient.getScopes()));
    entity.setClientSettings(writeMap(registeredClient.getClientSettings().getSettings()));
    entity.setTokenSettings(writeMap(registeredClient.getTokenSettings().getSettings()));

    return entity;
  }

  private String encode(String value) {
    if (value != null) {
      return this.passwordEncoder.encode(value);
    }
    return null;
  }
}
