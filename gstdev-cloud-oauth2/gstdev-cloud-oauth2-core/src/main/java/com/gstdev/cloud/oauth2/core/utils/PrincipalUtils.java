//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev <support@gstdev.com>
//// Copyright (c) 2020-2025 gstdev.com
////
//// ====================================================
//package com.gstdev.cloud.oauth2.core.utils;
//
//import com.gstdev.cloud.commons.ass.definition.constants.BaseConstants;
//import com.gstdev.cloud.commons.ass.definition.domain.oauth2.PrincipalDetails;
//import com.gstdev.cloud.oauth2.core.definition.domain.HerodotusUser;
//import org.apache.commons.collections4.CollectionUtils;
//import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtClaimNames;
//
//import java.util.HashSet;
//import java.util.List;
//
///**
// * <p>Description: 身份信息工具类 </p>
// *
// * @author : cc
// * @date : 2022/12/31 12:07
// */
//public class PrincipalUtils {
//
//    public static PrincipalDetails toPrincipalDetails(HerodotusUser herodotusUser) {
//        PrincipalDetails details = new PrincipalDetails();
//        details.setOpenId(herodotusUser.getUserId());
//        details.setUsername(herodotusUser.getUsername());
//        details.setRoles(herodotusUser.getRoles());
//        details.setAvatar(herodotusUser.getAvatar());
//        details.setEmployeeId(herodotusUser.getEmployeeId());
//        return details;
//    }
//
//    public static PrincipalDetails toPrincipalDetails(OAuth2AuthenticatedPrincipal authenticatedPrincipal) {
//        PrincipalDetails details = new PrincipalDetails();
//        details.setOpenId(authenticatedPrincipal.getAttribute(BaseConstants.OPEN_ID));
//        details.setUsername(authenticatedPrincipal.getName());
//        List<String> roles = authenticatedPrincipal.getAttribute(BaseConstants.ROLES);
//        if (CollectionUtils.isNotEmpty(roles)) {
//            details.setRoles(new HashSet<>(roles));
//        }
//        details.setAvatar(authenticatedPrincipal.getAttribute(BaseConstants.AVATAR));
//        details.setEmployeeId(authenticatedPrincipal.getAttribute(BaseConstants.EMPLOYEE_ID));
//        return details;
//    }
//
//    public static PrincipalDetails toPrincipalDetails(Jwt jwt) {
//        PrincipalDetails details = new PrincipalDetails();
//        details.setOpenId(jwt.getClaimAsString(BaseConstants.OPEN_ID));
//        details.setUsername(jwt.getClaimAsString(JwtClaimNames.SUB));
//        details.setRoles(jwt.getClaim(BaseConstants.ROLES));
//        details.setAvatar(jwt.getClaimAsString(BaseConstants.AVATAR));
//        details.setEmployeeId(jwt.getClaimAsString(BaseConstants.EMPLOYEE_ID));
//        return details;
//    }
//}
