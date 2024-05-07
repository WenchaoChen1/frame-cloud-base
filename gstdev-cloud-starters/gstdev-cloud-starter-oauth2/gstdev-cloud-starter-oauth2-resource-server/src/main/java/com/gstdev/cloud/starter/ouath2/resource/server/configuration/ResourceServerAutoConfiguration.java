package com.gstdev.cloud.starter.ouath2.resource.server.configuration;

import com.gstdev.cloud.oauth2.resource.server.customizer.OAuth2AuthorizeHttpRequestsConfigurerCustomer;
import com.gstdev.cloud.oauth2.resource.server.customizer.OAuth2ResourceServerConfigurerCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * <p>Description: 资源服务器配置 </p>
 *
 * @author : cc
 * @date : 2022/1/21 23:56
 */
@AutoConfiguration
@EnableWebSecurity
public class ResourceServerAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(ResourceServerAutoConfiguration.class);

    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity httpSecurity,
//            OAuth2SessionManagementConfigurerCustomer oauth2SessionManagementConfigurerCustomer,
        OAuth2AuthorizeHttpRequestsConfigurerCustomer oauth2AuthorizeHttpRequestsConfigurerCustomer,
        OAuth2ResourceServerConfigurerCustomer oauth2ResourceServerConfigurerCustomer
    ) throws Exception {

        log.debug("[GstDev Cloud] |- Bean [Resource Server Security Filter Chain] Auto Configure.");
        httpSecurity.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable);
        httpSecurity
            .authorizeHttpRequests(oauth2AuthorizeHttpRequestsConfigurerCustomer)
//                .sessionManagement(oauth2SessionManagementConfigurerCustomer)
            .oauth2ResourceServer(oauth2ResourceServerConfigurerCustomer);

        return httpSecurity.build();
    }
}
