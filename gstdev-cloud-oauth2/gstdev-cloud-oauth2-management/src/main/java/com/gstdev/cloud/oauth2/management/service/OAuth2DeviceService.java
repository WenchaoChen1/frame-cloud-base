package com.gstdev.cloud.oauth2.management.service;

import com.gstdev.cloud.base.core.exception.transaction.TransactionalRollbackException;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.oauth2.data.jpa.repository.FrameRegisteredClientRepository;
import com.gstdev.cloud.oauth2.management.converter.OAuth2DeviceToRegisteredClientConverter;
import com.gstdev.cloud.oauth2.management.converter.RegisteredClientToOAuth2DeviceConverter;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Device;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Scope;
import com.gstdev.cloud.oauth2.management.repository.OAuth2DeviceRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.oidc.OidcClientRegistration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: OAuth2DeviceService </p>
 *
 * @author : cc
 * @date : 2023/5/15 16:36
 */
@Service
public class OAuth2DeviceService extends BaseServiceImpl<OAuth2Device, String> {

    private static final Logger log = LoggerFactory.getLogger(OAuth2ApplicationService.class);

    private final RegisteredClientRepository registeredClientRepository;
    private final FrameRegisteredClientRepository frameRegisteredClientRepository;
    private OAuth2DeviceRepository deviceRepository;
    private final Converter<OAuth2Device, RegisteredClient> oauth2DeviceToRegisteredClientConverter;
    private final Converter<RegisteredClient, OAuth2Device> registeredClientToOAuth2DeviceConverter;

    public OAuth2DeviceService(RegisteredClientRepository registeredClientRepository, FrameRegisteredClientRepository frameRegisteredClientRepository, OAuth2DeviceRepository deviceRepository, OAuth2ScopeService scopeService) {
        super(deviceRepository);
        this.registeredClientRepository = registeredClientRepository;
        this.frameRegisteredClientRepository = frameRegisteredClientRepository;
        this.oauth2DeviceToRegisteredClientConverter = new OAuth2DeviceToRegisteredClientConverter();
        this.registeredClientToOAuth2DeviceConverter = new RegisteredClientToOAuth2DeviceConverter(scopeService);
    }


    @Transactional(rollbackFor = TransactionalRollbackException.class)
    @Override
    public OAuth2Device saveAndFlush(OAuth2Device entity) {
        OAuth2Device device = super.saveAndFlush(entity);
        if (ObjectUtils.isNotEmpty(device)) {
            registeredClientRepository.save(oauth2DeviceToRegisteredClientConverter.convert(device));
            return device;
        } else {
            log.error("[GstDev Cloud] |- OAuth2DeviceService saveOrUpdate error, rollback data!");
            throw new NullPointerException("save or update OAuth2DeviceService failed");
        }
    }

    @Transactional(rollbackFor = TransactionalRollbackException.class)
    @Override
    public void deleteById(String id) {
        super.deleteById(id);
        frameRegisteredClientRepository.deleteById(id);
    }

    @Transactional(rollbackFor = TransactionalRollbackException.class)
    public OAuth2Device authorize(String deviceId, String[] scopeIds) {

        Set<OAuth2Scope> scopes = new HashSet<>();
        for (String scopeId : scopeIds) {
            OAuth2Scope scope = new OAuth2Scope();
            scope.setScopeId(scopeId);
            scopes.add(scope);
        }

        OAuth2Device oldDevice = findById(deviceId);
        oldDevice.setScopes(scopes);

        return saveAndFlush(oldDevice);
    }

    /**
     * 客户端自动注册是将信息存储在 oauth2_registered_client 中。
     * 为了方便管理，将该条数据同步至 oauth2_device 表中。
     *
     * @param oidcClientRegistration {@link OidcClientRegistration}
     * @return 是否同步成功
     */
    public boolean sync(OidcClientRegistration oidcClientRegistration) {
        RegisteredClient registeredClient = registeredClientRepository.findByClientId(oidcClientRegistration.getClientId());

        if (ObjectUtils.isNotEmpty(registeredClient)) {
            OAuth2Device oauth2Device = registeredClientToOAuth2DeviceConverter.convert(registeredClient);
            if (ObjectUtils.isNotEmpty(oauth2Device)) {
                OAuth2Device result = deviceRepository.save(oauth2Device);
                return ObjectUtils.isNotEmpty(result);
            }
        }
        return false;
    }

    public boolean activate(String clientId, boolean isActivated) {
        int result = deviceRepository.activate(clientId, isActivated);
        return result != 0;
    }
}
