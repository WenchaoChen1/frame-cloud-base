package com.gstdev.cloud.oauth2.resource.server.autoconfigure.configuration;


import com.gstdev.cloud.oauth2.resource.configuration.OAuth2AuthorizationConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: OAuth2 资源服务器自动配置模块 </p>
 *
 * 所有提供资源访问的服务（即可以作为 OAuth2 资源服务器的服务），所需的基础配置都在该模块中统一完成配置
 *
 * @author : cc
 * @date : 2023/10/28 14:22
 */
@AutoConfiguration
//@EnableAsync
//@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@Import({OAuth2AuthorizationConfiguration.class})
//@ComponentScan(basePackageClasses = SecurityGlobalExceptionHandler.class)
//@RemoteApplicationEventScan({
//        "cn.herodotus.engine.oauth2.resource.autoconfigure.bus"
//})
public class OAuth2ResourceServerAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OAuth2ResourceServerAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- Module [OAuth2 Resource Server Starter] Auto Configure.");
    }

//    @Bean
//    @ConditionalOnMissingBean
//    public RemoteSecurityMetadataSyncListener remoteSecurityMetadataSyncListener(SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer, ServiceMatcher serviceMatcher) {
//        RemoteSecurityMetadataSyncListener listener = new RemoteSecurityMetadataSyncListener(securityMetadataSourceAnalyzer, serviceMatcher);
//        log.trace("[GstDev Cloud] |- Bean [Security Metadata Refresh Listener] Auto Configure.");
//        return listener;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public RequestMappingScanEventManager requestMappingScanEventManager(SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer) {
//        DefaultRequestMappingScanEventManager manager = new DefaultRequestMappingScanEventManager(securityMetadataSourceAnalyzer);
//        log.trace("[GstDev Cloud] |- Bean [Request Mapping Scan Manager] Auto Configure.");
//        return manager;
//    }
}
