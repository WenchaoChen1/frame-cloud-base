package com.gstdev.cloud.captcha.autoconfigure;


import com.gstdev.cloud.cache.jetcache.autoconfigure.CacheJetCacheAutoConfiguration;
import com.gstdev.cloud.captcha.autoconfigure.customizer.CaptchaErrorCodeMapperBuilderCustomizer;
import com.gstdev.cloud.captcha.core.processor.CaptchaRendererFactory;
import com.gstdev.cloud.captcha.core.processor.properties.CaptchaProperties;
import com.gstdev.cloud.captcha.core.provider.ResourceProvider;
import com.gstdev.cloud.base.definition.function.ErrorCodeMapperBuilderCustomizer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

/**
 * <p>Description: 验证码自动注入 </p>
 *
 * @author : cc
 * @date : 2022/1/18 21:12
 */
@AutoConfiguration(after = CacheJetCacheAutoConfiguration.class)
@EnableConfigurationProperties(CaptchaProperties.class)
public class CaptchaAutoConfiguration {

  private static final Logger log = LoggerFactory.getLogger(CaptchaAutoConfiguration.class);

  @PostConstruct
  public void postConstruct() {
    log.info("[GstDev Cloud] |- Module [Captcha Starter] Auto Configure.");
  }

  @Bean
  @ConditionalOnMissingBean
  public ResourceProvider resourceProvider(CaptchaProperties captchaProperties) {
    ResourceProvider resourceProvider = new ResourceProvider(captchaProperties);
    log.trace("[GstDev Cloud] |- Bean [Resource Provider] Auto Configure.");
    return resourceProvider;
  }

  @Bean
  @ConditionalOnMissingBean
  @DependsOn("jetCacheCreateCacheFactory")
  public CaptchaRendererFactory captchaRendererFactory() {
    CaptchaRendererFactory captchaRendererFactory = new CaptchaRendererFactory();
    log.trace("[GstDev Cloud] |- Bean [Captcha Renderer Factory] Auto Configure.");
    return captchaRendererFactory;
  }

  @Bean
  public ErrorCodeMapperBuilderCustomizer captchaErrorCodeMapperBuilderCustomizer() {
    CaptchaErrorCodeMapperBuilderCustomizer customizer = new CaptchaErrorCodeMapperBuilderCustomizer();
    log.debug("[GstDev Cloud] |- Strategy [Captcha ErrorCodeMapper Builder Customizer] Auto Configure.");
    return customizer;
  }
}
