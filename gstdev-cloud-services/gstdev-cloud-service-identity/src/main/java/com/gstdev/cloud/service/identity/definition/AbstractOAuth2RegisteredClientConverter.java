package com.gstdev.cloud.service.identity.definition;

import com.gstdev.cloud.oauth2.core.enums.AllJwsAlgorithm;
import com.gstdev.cloud.oauth2.core.enums.SignatureJwsAlgorithm;
import com.gstdev.cloud.oauth2.data.jpa.definition.converter.RegisteredClientConverter;
import com.gstdev.cloud.service.identity.entity.OAuth2Scope;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithm;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>Description: OAuth2Application  </p>
 *
 * @author : cc
 * @date : 2023/5/13 10:34
 */
public abstract class AbstractOAuth2RegisteredClientConverter<T extends AbstractOAuth2RegisteredClient> implements RegisteredClientConverter<T> {

    @Override
    public Set<String> getScopes(T details) {
        Set<OAuth2Scope> clientScopes = details.getScopes();
        return clientScopes.stream().map(OAuth2Scope::getScopeCode).collect(Collectors.toSet());
    }

    @Override
    public ClientSettings getClientSettings(T details) {
        ClientSettings.Builder clientSettingsBuilder = ClientSettings.builder();
        clientSettingsBuilder.requireAuthorizationConsent(details.getRequireAuthorizationConsent());
        clientSettingsBuilder.requireProofKey(details.getRequireProofKey());
        if (StringUtils.hasText(details.getJwkSetUrl())) {
            clientSettingsBuilder.jwkSetUrl(details.getJwkSetUrl());
        }
        JwsAlgorithm jwsAlgorithm = toJwsAlgorithm(details.getAuthenticationSigningAlgorithm());
        if (ObjectUtils.isNotEmpty(jwsAlgorithm)) {
            clientSettingsBuilder.tokenEndpointAuthenticationSigningAlgorithm(jwsAlgorithm);
        }
        return clientSettingsBuilder.build();
    }

    @Override
    public TokenSettings getTokenSettings(T details) {
        TokenSettings.Builder tokenSettingsBuilder = TokenSettings.builder();
        tokenSettingsBuilder.authorizationCodeTimeToLive(details.getAuthorizationCodeValidity());
        tokenSettingsBuilder.accessTokenTimeToLive(details.getAccessTokenValidity());
        tokenSettingsBuilder.accessTokenFormat(new OAuth2TokenFormat(details.getAccessTokenFormat().getFormat()));
        tokenSettingsBuilder.deviceCodeTimeToLive(details.getDeviceCodeValidity());
        // 是否可重用刷新令牌
        tokenSettingsBuilder.reuseRefreshTokens(details.getReuseRefreshTokens());
        // refreshToken 的有效期
        tokenSettingsBuilder.refreshTokenTimeToLive(details.getRefreshTokenValidity());
        SignatureAlgorithm signatureAlgorithm = toSignatureAlgorithm(details.getIdTokenSignatureAlgorithm());
        if (ObjectUtils.isNotEmpty(signatureAlgorithm)) {
            tokenSettingsBuilder.idTokenSignatureAlgorithm(signatureAlgorithm);
        }
        return tokenSettingsBuilder.build();
    }

    private JwsAlgorithm toJwsAlgorithm(AllJwsAlgorithm allJwsAlgorithm) {
        if (ObjectUtils.isNotEmpty(allJwsAlgorithm)) {
            if (allJwsAlgorithm.getValue() < AllJwsAlgorithm.HS256.getValue()) {
                // 如果是签名算法, 转换成 SAS 签名算法
                return SignatureAlgorithm.from(allJwsAlgorithm.name());
            } else {
                // 如果是 Mac 算法, 转换成 Mac 签名算法
                return MacAlgorithm.from(allJwsAlgorithm.name());
            }
        }

        return null;
    }

    private SignatureAlgorithm toSignatureAlgorithm(SignatureJwsAlgorithm signatureJwsAlgorithm) {
        if (ObjectUtils.isNotEmpty(signatureJwsAlgorithm)) {
            return SignatureAlgorithm.from(signatureJwsAlgorithm.name());
        }

        return null;
    }
}
