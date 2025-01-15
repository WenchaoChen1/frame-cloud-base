package com.gstdev.cloud.service.identity.configuration;

import com.gstdev.cloud.message.core.logic.strategy.AccountStatusEventManager;
import com.gstdev.cloud.oauth2.authorization.server.stamp.LockedUserDetailsStampManager;
import com.gstdev.cloud.oauth2.authorization.server.stamp.SignInFailureLimitedStampManager;
import com.gstdev.cloud.oauth2.core.definition.service.ClientDetailsService;
import com.gstdev.cloud.service.identity.compliance.annotation.ConditionalOnAutoUnlockUserAccount;
import com.gstdev.cloud.service.identity.compliance.listener.AccountAutoEnableListener;
import com.gstdev.cloud.service.identity.compliance.listener.AuthenticationFailureListener;
import com.gstdev.cloud.service.identity.compliance.processor.FrameClientDetailsService;
import com.gstdev.cloud.service.identity.compliance.processor.OAuth2AccountStatusManager;
import com.gstdev.cloud.service.identity.service.OAuth2ApplicationService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>Description: OAuth2 应用安全合规配置 </p>
 *
 * @author : cc
 * @date : 2022/7/11 10:20
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(AccountStatusEventManager.class)
public class OAuth2ComplianceConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OAuth2ComplianceConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- SDK [OAuth2 Compliance] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2AccountStatusManager accountStatusManager(UserDetailsService userDetailsService, AccountStatusEventManager accountStatusChanger, LockedUserDetailsStampManager lockedUserDetailsStampManager) {
        OAuth2AccountStatusManager manager = new OAuth2AccountStatusManager(userDetailsService, accountStatusChanger, lockedUserDetailsStampManager);
        log.trace("[GstDev Cloud] |- Bean [OAuth2 Account Status Manager] Auto Configure.");
        return manager;
    }
    @Bean
    @ConditionalOnMissingBean
    public ClientDetailsService clientDetailsService(OAuth2ApplicationService applicationService) {
        FrameClientDetailsService frameClientDetailsService = new FrameClientDetailsService(applicationService);
        log.trace("[GstDev Cloud] |- Bean [OAuth2 Client Details Service] Auto Configure.");
        return frameClientDetailsService;
    }

    @Bean
    @ConditionalOnAutoUnlockUserAccount
    @ConditionalOnMissingBean
    public AccountAutoEnableListener accountLockStatusListener(RedisMessageListenerContainer redisMessageListenerContainer, OAuth2AccountStatusManager accountStatusManager) {
        AccountAutoEnableListener listener = new AccountAutoEnableListener(redisMessageListenerContainer, accountStatusManager);
        log.trace("[GstDev Cloud] |- Bean [OAuth2 Account Lock Status Listener] Auto Configure.");
        return listener;
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationFailureListener authenticationFailureListener(SignInFailureLimitedStampManager stampManager, OAuth2AccountStatusManager accountLockService) {
        AuthenticationFailureListener listener = new AuthenticationFailureListener(stampManager, accountLockService);
        log.trace("[GstDev Cloud] |- Bean [OAuth2 Authentication Failure Listener] Auto Configure.");
        return listener;
    }
}
