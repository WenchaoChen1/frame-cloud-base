

package com.gstdev.cloud.access.sms.stamp;

import com.gstdev.cloud.access.core.constants.AccessConstants;
import com.gstdev.cloud.access.sms.properties.SmsProperties;
import com.gstdev.cloud.cache.jetcache.stamp.AbstractStampManager;
import org.dromara.hutool.core.util.RandomUtil;

/**
 * <p>Description: 手机短信验证码签章 </p>
 *
 * @author : cc
 * @date : 2021/8/26 17:44
 */
public class VerificationCodeStampManager extends AbstractStampManager<String, String> {

    private SmsProperties smsProperties;

    public VerificationCodeStampManager() {
        super(AccessConstants.CACHE_NAME_TOKEN_VERIFICATION_CODE);
    }

    public void setSmsProperties(SmsProperties smsProperties) {
        this.smsProperties = smsProperties;
    }

    @Override
    public String nextStamp(String key) {
        if (smsProperties.getSandbox()) {
            return smsProperties.getTestCode();
        } else {
            return RandomUtil.randomNumbers(smsProperties.getLength());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setExpire(smsProperties.getExpire());
    }

    public Boolean getSandbox() {
        return smsProperties.getSandbox();
    }

    public String getVerificationCodeTemplateId() {
        return smsProperties.getVerificationCodeTemplateId();
    }
}
