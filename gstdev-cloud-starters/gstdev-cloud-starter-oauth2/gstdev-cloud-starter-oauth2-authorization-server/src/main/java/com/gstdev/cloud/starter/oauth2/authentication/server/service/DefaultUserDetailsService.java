//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev Cloud <support@gstdev.com>
//// Copyright (c) 2022-2025 gstdev.com
////
//// ====================================================
//
//package com.gstdev.cloud.starter.oauth2.authentication.server.service;
//
//import cn.hutool.core.util.ObjectUtil;
//import com.gstdev.cloud.base.definition.domain.oauth2.AccessPrincipal;
//import com.gstdev.cloud.oauth2.authorization.server.utils.OAuth2EndpointUtils;
//import com.gstdev.cloud.oauth2.core.definition.service.EnhanceUserDetailsService;
//import com.gstdev.cloud.starter.oauth2.authentication.server.model.UserAuth;
//import jakarta.annotation.Resource;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
//import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//
//public class DefaultUserDetailsService implements EnhanceUserDetailsService {
//
//  @Resource
//  private UserDetailService userDetailService;
//
//  @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    UserAuth userAuth = userDetailService.loadUserByUsername(username);
//
//    if (ObjectUtil.isNull(userAuth) || ObjectUtil.isNull(userAuth.getUsername())) {
//      OAuth2EndpointUtils.throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.USERNAME, OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
//    }
//
//    return new User(userAuth.getUsername(), userAuth.getPassword(), getAuthorities(new ArrayList<>(List.of("USER"))));
//  }
//
//  private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
//    if (roles == null) {
//      return Collections.emptyList();
//    }
//
//    List<GrantedAuthority> authorities = new ArrayList<>();
//    for (String role : roles) {
//      authorities.add(new SimpleGrantedAuthority(role));
//    }
//
//    return authorities;
//  }
//
//  @Override
//  public UserDetails loadUserBySocial(String source, AccessPrincipal accessPrincipal) throws AuthenticationException {
//    return null;
//  }
//
//  @Override
//  public HerodotusUser loadHerodotusUserByUsername(String username) throws UsernameNotFoundException {
//    return null;
//  }
//}
