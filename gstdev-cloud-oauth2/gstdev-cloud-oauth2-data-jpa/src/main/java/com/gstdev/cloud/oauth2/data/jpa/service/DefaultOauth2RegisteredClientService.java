package com.gstdev.cloud.oauth2.data.jpa.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.oauth2.data.jpa.entity.DefaultOauth2RegisteredClient;
import com.gstdev.cloud.oauth2.data.jpa.repository.HerodotusRegisteredClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>Description: HerodotusRegisteredClientService </p>
 * <p>
 * 这里命名没有按照统一的习惯，主要是为了防止与 spring-authorization-server 已有类的同名而导致Bean注入失败
 *
 * @author : cc
 * @date : 2022/2/25 21:06
 */
@Service
public class DefaultOauth2RegisteredClientService extends BaseServiceImpl<DefaultOauth2RegisteredClient, String, HerodotusRegisteredClientRepository> {

    private static final Logger log = LoggerFactory.getLogger(DefaultOauth2RegisteredClientService.class);


    @Autowired
    public DefaultOauth2RegisteredClientService(HerodotusRegisteredClientRepository registeredClientRepository) {
        super(registeredClientRepository);
    }


    public Optional<DefaultOauth2RegisteredClient> findByClientId(String clientId) {
        Optional<DefaultOauth2RegisteredClient> result = getRepository().findByClientId(clientId);
        log.trace("[GstDev Cloud] |- HerodotusRegisteredClient Service findByClientId.");
        return result;
    }
}
