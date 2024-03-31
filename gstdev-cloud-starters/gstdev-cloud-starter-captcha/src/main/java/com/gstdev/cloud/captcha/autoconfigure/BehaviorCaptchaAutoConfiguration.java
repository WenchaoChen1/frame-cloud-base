package com.gstdev.cloud.captcha.autoconfigure;

import com.gstdev.cloud.cache.jetcache.autoconfigure.CacheJetCacheAutoConfiguration;
import com.gstdev.cloud.captcha.behavior.renderer.JigsawCaptchaRenderer;
import com.gstdev.cloud.captcha.behavior.renderer.WordClickCaptchaRenderer;
import com.gstdev.cloud.captcha.core.definition.enums.CaptchaCategory;
import com.gstdev.cloud.captcha.core.provider.ResourceProvider;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: 行为验证码配置 </p>
 *
 * @author : cc
 * @date : 2022/1/18 20:57
 */
@AutoConfiguration(after = CaptchaAutoConfiguration.class)
public class BehaviorCaptchaAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(BehaviorCaptchaAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- Module [Captcha Behavior] Auto Configure.");
    }

    @Bean(CaptchaCategory.JIGSAW_CAPTCHA)
    public JigsawCaptchaRenderer jigsawCaptchaRenderer(ResourceProvider resourceProvider) {
        JigsawCaptchaRenderer jigsawCaptchaRenderer = new JigsawCaptchaRenderer();
        jigsawCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[GstDev Cloud] |- Bean [Jigsaw Captcha Renderer] Auto Configure.");
        return jigsawCaptchaRenderer;
    }

    @Bean(CaptchaCategory.WORD_CLICK_CAPTCHA)
    public WordClickCaptchaRenderer wordClickCaptchaRenderer(ResourceProvider resourceProvider) {
        WordClickCaptchaRenderer wordClickCaptchaRenderer = new WordClickCaptchaRenderer();
        wordClickCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[GstDev Cloud] |- Bean [Word Click Captcha Renderer] Auto Configure.");
        return wordClickCaptchaRenderer;
    }
}
