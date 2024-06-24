package com.gstdev.cloud.captcha.autoconfigure;


import com.gstdev.cloud.captcha.core.definition.enums.CaptchaCategory;
import com.gstdev.cloud.captcha.core.provider.ResourceProvider;
import com.gstdev.cloud.captcha.graphic.renderer.*;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

/**
 * <p>Description: 图形验证码配置 </p>
 *
 * @author : cc
 * @date : 2022/1/18 20:56
 */
@AutoConfiguration(after = CaptchaAutoConfiguration.class)
public class GraphicCaptchaAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(GraphicCaptchaAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- Module [Captcha Graphic] Auto Configure.");
    }

    @Bean(CaptchaCategory.ARITHMETIC_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    @DependsOn("jetCacheCreateCacheFactory")
    public ArithmeticCaptchaRenderer arithmeticCaptchaRenderer(ResourceProvider resourceProvider) {
        ArithmeticCaptchaRenderer arithmeticCaptchaRenderer = new ArithmeticCaptchaRenderer();
        arithmeticCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[GstDev Cloud] |- Bean [Arithmetic Captcha Renderer] Auto Configure.");
        return arithmeticCaptchaRenderer;
    }

    @Bean(CaptchaCategory.CHINESE_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    @DependsOn("jetCacheCreateCacheFactory")
    public ChineseCaptchaRenderer chineseCaptchaRenderer(ResourceProvider resourceProvider) {
        ChineseCaptchaRenderer chineseCaptchaRenderer = new ChineseCaptchaRenderer();
        chineseCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[GstDev Cloud] |- Bean [Chinese Captcha Renderer] Auto Configure.");
        return chineseCaptchaRenderer;
    }

    @Bean(CaptchaCategory.CHINESE_GIF_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    @DependsOn("jetCacheCreateCacheFactory")
    public ChineseGifCaptchaRenderer chineseGifCaptchaRenderer(ResourceProvider resourceProvider) {
        ChineseGifCaptchaRenderer chineseGifCaptchaRenderer = new ChineseGifCaptchaRenderer();
        chineseGifCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[GstDev Cloud] |- Bean [Chinese Gif Captcha Renderer] Auto Configure.");
        return chineseGifCaptchaRenderer;
    }

    @Bean(CaptchaCategory.SPEC_GIF_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    @DependsOn("jetCacheCreateCacheFactory")
    public SpecGifCaptchaRenderer specGifCaptchaRenderer(ResourceProvider resourceProvider) {
        SpecGifCaptchaRenderer specGifCaptchaRenderer = new SpecGifCaptchaRenderer();
        specGifCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[GstDev Cloud] |- Bean [Spec Gif Captcha Renderer] Auto Configure.");
        return specGifCaptchaRenderer;
    }

    @Bean(CaptchaCategory.SPEC_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    @DependsOn("jetCacheCreateCacheFactory")
    public SpecCaptchaRenderer specCaptchaRenderer(ResourceProvider resourceProvider) {
        SpecCaptchaRenderer specCaptchaRenderer = new SpecCaptchaRenderer();
        specCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[GstDev Cloud] |- Bean [Spec Captcha Renderer] Auto Configure.");
        return specCaptchaRenderer;
    }
}
