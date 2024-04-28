package com.gstdev.cloud.oauth2.data.jpa.storage;

import com.gstdev.cloud.oauth2.data.jpa.converter.FrameToOAuth2AuthorizationConsentConverter;
import com.gstdev.cloud.oauth2.data.jpa.converter.OAuth2ToFrameAuthorizationConsentConverter;
import com.gstdev.cloud.oauth2.data.jpa.entity.FrameAuthorizationConsent;
import com.gstdev.cloud.oauth2.data.jpa.service.FrameAuthorizationConsentService;
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

    private final FrameAuthorizationConsentService authorizationConsentService;
    private final Converter<FrameAuthorizationConsent, OAuth2AuthorizationConsent> frameToOAuth2Converter;
    private final Converter<OAuth2AuthorizationConsent, FrameAuthorizationConsent> oauth2ToherodotusConverter;

    public JpaOAuth2AuthorizationConsentService(FrameAuthorizationConsentService authorizationConsentService, RegisteredClientRepository registeredClientRepository) {
        this.authorizationConsentService = authorizationConsentService;
        this.frameToOAuth2Converter = new FrameToOAuth2AuthorizationConsentConverter(registeredClientRepository);
        this.oauth2ToherodotusConverter = new OAuth2ToFrameAuthorizationConsentConverter();
    }

    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        this.authorizationConsentService.save(toEntity(authorizationConsent));
    }

    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        this.authorizationConsentService.deleteByRegisteredClientIdAndPrincipalName(
            authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName());
    }

    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        return this.authorizationConsentService.findByRegisteredClientIdAndPrincipalName(
            registeredClientId, principalName).map(this::toObject).orElse(null);
    }

    private OAuth2AuthorizationConsent toObject(FrameAuthorizationConsent authorizationConsent) {
        return frameToOAuth2Converter.convert(authorizationConsent);
    }

    private FrameAuthorizationConsent toEntity(OAuth2AuthorizationConsent authorizationConsent) {
        return oauth2ToherodotusConverter.convert(authorizationConsent);
    }
}
