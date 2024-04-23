package com.gstdev.cloud.oauth2.management.service;

import com.gstdev.cloud.base.core.exception.transaction.TransactionalRollbackException;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.oauth2.data.jpa.repository.HerodotusRegisteredClientRepository;
import com.gstdev.cloud.oauth2.management.converter.OAuth2ApplicationToRegisteredClientConverter;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Application;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Scope;
import com.gstdev.cloud.oauth2.management.repository.OAuth2ApplicationRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: OAuth2ApplicationService </p>
 *
 * @author : cc
 * @date : 2022/3/1 18:06
 */
@Service
public class OAuth2ApplicationService extends BaseServiceImpl<OAuth2Application, String,OAuth2ApplicationRepository> {

  private static final Logger log = LoggerFactory.getLogger(OAuth2ApplicationService.class);

  private final RegisteredClientRepository registeredClientRepository;
  private final HerodotusRegisteredClientRepository herodotusRegisteredClientRepository;
  private  OAuth2ApplicationRepository applicationRepository;
  private final Converter<OAuth2Application, RegisteredClient> objectConverter;

  public OAuth2ApplicationService(RegisteredClientRepository registeredClientRepository, RegisteredClientRepository registeredClientRepository1, HerodotusRegisteredClientRepository herodotusRegisteredClientRepository, OAuth2ApplicationRepository applicationRepository) {
      super(applicationRepository);
      this.registeredClientRepository = registeredClientRepository1;
      this.herodotusRegisteredClientRepository = herodotusRegisteredClientRepository;
    this.objectConverter = new OAuth2ApplicationToRegisteredClientConverter();
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
    herodotusRegisteredClientRepository.deleteById(id);
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
}
