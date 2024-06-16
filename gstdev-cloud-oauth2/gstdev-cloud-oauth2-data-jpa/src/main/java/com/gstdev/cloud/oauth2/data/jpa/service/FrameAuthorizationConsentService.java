package com.gstdev.cloud.oauth2.data.jpa.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.oauth2.data.jpa.entity.FrameAuthorizationConsent;
import com.gstdev.cloud.oauth2.data.jpa.generator.FrameAuthorizationConsentId;
import com.gstdev.cloud.oauth2.data.jpa.repository.FrameAuthorizationConsentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>Description: FrameAuthorizationConsentService </p>
 * <p>
 * 这里命名没有按照统一的习惯，主要是为了防止与 spring-authorization-server 已有类的同名而导致Bean注入失败
 *
 * @author : cc
 * @date : 2022/2/25 21:02
 */
@Service
public class FrameAuthorizationConsentService extends BaseServiceImpl<FrameAuthorizationConsent, FrameAuthorizationConsentId,FrameAuthorizationConsentRepository> {

    private static final Logger log = LoggerFactory.getLogger(FrameAuthorizationConsentService.class);

    private FrameAuthorizationConsentRepository authorizationConsentRepository;

    @Autowired
    public FrameAuthorizationConsentService(FrameAuthorizationConsentRepository authorizationConsentRepository) {
        super(authorizationConsentRepository);
        this.authorizationConsentRepository=authorizationConsentRepository;
    }

    @Override
    public FrameAuthorizationConsentRepository getRepository() {
        return this.authorizationConsentRepository;
    }

    public Optional<FrameAuthorizationConsent> findByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName) {
        Optional<FrameAuthorizationConsent> result = this.authorizationConsentRepository.findByRegisteredClientIdAndPrincipalName(registeredClientId, principalName);
        log.trace("[GstDev Cloud] |- FrameAuthorizationConsent Service findByRegisteredClientIdAndPrincipalName.");
        return result;
    }

    public void deleteByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName) {
        this.authorizationConsentRepository.deleteByRegisteredClientIdAndPrincipalName(registeredClientId, principalName);
        log.trace("[GstDev Cloud] |- FrameAuthorizationConsent Service deleteByRegisteredClientIdAndPrincipalName.");
    }
}
