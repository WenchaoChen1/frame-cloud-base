

package com.gstdev.cloud.access.sms.processor;

import com.gstdev.cloud.access.core.definition.AccessHandler;
import com.gstdev.cloud.access.core.definition.AccessResponse;
import com.gstdev.cloud.access.core.definition.AccessUserDetails;
import com.gstdev.cloud.access.core.exception.AccessIdentityVerificationFailedException;
import com.gstdev.cloud.access.sms.stamp.VerificationCodeStampManager;
import com.gstdev.cloud.base.definition.constants.BaseConstants;
import com.gstdev.cloud.base.definition.domain.oauth2.AccessPrincipal;
import com.gstdev.cloud.cache.core.exception.StampHasExpiredException;
import com.gstdev.cloud.cache.core.exception.StampMismatchException;
import com.gstdev.cloud.cache.core.exception.StampParameterIllegalException;
import org.dromara.sms4j.api.SmsBlend;
import org.dromara.sms4j.api.entity.SmsResponse;
import org.dromara.sms4j.core.factory.SmsFactory;

import java.util.LinkedHashMap;

/**
 * <p>Description: 手机短信接入处理器 </p>
 *
 * @author : cc
 * @date : 2022/1/26 11:46
 */
public class PhoneNumberAccessHandler implements AccessHandler {

    private final VerificationCodeStampManager verificationCodeStampManager;

    public PhoneNumberAccessHandler(VerificationCodeStampManager verificationCodeStampManager) {
        this.verificationCodeStampManager = verificationCodeStampManager;
    }

    @Override
    public AccessResponse preProcess(String phone, String... params) {
        String code = verificationCodeStampManager.create(phone);
        boolean result;
        if (verificationCodeStampManager.getSandbox()) {
            result = true;
        } else {
            SmsBlend smsBlend = SmsFactory.getSmsBlend();
            LinkedHashMap<String, String> message = new LinkedHashMap<>();
            message.put(BaseConstants.CODE, code);
            SmsResponse smsResponse = smsBlend.sendMessage(phone, verificationCodeStampManager.getVerificationCodeTemplateId(), message);
            result = smsResponse.isSuccess();
        }

        AccessResponse accessResponse = new AccessResponse();
        accessResponse.setSuccess(result);
        return accessResponse;
    }

    @Override
    public AccessUserDetails loadUserDetails(String source, AccessPrincipal accessPrincipal) {
        try {
            verificationCodeStampManager.check(accessPrincipal.getMobile(), accessPrincipal.getCode());

            AccessUserDetails accessUserDetails = new AccessUserDetails();
            accessUserDetails.setUuid(accessPrincipal.getMobile());
            accessUserDetails.setPhoneNumber(accessPrincipal.getMobile());
            accessUserDetails.setUsername(accessPrincipal.getMobile());
            accessUserDetails.setSource(source);

            verificationCodeStampManager.delete(accessPrincipal.getMobile());
            return accessUserDetails;

        } catch (StampParameterIllegalException | StampMismatchException | StampHasExpiredException e) {
            throw new AccessIdentityVerificationFailedException("Phone Verification Code Error!");
        }
    }
}
