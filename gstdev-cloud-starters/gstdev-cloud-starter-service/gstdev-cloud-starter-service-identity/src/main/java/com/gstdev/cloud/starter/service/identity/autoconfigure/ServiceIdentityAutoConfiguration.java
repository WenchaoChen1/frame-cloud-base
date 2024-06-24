package com.gstdev.cloud.starter.service.identity.autoconfigure;

import com.gstdev.cloud.service.identity.autoconfigure.OAuth2AuthorizationServerAutoConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({
        OAuth2AuthorizationServerAutoConfiguration.class
})
public class ServiceIdentityAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(ServiceIdentityAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- Module [Service Identity Server Starter] Auto Configure.");
    }
}
