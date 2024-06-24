package com.gstdev.cloud.base.core.utils;

import com.gstdev.cloud.base.definition.domain.oauth2.PrincipalDetails;
import org.apache.commons.lang3.ObjectUtils;
import org.dromara.hutool.core.bean.BeanUtil;
import org.dromara.hutool.core.bean.copier.CopyOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;


public class SecurityUserDetailUtils {

    public static final String PREFIX_ROLE = "ROLE_";
    public static final String PREFIX_SCOPE = "SCOPE_";
    private static final Logger log = LoggerFactory.getLogger(SecurityUserDetailUtils.class);

    public static SecurityContext getSecurityContext() {
        return SecurityContextHolder.getContext();
    }

    public static Authentication getAuthentication() {
        return getSecurityContext().getAuthentication();
    }

    public static boolean isAuthenticated() {
        return ObjectUtils.isNotEmpty(getAuthentication()) && getAuthentication().isAuthenticated();
    }

    public static Object getDetails() {
        return getAuthentication().getDetails();
    }

    /**
     * 获取认证用户信息
     * <p>
     * 该方法仅能获取有限用户信息。从实用角度建议使用本系统提供的其它获取用户方式。
     *
     * @return 自定义 UserDetails {@link PrincipalDetails}
     */
    @SuppressWarnings("unchecked")
    public static PrincipalDetails getPrincipal() {
        if (isAuthenticated()) {
            Authentication authentication = getAuthentication();
            if (authentication.getPrincipal() instanceof PrincipalDetails) {
                return (PrincipalDetails) authentication.getPrincipal();
            }
//            if (authentication.getPrincipal() instanceof OAuth2IntrospectionAuthenticatedPrincipal introspectionPrincipal) {
//                PrincipalDetails principalDetails = new PrincipalDetails();
//                principalDetails.setUserId(introspectionPrincipal.getId());
//
//                principalDetails.setUsername(introspectionPrincipal.getName());
//                return principalDetails;
////               return new DefaultSecurityUser(null, introspectionPrincipal.getUsername(), null, introspectionPrincipal.getAuthorities());
//            }
            if (authentication.getPrincipal() instanceof Map) {
                Map<String, Object> principal = (Map<String, Object>) authentication.getPrincipal();
                return BeanUtil.toBean(principal, PrincipalDetails.class, new CopyOptions());
            }
            if (authentication.getPrincipal() instanceof String && authentication.getPrincipal().equals("anonymousUser")) {
                return null;
            }
        }

        return null;
    }

    public static String getUsername() {
        PrincipalDetails user = getPrincipal();
        if (user != null) {
            return user.getUsername();
        }
        return null;
    }


    public static String getUserId() {
        PrincipalDetails details = getPrincipal();
        if (ObjectUtils.isNotEmpty(details)) {
            return details.getUserId();
        }
        return null;
    }
    public static String getAccountId() {
        PrincipalDetails details = getPrincipal();
        if (ObjectUtils.isNotEmpty(details)) {
            return details.getAccountId();
        }
        return null;
    }

//    public static String wellFormRolePrefix(String content) {
//        return wellFormPrefix(content, PREFIX_ROLE);
//    }
//
//    public static String wellFormPrefix(String content, String prefix) {
//        if (StringUtils.startsWith(content, prefix)) {
//            return content;
//        } else {
//            return prefix + content;
//        }
//    }

}
