package com.gstdev.cloud.oauth2.data.jpa.service;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.oauth2.data.jpa.entity.HerodotusRegisteredClient;
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
public class HerodotusRegisteredClientService extends BaseService<HerodotusRegisteredClient, String> {

    private static final Logger log = LoggerFactory.getLogger(HerodotusRegisteredClientService.class);

    private final HerodotusRegisteredClientRepository registeredClientRepository;

    @Autowired
    public HerodotusRegisteredClientService(HerodotusRegisteredClientRepository registeredClientRepository) {
        this.registeredClientRepository = registeredClientRepository;
    }

    @Override
    public BaseRepository<HerodotusRegisteredClient, String> getRepository() {
        return registeredClientRepository;
    }

    public Optional<HerodotusRegisteredClient> findByClientId(String clientId) {
        Optional<HerodotusRegisteredClient> result = this.registeredClientRepository.findByClientId(clientId);
        log.trace("[GstDev Cloud] |- HerodotusRegisteredClient Service findByClientId.");
        return result;
    }
}
