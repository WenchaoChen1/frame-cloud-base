package cn.herodotus.engine.rest.service.configuration;

import cn.herodotus.engine.message.core.logic.strategy.RequestMappingScanEventManager;
import cn.herodotus.engine.rest.condition.annotation.ConditionalOnScanEnabled;
import cn.herodotus.engine.rest.service.processor.RequestMappingScanner;
import cn.herodotus.engine.rest.condition.properties.ScanProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 接口扫描配置 </p>
 *
 * @author : cc
 * @date : 2022/1/16 18:40
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(RequestMappingScanEventManager.class)
@ConditionalOnScanEnabled
@EnableConfigurationProperties(ScanProperties.class)
public class RestScanConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RestScanConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- SDK [Rest Scan] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public RequestMappingScanner requestMappingScanner(ScanProperties scanProperties, RequestMappingScanEventManager requestMappingScanManager) {
        RequestMappingScanner requestMappingScanner = new RequestMappingScanner(scanProperties, requestMappingScanManager);
        log.trace("[GstDev Cloud] |- Bean [Request Mapping Scanner] Auto Configure.");
        return requestMappingScanner;
    }
}
