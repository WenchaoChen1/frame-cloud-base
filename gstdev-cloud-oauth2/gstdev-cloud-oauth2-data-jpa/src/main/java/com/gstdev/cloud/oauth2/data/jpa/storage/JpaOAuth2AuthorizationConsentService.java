package com.gstdev.cloud.oauth2.data.jpa.storage;

import com.gstdev.cloud.oauth2.data.jpa.converter.HerodotusToOAuth2AuthorizationConsentConverter;
import com.gstdev.cloud.oauth2.data.jpa.converter.OAuth2ToHerodotusAuthorizationConsentConverter;
import com.gstdev.cloud.oauth2.data.jpa.entity.DefaultOauth2AuthorizationConsent;
import com.gstdev.cloud.oauth2.data.jpa.service.DefaultOauth2AuthorizationConsentService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * <p>Description: 基于 JPA 的 OAuth2 认证服务 </p>
 *
 * @author : cc
 * @date : 2022/2/25 22:15
 */
public class JpaOAuth2AuthorizationConsentService implements OAuth2AuthorizationConsentService {

  private final DefaultOauth2AuthorizationConsentService herodotusAuthorizationConsentService;
  private final Converter<DefaultOauth2AuthorizationConsent, OAuth2AuthorizationConsent> herodotusToOAuth2Converter;
  private final Converter<OAuth2AuthorizationConsent, DefaultOauth2AuthorizationConsent> oauth2ToherodotusConverter;

  public JpaOAuth2AuthorizationConsentService(DefaultOauth2AuthorizationConsentService herodotusAuthorizationConsentService, RegisteredClientRepository registeredClientRepository) {
    this.herodotusAuthorizationConsentService = herodotusAuthorizationConsentService;
    this.herodotusToOAuth2Converter = new HerodotusToOAuth2AuthorizationConsentConverter(registeredClientRepository);
    this.oauth2ToherodotusConverter = new OAuth2ToHerodotusAuthorizationConsentConverter();
  }

  @Override
  public void save(OAuth2AuthorizationConsent authorizationConsent) {
    this.herodotusAuthorizationConsentService.save(toEntity(authorizationConsent));
  }

  @Override
  public void remove(OAuth2AuthorizationConsent authorizationConsent) {
    this.herodotusAuthorizationConsentService.deleteByRegisteredClientIdAndPrincipalName(
      authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName());
  }

  @Override
  public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
    return this.herodotusAuthorizationConsentService.findByRegisteredClientIdAndPrincipalName(
      registeredClientId, principalName).map(this::toObject).orElse(null);
  }

  private OAuth2AuthorizationConsent toObject(DefaultOauth2AuthorizationConsent authorizationConsent) {
    return herodotusToOAuth2Converter.convert(authorizationConsent);
  }

  private DefaultOauth2AuthorizationConsent toEntity(OAuth2AuthorizationConsent authorizationConsent) {
    return oauth2ToherodotusConverter.convert(authorizationConsent);
  }
}
