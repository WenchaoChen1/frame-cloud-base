// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.core.utils;

import com.gstdev.cloud.base.definition.constants.BaseConstants;
import com.gstdev.cloud.base.definition.domain.oauth2.PrincipalDetails;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;

import java.util.HashSet;
import java.util.List;

/**
 * <p>Description: 身份信息工具类 </p>
 *
 * @author : cc
 * @date : 2022/12/31 12:07
 */
public class PrincipalUtils {

    public static PrincipalDetails toPrincipalDetails(DefaultSecurityUser defaultSecurityUser) {
        PrincipalDetails details = new PrincipalDetails();
        details.setOpenId(defaultSecurityUser.getUserId());
        details.setUserId(defaultSecurityUser.getUserId());
        details.setUsername(defaultSecurityUser.getUsername());
        details.setRoles(defaultSecurityUser.getRoles());
        details.setAvatar(defaultSecurityUser.getAvatar());
        details.setEmployeeId(defaultSecurityUser.getEmployeeId());
        details.setAccountId(defaultSecurityUser.getAccountId());
        details.setAccountName(defaultSecurityUser.getAccountName());
        return details;
    }

    public static PrincipalDetails toPrincipalDetails(OAuth2AuthenticatedPrincipal authenticatedPrincipal) {
        PrincipalDetails details = new PrincipalDetails();
        details.setOpenId(authenticatedPrincipal.getAttribute(BaseConstants.OPEN_ID));
        details.setUserId(authenticatedPrincipal.getAttribute(BaseConstants.OPEN_ID));
        details.setUsername(authenticatedPrincipal.getName());
        List<String> roles = authenticatedPrincipal.getAttribute(BaseConstants.ROLES);
        if (CollectionUtils.isNotEmpty(roles)) {
            details.setRoles(new HashSet<>(roles));
        }
        details.setAvatar(authenticatedPrincipal.getAttribute(BaseConstants.AVATAR));
        details.setEmployeeId(authenticatedPrincipal.getAttribute(BaseConstants.EMPLOYEE_ID));
        details.setAccountId(authenticatedPrincipal.getAttribute(BaseConstants.ACCOUNT_ID));
        details.setAccountName(authenticatedPrincipal.getAttribute(BaseConstants.ACCOUNT_NAME));
        return details;
    }

    public static PrincipalDetails toPrincipalDetails(Jwt jwt) {
        PrincipalDetails details = new PrincipalDetails();
        details.setOpenId(jwt.getClaimAsString(BaseConstants.OPEN_ID));
        details.setUserId(jwt.getClaimAsString(BaseConstants.USER_ID));
        details.setUsername(jwt.getClaimAsString(JwtClaimNames.SUB));
        details.setRoles(new HashSet<>(jwt.getClaimAsStringList(BaseConstants.ROLES)));
        details.setAvatar(jwt.getClaimAsString(BaseConstants.AVATAR));
        details.setEmployeeId(jwt.getClaimAsString(BaseConstants.EMPLOYEE_ID));
        details.setAccountId(jwt.getClaimAsString(BaseConstants.ACCOUNT_ID));
        details.setAccountName(jwt.getClaimAsString(BaseConstants.ACCOUNT_NAME));
        return details;
    }
}
