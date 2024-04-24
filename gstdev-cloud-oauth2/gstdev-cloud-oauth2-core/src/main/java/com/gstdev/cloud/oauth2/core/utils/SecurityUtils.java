// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.core.utils;

import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.bean.BeanUtil;
import org.dromara.hutool.core.bean.copier.CopyOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author gengwei.zheng
 * @date 2018-3-8
 **/
public class SecurityUtils {

  public static final String PREFIX_ROLE = "ROLE_";
  public static final String PREFIX_SCOPE = "SCOPE_";
  private static final Logger log = LoggerFactory.getLogger(SecurityUtils.class);
  private static final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

  /**
   * 密码加密
   *
   * @param password 明文密码
   * @return 已加密密码
   */
  public static String encrypt(String password) {
    return passwordEncoder.encode(password);
  }

  /**
   * 密码验证
   *
   * @param rawPassword     原始密码
   * @param encodedPassword 加密后的密码
   * @return 密码是否匹配
   */
  public static boolean matches(String rawPassword, String encodedPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }

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
   * 当用户角色发生变化，或者用户角色对应的权限发生变化，那么就从数据库中重新查询用户相关信息
   *
   * @param defaultSecurityUser 从数据库中重新查询并生成的用户信息
   */
  public static void reloadAuthority(DefaultSecurityUser defaultSecurityUser) {
    // 重新new一个token，因为Authentication中的权限是不可变的.
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
        defaultSecurityUser, defaultSecurityUser.getPassword(),
        defaultSecurityUser.getAuthorities());
    token.setDetails(getDetails());
    getSecurityContext().setAuthentication(token);
  }

  /**
   * 获取认证用户信息
   * <p>
   * 该方法仅能获取有限用户信息。从实用角度建议使用本系统提供的其它获取用户方式。
   *
   * @return 自定义 UserDetails {@link DefaultSecurityUser}
   */
  @SuppressWarnings("unchecked")
  public static DefaultSecurityUser getPrincipal() {
    if (isAuthenticated()) {
      Authentication authentication = getAuthentication();
      if (authentication.getPrincipal() instanceof OAuth2IntrospectionAuthenticatedPrincipal introspectionPrincipal) {
        return new DefaultSecurityUser(null, introspectionPrincipal.getUsername(), null, introspectionPrincipal.getAuthorities());
      }
      if (authentication.getPrincipal() instanceof DefaultSecurityUser) {
        return (DefaultSecurityUser) authentication.getPrincipal();
      }
      if (authentication.getPrincipal() instanceof Map) {
        Map<String, Object> principal = (Map<String, Object>) authentication.getPrincipal();
        return BeanUtil.toBean(principal, DefaultSecurityUser.class, new CopyOptions());
      }
    }

    return null;
  }

  public static String getUsername() {
    DefaultSecurityUser user = getPrincipal();
    if (user != null) {
      return user.getUsername();
    }
    return null;
  }

  public static DefaultSecurityUser getPrincipals() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal != null) {
      if (principal instanceof DefaultSecurityUser) {
        return (DefaultSecurityUser) principal;
      } else if (principal instanceof LinkedHashMap) {
        // TODO: zhangyu 2019/7/15 感觉还可以升级一把，不吐linkedhashmap 直接就是oauth2user
        // 2019/7/20 试验过将OAuth2UserAuthenticationConverter map<string,?>中的?强制转换成oauth2user，试验失败，问题不是很急，可以先放着
        /**
         * https://blog.csdn.net/m0_37834471/article/details/81814233
         * cn/itcraftsman/luban/auth/oauth2/OAuth2UserAuthenticationConverter.java
         */
//                HerodotusUser user = new HerodotusUser();
//                BeanUtil.fillBeanWithMap((LinkedHashMap) principal, user, true);
        return null;
      } else if (principal instanceof String && principal.equals("anonymousUser")) {
        return null;
      } else {
        throw new IllegalStateException("获取用户数据失败");
      }
    }
    return null;
  }

  public static String getUserId() {
    DefaultSecurityUser defaultSecurityUser = getPrincipal();
    if (ObjectUtils.isNotEmpty(defaultSecurityUser)) {
      return defaultSecurityUser.getUserId();
    }

    return null;
  }

  public static String wellFormRolePrefix(String content) {
    return wellFormPrefix(content, PREFIX_ROLE);
  }

  public static String wellFormPrefix(String content, String prefix) {
    if (StringUtils.startsWith(content, prefix)) {
      return content;
    } else {
      return prefix + content;
    }
  }

}
