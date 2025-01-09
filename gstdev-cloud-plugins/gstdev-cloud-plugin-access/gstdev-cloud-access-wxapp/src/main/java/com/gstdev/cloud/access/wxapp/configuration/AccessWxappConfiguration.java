

package com.gstdev.cloud.access.wxapp.configuration;

import com.gstdev.cloud.access.wxapp.annotation.ConditionalOnWxappEnabled;
import com.gstdev.cloud.access.wxapp.processor.WxappAccessHandler;
import com.gstdev.cloud.access.wxapp.processor.WxappLogHandler;
import com.gstdev.cloud.access.wxapp.processor.WxappProcessor;
import com.gstdev.cloud.access.wxapp.properties.WxappProperties;
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
 * <p>Description: 微信小程序后配置 </p>
 *
 * @author : cc
 * @date : 2021/3/29 9:27
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWxappEnabled
@EnableConfigurationProperties(WxappProperties.class)
public class AccessWxappConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AccessWxappConfiguration.class);

    @PostConstruct
    public void init() {
        log.debug("[Herodotus] |- SDK [Access Wxapp] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public WxappProcessor wxappProcessor(WxappProperties wxappProperties) {
        WxappProcessor wxappProcessor = new WxappProcessor();
        wxappProcessor.setWxappProperties(wxappProperties);
        wxappProcessor.setWxappLogHandler(new WxappLogHandler());
        log.trace("[Herodotus] |- Bean [Wxapp Processor] Auto Configure.");
        return wxappProcessor;
    }

    @Bean(AccountType.WECHAT_MINI_APP_HANDLER)
    @ConditionalOnBean(WxappProcessor.class)
    @ConditionalOnMissingBean
    public WxappAccessHandler wxappAccessHandler(WxappProcessor wxappProcessor) {
        WxappAccessHandler wxappAccessHandler = new WxappAccessHandler(wxappProcessor);
        log.debug("[Herodotus] |- Bean [Wxapp Access Handler] Auto Configure.");
        return wxappAccessHandler;
    }
}
