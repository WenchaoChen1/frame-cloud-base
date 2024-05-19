package com.gstdev.cloud.oauth2.data.jpa.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.oauth2.data.jpa.entity.FrameRegisteredClient;
import com.gstdev.cloud.oauth2.data.jpa.repository.FrameAuthorizationConsentRepository;
import com.gstdev.cloud.oauth2.data.jpa.repository.FrameAuthorizationRepository;
import com.gstdev.cloud.oauth2.data.jpa.repository.FrameRegisteredClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>Description: FrameRegisteredClientService </p>
 * <p>
 * 这里命名没有按照统一的习惯，主要是为了防止与 spring-authorization-server 已有类的同名而导致Bean注入失败
 *
 * @author : cc
 * @date : 2022/2/25 21:06
 */
@Service
public class FrameRegisteredClientService extends BaseServiceImpl<FrameRegisteredClient, String> {

    private static final Logger log = LoggerFactory.getLogger(FrameRegisteredClientService.class);

    private FrameRegisteredClientRepository registeredClientRepository;
    @Autowired
    public FrameRegisteredClientService(FrameRegisteredClientRepository registeredClientRepository) {
        super(registeredClientRepository);
        this.registeredClientRepository=registeredClientRepository;
    }
    public FrameRegisteredClientRepository getRepository() {
        return this.registeredClientRepository;
    }

    public Optional<FrameRegisteredClient> findByClientId(String clientId) {
        Optional<FrameRegisteredClient> result = getRepository().findByClientId(clientId);
        log.trace("[GstDev Cloud] |- GstDevRegisteredClient Service findByClientId.");
        return result;
    }
}
