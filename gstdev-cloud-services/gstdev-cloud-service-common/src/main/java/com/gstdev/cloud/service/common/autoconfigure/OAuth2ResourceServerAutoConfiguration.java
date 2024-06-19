package com.gstdev.cloud.service.common.autoconfigure;


import com.gstdev.cloud.data.jpa.autoconfigure.DataJpaAutoConfiguration;
import com.gstdev.cloud.service.common.autoconfigure.metadata.RemoteSecurityMetadataSyncListener;
import com.gstdev.cloud.service.common.autoconfigure.scan.DefaultRequestMappingScanEventManager;
import com.gstdev.cloud.message.core.logic.strategy.RequestMappingScanEventManager;
import com.gstdev.cloud.oauth2.core.exception.SecurityGlobalExceptionHandler;
import com.gstdev.cloud.oauth2.resource.server.configuration.OAuth2AuthorizationConfiguration;
import com.gstdev.cloud.oauth2.resource.server.processor.SecurityMetadataSourceAnalyzer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.bus.ServiceMatcher;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <p>Description: OAuth2 资源服务器自动配置模块 </p>
 * <p>
 * 所有提供资源访问的服务（即可以作为 OAuth2 资源服务器的服务），所需的基础配置都在该模块中统一完成配置
 *
 * @author : cc
 * @date : 2023/10/28 14:22
 */
@AutoConfiguration
@EnableAsync
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@Import({OAuth2AuthorizationConfiguration.class, DataJpaAutoConfiguration.class})
@ComponentScan(basePackageClasses = SecurityGlobalExceptionHandler.class)
@RemoteApplicationEventScan({
    "com.gstdev.cloud.service.common.autoconfigure.bus"
})
public class OAuth2ResourceServerAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OAuth2ResourceServerAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- Module [OAuth2 Resource Server Starter] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public RemoteSecurityMetadataSyncListener remoteSecurityMetadataSyncListener(SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer, ServiceMatcher serviceMatcher) {
        RemoteSecurityMetadataSyncListener listener = new RemoteSecurityMetadataSyncListener(securityMetadataSourceAnalyzer, serviceMatcher);
        log.trace("[GstDev Cloud] |- Bean [Security Metadata Refresh Listener] Auto Configure.");
        return listener;
    }

    @Bean
    @ConditionalOnMissingBean
    public RequestMappingScanEventManager requestMappingScanEventManager(SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer) {
        DefaultRequestMappingScanEventManager manager = new DefaultRequestMappingScanEventManager(securityMetadataSourceAnalyzer);
        log.trace("[GstDev Cloud] |- Bean [Request Mapping Scan Manager] Auto Configure.");
        return manager;
    }
}
