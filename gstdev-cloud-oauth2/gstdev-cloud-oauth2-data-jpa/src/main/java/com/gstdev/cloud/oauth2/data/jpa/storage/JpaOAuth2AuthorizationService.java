package com.gstdev.cloud.oauth2.data.jpa.storage;

import com.gstdev.cloud.oauth2.data.jpa.converter.FrameToOAuth2AuthorizationConverter;
import com.gstdev.cloud.oauth2.data.jpa.converter.OAuth2ToFrameAuthorizationConverter;
import com.gstdev.cloud.oauth2.data.jpa.entity.FrameAuthorization;
import com.gstdev.cloud.oauth2.data.jpa.jackson2.OAuth2JacksonProcessor;
import com.gstdev.cloud.oauth2.data.jpa.service.FrameAuthorizationService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>Description: 基于 JPA 的 OAuth2 认证服务 </p>
 *
 * @author : cc
 * @date : 2022/2/25 22:16
 */
public class JpaOAuth2AuthorizationService implements OAuth2AuthorizationService {

    private static final Logger log = LoggerFactory.getLogger(JpaOAuth2AuthorizationService.class);

    private final FrameAuthorizationService frameAuthorizationService;
    private final Converter<FrameAuthorization, OAuth2Authorization> frameToOAuth2Converter;
    private final Converter<OAuth2Authorization, FrameAuthorization> oauth2ToFrameAuthorizationConverter;

    public JpaOAuth2AuthorizationService(FrameAuthorizationService frameAuthorizationService, RegisteredClientRepository registeredClientRepository) {
        this.frameAuthorizationService = frameAuthorizationService;

        OAuth2JacksonProcessor jacksonProcessor = new OAuth2JacksonProcessor();
        this.frameToOAuth2Converter = new FrameToOAuth2AuthorizationConverter(jacksonProcessor, registeredClientRepository);
        this.oauth2ToFrameAuthorizationConverter = new OAuth2ToFrameAuthorizationConverter(jacksonProcessor);

    }

    @Override
    public void save(OAuth2Authorization authorization) {
        this.frameAuthorizationService.saveAndFlush(toEntity(authorization));
    }

    @Transactional
    @Override
    public void remove(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization cannot be null");
        this.frameAuthorizationService.deleteById(authorization.getId());
        log.debug("[GstDev Cloud] |- Jpa OAuth2 Authorization Service remove entity.");
        // TODO： 后期还是考虑改为异步任务的形式，先临时放在这里。
        this.frameAuthorizationService.clearHistoryToken();
        log.debug("[GstDev Cloud] |- Jpa OAuth2 Authorization Service clear history token.");
    }

    @Override
    public OAuth2Authorization findById(String id) {
        FrameAuthorization frameAuthorization = this.frameAuthorizationService.findById(id);
        if (ObjectUtils.isNotEmpty(frameAuthorization)) {
            return toObject(frameAuthorization);
        } else {
            return null;
        }
    }

    public int findAuthorizationCount(String registeredClientId, String principalName) {
        int count = this.frameAuthorizationService.findAuthorizationCount(registeredClientId, principalName);
        log.debug("[GstDev Cloud] |- Jpa OAuth2 Authorization Service findAuthorizationCount.");
        return count;
    }

    public List<OAuth2Authorization> findAvailableAuthorizations(String registeredClientId, String principalName) {
        List<FrameAuthorization> authorizations = this.frameAuthorizationService.findAvailableAuthorizations(registeredClientId, principalName);
        if (CollectionUtils.isNotEmpty(authorizations)) {
            return authorizations.stream().map(this::toObject).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        Assert.hasText(token, "token cannot be empty");

        Optional<FrameAuthorization> result;
        if (tokenType == null) {
            result = this.frameAuthorizationService.findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValueOrOidcIdTokenValueOrUserCodeValueOrDeviceCodeValue(token);
        } else if (OAuth2ParameterNames.STATE.equals(tokenType.getValue())) {
            result = this.frameAuthorizationService.findByState(token);
        } else if (OAuth2ParameterNames.CODE.equals(tokenType.getValue())) {
            result = this.frameAuthorizationService.findByAuthorizationCode(token);
        } else if (OAuth2ParameterNames.ACCESS_TOKEN.equals(tokenType.getValue())) {
            result = this.frameAuthorizationService.findByAccessToken(token);
        } else if (OAuth2ParameterNames.REFRESH_TOKEN.equals(tokenType.getValue())) {
            result = this.frameAuthorizationService.findByRefreshToken(token);
        } else if (OidcParameterNames.ID_TOKEN.equals(tokenType.getValue())) {
            result = this.frameAuthorizationService.findByOidcIdTokenValue(token);
        } else if (OAuth2ParameterNames.USER_CODE.equals(tokenType.getValue())) {
            result = this.frameAuthorizationService.findByUserCodeValue(token);
        } else if (OAuth2ParameterNames.DEVICE_CODE.equals(tokenType.getValue())) {
            result = this.frameAuthorizationService.findByDeviceCodeValue(token);
        } else {
            result = Optional.empty();
        }

        return result.map(this::toObject).orElse(null);
    }

    private OAuth2Authorization toObject(FrameAuthorization entity) {
        return frameToOAuth2Converter.convert(entity);
    }

    private FrameAuthorization toEntity(OAuth2Authorization authorization) {
        return oauth2ToFrameAuthorizationConverter.convert(authorization);
    }
}
