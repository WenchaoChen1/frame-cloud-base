

package com.gstdev.cloud.access.sms.configuration;

import com.gstdev.cloud.access.sms.annotation.ConditionalOnSmsEnabled;
import com.gstdev.cloud.access.sms.processor.PhoneNumberAccessHandler;
import com.gstdev.cloud.access.sms.properties.SmsProperties;
import com.gstdev.cloud.access.sms.stamp.VerificationCodeStampManager;
import com.gstdev.cloud.base.core.enums.AccountType;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 发送短信统一配置 </p>
 *
 * @author : cc
 * @date : 2021/5/25 12:03
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnSmsEnabled
@EnableConfigurationProperties({SmsProperties.class})
public class AccessSmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AccessSmsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- SDK [Access SMS] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public VerificationCodeStampManager verificationCodeStampManager(SmsProperties smsProperties) {
        VerificationCodeStampManager verificationCodeStampManager = new VerificationCodeStampManager();
        verificationCodeStampManager.setSmsProperties(smsProperties);
        log.trace("[GstDev Cloud] |- Bean [Verification Code Stamp Manager] Auto Configure.");
        return verificationCodeStampManager;
    }

    @Bean(AccountType.PHONE_NUMBER_HANDLER)
    public PhoneNumberAccessHandler phoneNumberAccessHandler(VerificationCodeStampManager verificationCodeStampManager) {
        PhoneNumberAccessHandler phoneNumberAuthenticationHandler = new PhoneNumberAccessHandler(verificationCodeStampManager);
        log.trace("[GstDev Cloud] |- Bean [Phone Number SignIn Handler] Auto Configure.");
        return phoneNumberAuthenticationHandler;
    }
}
