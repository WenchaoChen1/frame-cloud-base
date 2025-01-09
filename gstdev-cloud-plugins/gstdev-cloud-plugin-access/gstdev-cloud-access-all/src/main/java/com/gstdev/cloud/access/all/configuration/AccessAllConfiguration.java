

package com.gstdev.cloud.access.all.configuration;

import com.gstdev.cloud.access.all.controller.JustAuthAccessController;
import com.gstdev.cloud.access.all.controller.PhoneNumberAccessController;
import com.gstdev.cloud.access.all.controller.WxappAccessController;
import com.gstdev.cloud.access.all.processor.AccessHandlerStrategyFactory;
import com.gstdev.cloud.access.justauth.annotation.ConditionalOnJustAuthEnabled;
import com.gstdev.cloud.access.justauth.configuration.AccessJustAuthConfiguration;
import com.gstdev.cloud.access.sms.annotation.ConditionalOnSmsEnabled;
import com.gstdev.cloud.access.sms.configuration.AccessSmsConfiguration;
import com.gstdev.cloud.access.wxapp.annotation.ConditionalOnWxappEnabled;
import com.gstdev.cloud.access.wxapp.configuration.AccessWxappConfiguration;
import com.gstdev.cloud.access.wxmpp.configuration.AccessWxmppConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: Access 业务模块配置 </p>
 *
 * @author : cc
 * @date : 2022/1/26 14:59
 */
@Configuration(proxyBeanMethods = false)
@Import({
        AccessJustAuthConfiguration.class,
        AccessSmsConfiguration.class,
        AccessWxappConfiguration.class,
        AccessWxmppConfiguration.class
})
public class AccessAllConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AccessAllConfiguration.class);

    @PostConstruct
    public void init() {
        log.debug("[Herodotus] |- SDK [Access All] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean(AccessHandlerStrategyFactory.class)
    public AccessHandlerStrategyFactory accessHandlerStrategyFactory() {
        AccessHandlerStrategyFactory accessHandlerStrategyFactory = new AccessHandlerStrategyFactory();
        log.trace("[Herodotus] |- Bean [Access Handler Strategy Factory] Auto Configure.");
        return accessHandlerStrategyFactory;
    }

    @Configuration(proxyBeanMethods = false)
    static class ControllerConfiguration {

        @PostConstruct
        public void init() {
            log.debug("[Herodotus] |- SDK [Access All Controller] Auto Configure.");
        }

        @Bean
        @ConditionalOnSmsEnabled
        @ConditionalOnMissingBean
        public PhoneNumberAccessController phoneNumberAccessController() {
            PhoneNumberAccessController phoneNumberAuthenticationController = new PhoneNumberAccessController();
            log.trace("[Herodotus] |- Bean [Phone Number Access Controller] Auto Configure.");
            return phoneNumberAuthenticationController;
        }

        @Bean
        @ConditionalOnJustAuthEnabled
        @ConditionalOnMissingBean
        public JustAuthAccessController justAuthSignInController() {
            JustAuthAccessController justAuthAuthenticationController = new JustAuthAccessController();
            log.trace("[Herodotus] |- Bean [Just Auth Access Controller] Auto Configure.");
            return justAuthAuthenticationController;
        }

        @Bean
        @ConditionalOnWxappEnabled
        @ConditionalOnMissingBean
        public WxappAccessController wxappAccessController() {
            WxappAccessController wxappAccessController = new WxappAccessController();
            log.trace("[Herodotus] |- Bean [Wxapp Access Controller] Auto Configure.");
            return wxappAccessController;
        }
    }
}
