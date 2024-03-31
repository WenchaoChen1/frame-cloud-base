
package com.gstdev.cloud.commons.ass.core.support;

import com.gstdev.cloud.commons.ass.definition.domain.oauth2.PrincipalDetails;

/**
 * <p>Description: 身份信息解析器 </p>
 *
 * @author : cc
 * @date : 2022/12/28 0:08
 */
public interface BearerTokenResolver {

    PrincipalDetails resolve(String token);
}
