package com.gstdev.cloud.rest.autoconfigure;

import com.gstdev.cloud.rest.protect.configuration.SecureConfiguration;
//import com.gstdev.cloud.rest.protect.configuration.TenantConfiguration;
//import com.gstdev.cloud.rest.protect.secure.interceptor.AccessLimitedInterceptor;
//import com.gstdev.cloud.rest.protect.secure.interceptor.IdempotentInterceptor;
//import com.gstdev.cloud.rest.protect.tenant.MultiTenantInterceptor;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

/**
 * <p>Description: WebMvcAutoConfiguration </p>
 *
 * @author : cc
 * @date : 2020/3/4 11:00
 */
@AutoConfiguration
@Import({
        SecureConfiguration.class,
//  TenantConfiguration.class
})
@EnableWebMvc
public class RestWebMvcAutoConfiguration implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(RestWebMvcAutoConfiguration.class);

//  private final IdempotentInterceptor idempotentInterceptor;
//  private final AccessLimitedInterceptor accessLimitedInterceptor;
//  private final MultiTenantInterceptor multiTenantInterceptor;
//
//  public RestWebMvcAutoConfiguration(IdempotentInterceptor idempotentInterceptor, AccessLimitedInterceptor accessLimitedInterceptor, MultiTenantInterceptor multiTenantInterceptor) {
//    this.idempotentInterceptor = idempotentInterceptor;
//    this.accessLimitedInterceptor = accessLimitedInterceptor;
//    this.multiTenantInterceptor = multiTenantInterceptor;
//  }

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- Module [Rest WebMvc] Auto Configure.");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//    registry.addInterceptor(accessLimitedInterceptor);
//    registry.addInterceptor(idempotentInterceptor);
//    registry.addInterceptor(multiTenantInterceptor);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .resourceChain(false)
                .addResolver(new WebJarsResourceResolver());
        registry.addResourceHandler("/plugins/**")
                .addResourceLocations("classpath:/static/plugins/");
        registry.addResourceHandler("/frame/**")
                .addResourceLocations("classpath:/static/frame/");
    }

}
