

package com.gstdev.cloud.access.justauth.configuration;

import com.gstdev.cloud.access.justauth.annotation.ConditionalOnJustAuthEnabled;
import com.gstdev.cloud.access.justauth.processor.JustAuthAccessHandler;
import com.gstdev.cloud.access.justauth.processor.JustAuthProcessor;
import com.gstdev.cloud.access.justauth.properties.JustAuthProperties;
import com.gstdev.cloud.access.justauth.stamp.JustAuthStateStampManager;
import com.gstdev.cloud.base.core.enums.AccountType;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: JustAuth配置 </p>
 * <p>
 * 仅在存在herodotus.platform.social.justauth.configs配置的情况下才注入
 *
 * @author : cc
 * @date : 2021/5/22 11:25
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnJustAuthEnabled
@EnableConfigurationProperties(JustAuthProperties.class)
public class AccessJustAuthConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AccessJustAuthConfiguration.class);

    @PostConstruct
    public void init() {
        log.debug("[Herodotus] |- SDK [Access Just Auth] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public JustAuthStateStampManager justAuthStateStampManager(JustAuthProperties justAuthProperties) {
        JustAuthStateStampManager justAuthStateStampManager = new JustAuthStateStampManager();
        justAuthStateStampManager.setJustAuthProperties(justAuthProperties);
        log.trace("[Herodotus] |- Bean [Just Auth State Redis Cache] Auto Configure.");
        return justAuthStateStampManager;
    }

    @Bean
    @ConditionalOnBean(JustAuthStateStampManager.class)
    @ConditionalOnMissingBean
    public JustAuthProcessor justAuthProcessor(JustAuthStateStampManager justAuthStateStampManager, JustAuthProperties justAuthProperties) {
        JustAuthProcessor justAuthProcessor = new JustAuthProcessor();
        justAuthProcessor.setJustAuthStateRedisCache(justAuthStateStampManager);
        justAuthProcessor.setJustAuthProperties(justAuthProperties);
        log.trace("[Herodotus] |- Bean [Just Auth Request Generator] Auto Configure.");
        return justAuthProcessor;
    }

    @Bean(AccountType.JUST_AUTH_HANDLER)
    @ConditionalOnBean(JustAuthProcessor.class)
    @ConditionalOnMissingBean
    public JustAuthAccessHandler justAuthAccessHandler(JustAuthProcessor justAuthProcessor) {
        JustAuthAccessHandler justAuthAccessHandler = new JustAuthAccessHandler(justAuthProcessor);
        log.debug("[Herodotus] |- Bean [Just Auth Access Handler] Auto Configure.");
        return justAuthAccessHandler;
    }
}
