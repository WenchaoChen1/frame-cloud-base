// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.authentication.provider;

import com.gstdev.cloud.captcha.core.dto.Verification;
import com.gstdev.cloud.captcha.core.exception.CaptchaHasExpiredException;
import com.gstdev.cloud.captcha.core.exception.CaptchaIsEmptyException;
import com.gstdev.cloud.captcha.core.exception.CaptchaMismatchException;
import com.gstdev.cloud.captcha.core.exception.CaptchaParameterIllegalException;
import com.gstdev.cloud.captcha.core.processor.CaptchaRendererFactory;
import com.gstdev.cloud.oauth2.core.definition.details.FormLoginWebAuthenticationDetails;
import com.gstdev.cloud.oauth2.core.exception.OAuth2CaptchaArgumentIllegalException;
import com.gstdev.cloud.oauth2.core.exception.OAuth2CaptchaHasExpiredException;
import com.gstdev.cloud.oauth2.core.exception.OAuth2CaptchaIsEmptyException;
import com.gstdev.cloud.oauth2.core.exception.OAuth2CaptchaMismatchException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>Description: OAuth2 (Security) 表单登录 Provider </p>
 * <p>
 * 扩展的OAuth2表单登录Provider，以支持表单登录的验证码
 *
 * @author : cc
 * @date : 2022/4/12 10:21
 * @see DaoAuthenticationProvider
 */
public class OAuth2FormLoginAuthenticationProvider extends DaoAuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(OAuth2FormLoginAuthenticationProvider.class);

    private final CaptchaRendererFactory captchaRendererFactory;

    public OAuth2FormLoginAuthenticationProvider(CaptchaRendererFactory captchaRendererFactory) {
        super();
        this.captchaRendererFactory = captchaRendererFactory;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Object details = authentication.getDetails();

        if (ObjectUtils.isNotEmpty(details) && details instanceof FormLoginWebAuthenticationDetails formLoginWebAuthenticationDetails) {

            if (!formLoginWebAuthenticationDetails.getClosed()) {

                String code = formLoginWebAuthenticationDetails.getCode();
                String category = formLoginWebAuthenticationDetails.getCategory();
                String identity = formLoginWebAuthenticationDetails.getIdentity();

                if (StringUtils.isBlank(code)) {
                    throw new OAuth2CaptchaIsEmptyException("Captcha is empty.");
                }

                try {
                    Verification verification = new Verification();
                    verification.setCharacters(code);
                    verification.setCategory(category);
                    verification.setIdentity(identity);
                    captchaRendererFactory.verify(verification);
                } catch (CaptchaParameterIllegalException e) {
                    throw new OAuth2CaptchaArgumentIllegalException("Captcha argument is illegal!");
                } catch (CaptchaHasExpiredException e) {
                    throw new OAuth2CaptchaHasExpiredException("Captcha is expired!");
                } catch (CaptchaMismatchException e) {
                    throw new OAuth2CaptchaMismatchException("Captcha is mismatch!");
                } catch (CaptchaIsEmptyException e) {
                    throw new OAuth2CaptchaIsEmptyException("Captcha is empty!");
                }
            }
        }

        super.additionalAuthenticationChecks(userDetails, authentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //返回true后才会执行上面的authenticate方法,这步能确保authentication能正确转换类型
        boolean supports = (OAuth2FormLoginAuthenticationToken.class.isAssignableFrom(authentication));
        log.trace("[GstDev Cloud] |- Form Login Authentication is supports! [{}]", supports);
        return supports;
    }
}
