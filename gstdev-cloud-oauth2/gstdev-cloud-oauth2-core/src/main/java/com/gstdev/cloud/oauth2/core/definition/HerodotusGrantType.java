package com.gstdev.cloud.oauth2.core.definition;

import com.gstdev.cloud.commons.constant.BaseConstants;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

/**
 * <p>Description: 自定义 Grant Type 类型 </p>
 *
 * @author : cc
 * @date : 2022/2/25 9:53
 */
public interface HerodotusGrantType {

    AuthorizationGrantType SOCIAL = new AuthorizationGrantType(BaseConstants.SOCIAL_CREDENTIALS);

    AuthorizationGrantType PASSWORD = new AuthorizationGrantType(BaseConstants.PASSWORD);
}
