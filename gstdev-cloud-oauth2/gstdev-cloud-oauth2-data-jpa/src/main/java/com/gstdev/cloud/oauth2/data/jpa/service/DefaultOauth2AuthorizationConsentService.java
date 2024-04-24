package com.gstdev.cloud.oauth2.data.jpa.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.oauth2.data.jpa.entity.DefaultOauth2AuthorizationConsent;
import com.gstdev.cloud.oauth2.data.jpa.generator.HerodotusAuthorizationConsentId;
import com.gstdev.cloud.oauth2.data.jpa.repository.HerodotusAuthorizationConsentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>Description: HerodotusAuthorizationConsentService </p>
 * <p>
 * 这里命名没有按照统一的习惯，主要是为了防止与 spring-authorization-server 已有类的同名而导致Bean注入失败
 *
 * @author : cc
 * @date : 2022/2/25 21:02
 */
@Service
public class DefaultOauth2AuthorizationConsentService extends BaseServiceImpl<DefaultOauth2AuthorizationConsent, HerodotusAuthorizationConsentId, HerodotusAuthorizationConsentRepository> {

    private static final Logger log = LoggerFactory.getLogger(DefaultOauth2AuthorizationConsentService.class);

    private HerodotusAuthorizationConsentRepository authorizationConsentRepository;

    @Autowired
    public DefaultOauth2AuthorizationConsentService(HerodotusAuthorizationConsentRepository authorizationConsentRepository) {
        super(authorizationConsentRepository);
    }

    public HerodotusAuthorizationConsentRepository getRepository() {
        return this.authorizationConsentRepository;
    }

    public Optional<DefaultOauth2AuthorizationConsent> findByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName) {
        Optional<DefaultOauth2AuthorizationConsent> result = this.authorizationConsentRepository.findByRegisteredClientIdAndPrincipalName(registeredClientId, principalName);
        log.trace("[GstDev Cloud] |- HerodotusAuthorizationConsent Service findByRegisteredClientIdAndPrincipalName.");
        return result;
    }

    public void deleteByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName) {
        this.authorizationConsentRepository.deleteByRegisteredClientIdAndPrincipalName(registeredClientId, principalName);
        log.trace("[GstDev Cloud] |- HerodotusAuthorizationConsent Service deleteByRegisteredClientIdAndPrincipalName.");
    }
}
