package com.gstdev.cloud.oauth2.core.constants;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * <p>Description: Web配置常量 </p>
 *
 */
public class SecurityResources {

    public static final List<String> DEFAULT_IGNORED_STATIC_RESOURCES = Lists.newArrayList(
            "/error/**",
            "/plugins/**",
            "/static/**",
            "/webjars/**",
            "/assets/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/openapi.json",
            "/favicon.ico");
    public static final List<String> DEFAULT_PERMIT_ALL_RESOURCES = Lists.newArrayList("/open/**", "/stomp/ws", "/oauth2/sign-out", "/login*");

    public static final List<String> DEFAULT_HAS_AUTHENTICATED_RESOURCES = Lists.newArrayList("/engine-rest/**");
}
