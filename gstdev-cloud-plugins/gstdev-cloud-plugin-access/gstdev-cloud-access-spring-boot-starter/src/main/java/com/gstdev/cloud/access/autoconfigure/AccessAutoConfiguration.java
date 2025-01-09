

package com.gstdev.cloud.access.autoconfigure;

import com.gstdev.cloud.access.all.configuration.AccessAllConfiguration;
import com.gstdev.cloud.access.autoconfigure.customizer.AccessErrorCodeMapperBuilderCustomizer;
import com.gstdev.cloud.base.definition.function.ErrorCodeMapperBuilderCustomizer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;


/**
 * <p>Description: 外部程序接入模块自动配置 </p>
 *
 * @author : cc
 * @date : 2022/1/26 16:23
 */
@AutoConfiguration
@Import({
        AccessAllConfiguration.class
})
public class AccessAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AccessAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Herodotus] |- Module [Access Starter] Auto Configure.");
    }

    @Bean
    public ErrorCodeMapperBuilderCustomizer accessErrorCodeMapperBuilderCustomizer() {
        AccessErrorCodeMapperBuilderCustomizer customizer = new AccessErrorCodeMapperBuilderCustomizer();
        log.debug("[Herodotus] |- Strategy [Access ErrorCodeMapper Builder Customizer] Auto Configure.");
        return customizer;
    }
}
