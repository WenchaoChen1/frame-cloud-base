

package com.gstdev.cloud.access.sms.properties;

import com.gstdev.cloud.access.core.constants.AccessConstants;
import com.google.common.base.MoreObjects;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * <p>Description: 短信验证码配置属性 </p>
 *
 * @author : cc
 * @date : 2021/5/26 17:02
 */
@ConfigurationProperties(prefix = AccessConstants.PROPERTY_PREFIX_SMS)
public class SmsProperties {

    /**
     * 是否开启
     */
    private Boolean enabled;

    /**
     * 启用短信沙盒测试模式
     */
    private Boolean sandbox = false;

    /**
     * 短信沙盒测试模式中，创建的默认验证码。
     */
    private String testCode = "123456";

    /**
     * 验证码短信模版名称
     */
    private String verificationCodeTemplateId = "VERIFICATION_CODE";

    /**
     * 超时时长，默认5分钟
     */
    private Duration expire = Duration.ofMinutes(5);

    /**
     * 手机验证码长度，默认为6位数
     */
    private int length = 6;

    public Duration getExpire() {
        return expire;
    }

    public void setExpire(Duration expire) {
        this.expire = expire;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getSandbox() {
        return sandbox;
    }

    public void setSandbox(Boolean sandbox) {
        this.sandbox = sandbox;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getVerificationCodeTemplateId() {
        return verificationCodeTemplateId;
    }

    public void setVerificationCodeTemplateId(String verificationCodeTemplateId) {
        this.verificationCodeTemplateId = verificationCodeTemplateId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("enabled", enabled)
                .add("sandbox", sandbox)
                .add("testCode", testCode)
                .add("verificationCodeTemplateId", verificationCodeTemplateId)
                .add("expire", expire)
                .add("length", length)
                .toString();
    }
}
