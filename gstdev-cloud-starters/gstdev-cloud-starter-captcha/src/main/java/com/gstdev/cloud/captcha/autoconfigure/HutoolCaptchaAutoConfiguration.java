package com.gstdev.cloud.captcha.autoconfigure;

import com.gstdev.cloud.cache.jetcache.autoconfigure.CacheJetCacheAutoConfiguration;
import com.gstdev.cloud.captcha.core.definition.enums.CaptchaCategory;
import com.gstdev.cloud.captcha.core.provider.ResourceProvider;
import com.gstdev.cloud.captcha.hutool.renderer.CircleCaptchaRenderer;
import com.gstdev.cloud.captcha.hutool.renderer.GifCaptchaRenderer;
import com.gstdev.cloud.captcha.hutool.renderer.LineCaptchaRenderer;
import com.gstdev.cloud.captcha.hutool.renderer.ShearCaptchaRenderer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

/**
 * <p>Description: Hutool 验证码配置 </p>
 *
 * @author : cc
 * @date : 2022/1/18 20:57
 */
@AutoConfiguration(after = CaptchaAutoConfiguration.class)
public class HutoolCaptchaAutoConfiguration {

  private static final Logger log = LoggerFactory.getLogger(HutoolCaptchaAutoConfiguration.class);

  @PostConstruct
  public void postConstruct() {
    log.info("[GstDev Cloud] |- Module [Captcha Hutool] Auto Configure.");
  }

  @Bean(CaptchaCategory.HUTOOL_LINE_CAPTCHA)
  @ConditionalOnBean(ResourceProvider.class)
  @DependsOn("jetCacheCreateCacheFactory")
  public LineCaptchaRenderer lineCaptchaRenderer(ResourceProvider resourceProvider) {
    LineCaptchaRenderer lineCaptchaRenderer = new LineCaptchaRenderer();
    lineCaptchaRenderer.setResourceProvider(resourceProvider);
    log.trace("[GstDev Cloud] |- Bean [Hutool Line Captcha Renderer] Auto Configure.");
    return lineCaptchaRenderer;
  }

  @Bean(CaptchaCategory.HUTOOL_CIRCLE_CAPTCHA)
  @ConditionalOnBean(ResourceProvider.class)
  @DependsOn("jetCacheCreateCacheFactory")
  public CircleCaptchaRenderer circleCaptchaRenderer(ResourceProvider resourceProvider) {
    CircleCaptchaRenderer circleCaptchaRenderer = new CircleCaptchaRenderer();
    circleCaptchaRenderer.setResourceProvider(resourceProvider);
    log.trace("[GstDev Cloud] |- Bean [Hutool Circle Captcha Renderer] Auto Configure.");
    return circleCaptchaRenderer;
  }

  @Bean(CaptchaCategory.HUTOOL_SHEAR_CAPTCHA)
  @ConditionalOnBean(ResourceProvider.class)
  @DependsOn("jetCacheCreateCacheFactory")
  public ShearCaptchaRenderer shearCaptchaRenderer(ResourceProvider resourceProvider) {
    ShearCaptchaRenderer shearCaptchaRenderer = new ShearCaptchaRenderer();
    shearCaptchaRenderer.setResourceProvider(resourceProvider);
    log.trace("[GstDev Cloud] |- Bean [Hutool Shear Captcha Renderer] Auto Configure.");
    return shearCaptchaRenderer;
  }

  @Bean(CaptchaCategory.HUTOOL_GIF_CAPTCHA)
  @ConditionalOnBean(ResourceProvider.class)
  @DependsOn("jetCacheCreateCacheFactory")
  public GifCaptchaRenderer gifCaptchaRenderer(ResourceProvider resourceProvider) {
    GifCaptchaRenderer gifCaptchaRenderer = new GifCaptchaRenderer();
    gifCaptchaRenderer.setResourceProvider(resourceProvider);
    log.trace("[GstDev Cloud] |- Bean [Hutool Gif Captcha Renderer] Auto Configure.");
    return gifCaptchaRenderer;
  }
}
