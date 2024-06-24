package com.gstdev.cloud.service.identity.service;

import com.gstdev.cloud.base.core.exception.transaction.TransactionalRollbackException;
import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.oauth2.data.jpa.repository.FrameRegisteredClientRepository;
import com.gstdev.cloud.service.identity.domain.converter.OAuth2ApplicationToRegisteredClientConverter;
import com.gstdev.cloud.service.identity.domain.entity.OAuth2Application;
import com.gstdev.cloud.service.identity.domain.entity.OAuth2Scope;
import com.gstdev.cloud.service.identity.domain.pojo.application.ApplicationManageAssignedScopeIO;
import com.gstdev.cloud.service.identity.repository.OAuth2ApplicationRepository;
import com.gstdev.cloud.service.identity.repository.OAuth2ScopeRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>Description: OAuth2ApplicationService </p>
 *
 * @author : cc
 * @date : 2022/3/1 18:06
 */
@Service
public class OAuth2ApplicationService extends BaseServiceImpl<OAuth2Application, String, OAuth2ApplicationRepository> implements BaseService<OAuth2Application, String> {

    private static final Logger log = LoggerFactory.getLogger(OAuth2ApplicationService.class);

    private final RegisteredClientRepository registeredClientRepository;
    private final FrameRegisteredClientRepository frameRegisteredClientRepository;
    private final Converter<OAuth2Application, RegisteredClient> objectConverter;
    private OAuth2ApplicationRepository applicationRepository;
    private OAuth2ScopeRepository oAuth2ScopeRepository;

    public OAuth2ApplicationService(RegisteredClientRepository registeredClientRepository, FrameRegisteredClientRepository frameRegisteredClientRepository, OAuth2ApplicationRepository applicationRepository, OAuth2ScopeRepository oAuth2ScopeRepository) {
        super(applicationRepository);
        this.registeredClientRepository = registeredClientRepository;
        this.frameRegisteredClientRepository = frameRegisteredClientRepository;
        this.applicationRepository = applicationRepository;
        this.objectConverter = new OAuth2ApplicationToRegisteredClientConverter();
        this.oAuth2ScopeRepository = oAuth2ScopeRepository;
    }

    @Override
    public OAuth2ApplicationRepository getRepository() {
        return applicationRepository;
    }

    @Override
    public OAuth2Application saveAndFlush(OAuth2Application entity) {
        OAuth2Application application = super.saveAndFlush(entity);
        if (ObjectUtils.isNotEmpty(application)) {
            registeredClientRepository.save(objectConverter.convert(application));
            log.debug("[GstDev Cloud] |- OAuth2ApplicationService saveOrUpdate.");
            return application;
        } else {
            log.error("[GstDev Cloud] |- OAuth2ApplicationService saveOrUpdate error, rollback data!");
            throw new NullPointerException("save or update OAuth2Application failed");
        }
    }

    @Transactional(rollbackFor = TransactionalRollbackException.class)
    @Override
    public void deleteById(String id) {
        super.deleteById(id);
        frameRegisteredClientRepository.deleteById(id);
    }

    @Transactional(rollbackFor = TransactionalRollbackException.class)
    public OAuth2Application authorize(String applicationId, String[] scopeIds) {

        Set<OAuth2Scope> scopes = new HashSet<>();
        for (String scopeId : scopeIds) {
            OAuth2Scope scope = new OAuth2Scope();
            scope.setScopeId(scopeId);
            scopes.add(scope);
        }

        OAuth2Application oldApplication = findById(applicationId);
        oldApplication.setScopes(scopes);

        return saveAndFlush(oldApplication);
    }

    public OAuth2Application findByClientId(String clientId) {
        return applicationRepository.findByClientId(clientId);
    }

    @Transactional
    public void updateApplicationManageAssignedScope(ApplicationManageAssignedScopeIO applicationManageAssignedScopeIO) {
        OAuth2Application application = findById(applicationManageAssignedScopeIO.getApplicationId());
        Set<OAuth2Scope> scopes = new HashSet<>();
        for (String scopeId : applicationManageAssignedScopeIO.getScopeIds()) {
            Optional<OAuth2Scope> scope = oAuth2ScopeRepository.findById(scopeId);
            scope.ifPresent(scopes::add);
        }
        application.setScopes(scopes);
        saveAndFlush(application);
    }

    public Set<String> getApplicationScopeIdByApplicationId(String id) {
        return findById(id).getScopes().stream().map(OAuth2Scope::getScopeId).collect(Collectors.toSet());
    }
}
