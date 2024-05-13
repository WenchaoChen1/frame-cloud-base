package com.gstdev.cloud.rest.protect.configuration;

import com.gstdev.cloud.rest.condition.properties.CryptoProperties;
import com.gstdev.cloud.rest.core.definition.crypto.AsymmetricCryptoProcessor;
import com.gstdev.cloud.rest.core.definition.crypto.SymmetricCryptoProcessor;
import com.gstdev.cloud.rest.protect.crypto.enhance.DecryptRequestBodyAdvice;
import com.gstdev.cloud.rest.protect.crypto.enhance.DecryptRequestParamMapResolver;
import com.gstdev.cloud.rest.protect.crypto.enhance.DecryptRequestParamResolver;
import com.gstdev.cloud.rest.protect.crypto.enhance.EncryptResponseBodyAdvice;
import com.gstdev.cloud.rest.protect.crypto.processor.HttpCryptoProcessor;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: Rest 加密配置 </p>
 *
 * @author : cc
 * @date : 2022/1/14 21:11
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CryptoProperties.class)
@Import({
    CryptoStrategyConfiguration.class,
})
public class HttpCryptoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(HttpCryptoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- SDK [Protect Http Crypto] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    @DependsOn("jetCacheCreateCacheFactory")
    public HttpCryptoProcessor httpCryptoProcessor(AsymmetricCryptoProcessor asymmetricCryptoProcessor, SymmetricCryptoProcessor symmetricCryptoProcessor) {
        HttpCryptoProcessor httpCryptoProcessor = new HttpCryptoProcessor(asymmetricCryptoProcessor, symmetricCryptoProcessor);
        log.trace("[GstDev Cloud] |- Bean [Interface Crypto Processor] Auto Configure.");
        return httpCryptoProcessor;
    }

    @Bean
    @ConditionalOnClass(HttpCryptoProcessor.class)
    @ConditionalOnMissingBean
    public DecryptRequestBodyAdvice decryptRequestBodyAdvice(HttpCryptoProcessor httpCryptoProcessor) {
        DecryptRequestBodyAdvice decryptRequestBodyAdvice = new DecryptRequestBodyAdvice();
        decryptRequestBodyAdvice.setInterfaceCryptoProcessor(httpCryptoProcessor);
        log.trace("[GstDev Cloud] |- Bean [Decrypt Request Body Advice] Auto Configure.");
        return decryptRequestBodyAdvice;
    }

    @Bean
    @ConditionalOnClass(HttpCryptoProcessor.class)
    @ConditionalOnMissingBean
    public EncryptResponseBodyAdvice encryptResponseBodyAdvice(HttpCryptoProcessor httpCryptoProcessor) {
        EncryptResponseBodyAdvice encryptResponseBodyAdvice = new EncryptResponseBodyAdvice();
        encryptResponseBodyAdvice.setInterfaceCryptoProcessor(httpCryptoProcessor);
        log.trace("[GstDev Cloud] |- Bean [Encrypt Response Body Advice] Auto Configure.");
        return encryptResponseBodyAdvice;
    }

    @Bean
    @ConditionalOnClass(HttpCryptoProcessor.class)
    @ConditionalOnMissingBean
    public DecryptRequestParamMapResolver decryptRequestParamStringResolver(HttpCryptoProcessor httpCryptoProcessor) {
        DecryptRequestParamMapResolver decryptRequestParamMapResolver = new DecryptRequestParamMapResolver();
        decryptRequestParamMapResolver.setInterfaceCryptoProcessor(httpCryptoProcessor);
        log.trace("[GstDev Cloud] |- Bean [Decrypt Request Param Map Resolver] Auto Configure.");
        return decryptRequestParamMapResolver;
    }

    @Bean
    @ConditionalOnClass(HttpCryptoProcessor.class)
    @ConditionalOnMissingBean
    public DecryptRequestParamResolver decryptRequestParamResolver(HttpCryptoProcessor httpCryptoProcessor) {
        DecryptRequestParamResolver decryptRequestParamResolver = new DecryptRequestParamResolver();
        decryptRequestParamResolver.setInterfaceCryptoProcessor(httpCryptoProcessor);
        log.trace("[GstDev Cloud] |- Bean [Decrypt Request Param Resolver] Auto Configure.");
        return decryptRequestParamResolver;
    }
}
