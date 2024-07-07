package com.gstdev.cloud.service.identity.domain.converter;

import com.gstdev.cloud.oauth2.core.enums.AllJwsAlgorithm;
import com.gstdev.cloud.oauth2.core.enums.SignatureJwsAlgorithm;
import com.gstdev.cloud.oauth2.core.enums.TokenFormat;
import com.gstdev.cloud.service.identity.domain.entity.OAuth2Application;
import com.gstdev.cloud.service.identity.domain.entity.OAuth2Device;
import com.gstdev.cloud.service.identity.domain.entity.OAuth2Scope;
import com.gstdev.cloud.service.identity.service.OAuth2ScopeService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>Description: OAuth2Device 转 RegisteredClient 转换器 </p>
 *
 * @author : cc
 * @date : 2023/5/21 22:05
 */
public class RegisteredClientToOAuth2ApplicationConverter implements Converter<RegisteredClient, OAuth2Application> {

    private final OAuth2ScopeService scopeService;

    public RegisteredClientToOAuth2ApplicationConverter(OAuth2ScopeService scopeService) {
        this.scopeService = scopeService;
    }

    @Override
    public OAuth2Application convert(RegisteredClient registeredClient) {

        OAuth2Application auth2Application = new OAuth2Application();
        auth2Application.setApplicationId(registeredClient.getId());
        auth2Application.setApplicationName(registeredClient.getClientName());
        auth2Application.setScopes(getOAuth2Scopes(registeredClient.getScopes()));
        auth2Application.setClientId(registeredClient.getClientId());
        auth2Application.setClientSecret(registeredClient.getClientSecret());
        auth2Application.setClientIdIssuedAt(registeredClient.getClientIdIssuedAt());
        auth2Application.setClientSecretExpiresAt(registeredClient.getClientSecretExpiresAt());
        auth2Application.setClientAuthenticationMethods(StringUtils.collectionToCommaDelimitedString(registeredClient.getClientAuthenticationMethods()));
        auth2Application.setAuthorizationGrantTypes(StringUtils.collectionToCommaDelimitedString(registeredClient.getAuthorizationGrantTypes().stream().map(AuthorizationGrantType::getValue).collect(Collectors.toSet())));
        auth2Application.setRedirectUris(StringUtils.collectionToCommaDelimitedString(registeredClient.getRedirectUris()));
        auth2Application.setPostLogoutRedirectUris(StringUtils.collectionToCommaDelimitedString(registeredClient.getRedirectUris()));

        ClientSettings clientSettings = registeredClient.getClientSettings();
        auth2Application.setRequireProofKey(clientSettings.isRequireProofKey());
        auth2Application.setRequireAuthorizationConsent(clientSettings.isRequireAuthorizationConsent());
        auth2Application.setJwkSetUrl(clientSettings.getJwkSetUrl());
        if (ObjectUtils.isNotEmpty(clientSettings.getTokenEndpointAuthenticationSigningAlgorithm())) {
            auth2Application.setAuthenticationSigningAlgorithm(AllJwsAlgorithm.valueOf(clientSettings.getTokenEndpointAuthenticationSigningAlgorithm().getName()));
        }

        TokenSettings tokenSettings = registeredClient.getTokenSettings();
        auth2Application.setAuthorizationCodeValidity(tokenSettings.getAuthorizationCodeTimeToLive());
        auth2Application.setAccessTokenValidity(tokenSettings.getAccessTokenTimeToLive());
        auth2Application.setDeviceCodeValidity(tokenSettings.getDeviceCodeTimeToLive());
        auth2Application.setRefreshTokenValidity(tokenSettings.getRefreshTokenTimeToLive());
        auth2Application.setAccessTokenFormat(TokenFormat.get(tokenSettings.getAccessTokenFormat().getValue()));
        auth2Application.setReuseRefreshTokens(tokenSettings.isReuseRefreshTokens());
        auth2Application.setIdTokenSignatureAlgorithm(SignatureJwsAlgorithm.valueOf(tokenSettings.getIdTokenSignatureAlgorithm().getName()));

        return auth2Application;
    }

    private Set<OAuth2Scope> getOAuth2Scopes(Set<String> scopes) {
        if (CollectionUtils.isNotEmpty(scopes)) {
            List<String> scopeCodes = new ArrayList<>(scopes);
            List<OAuth2Scope> result = scopeService.findByScopeCodeIn(scopeCodes);
            if (CollectionUtils.isNotEmpty(result)) {
                return new HashSet<>(result);
            }
        }
        return new HashSet<>();
    }
}
