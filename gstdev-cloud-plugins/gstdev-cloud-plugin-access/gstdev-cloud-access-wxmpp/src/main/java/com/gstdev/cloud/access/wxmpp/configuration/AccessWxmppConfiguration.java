

package com.gstdev.cloud.access.wxmpp.configuration;

import com.gstdev.cloud.access.wxmpp.annotation.ConditionalOnWxmppEnabled;
import com.gstdev.cloud.access.wxmpp.processor.WxmppLogHandler;
import com.gstdev.cloud.access.wxmpp.processor.WxmppProcessor;
import com.gstdev.cloud.access.wxmpp.properties.WxmppProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * <p>Description: 微信公众号配置 </p>
 *
 * @author : cc
 * @date : 2021/4/7 13:25
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWxmppEnabled
@EnableConfigurationProperties(WxmppProperties.class)
public class AccessWxmppConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AccessWxmppConfiguration.class);

    @PostConstruct
    public void init() {
        log.debug("[GstDev Cloud] |- SDK [Access Wxmpp] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public WxmppProcessor wxmppProcessor(WxmppProperties wxmppProperties, StringRedisTemplate stringRedisTemplate) {
        WxmppProcessor wxmppProcessor = new WxmppProcessor();
        wxmppProcessor.setWxmppProperties(wxmppProperties);
        wxmppProcessor.setWxmppLogHandler(new WxmppLogHandler());
        wxmppProcessor.setStringRedisTemplate(stringRedisTemplate);
        log.trace("[GstDev Cloud] |- Bean [Wxmpp Processor] Auto Configure.");
        return wxmppProcessor;
    }
}
