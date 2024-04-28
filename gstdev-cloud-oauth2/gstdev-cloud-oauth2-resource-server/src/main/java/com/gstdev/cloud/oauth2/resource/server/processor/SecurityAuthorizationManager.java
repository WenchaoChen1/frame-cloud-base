// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.resource.server.processor;

import com.gstdev.cloud.base.core.utils.http.HeaderUtils;
import com.gstdev.cloud.oauth2.resource.server.definition.FrameConfigAttribute;
import com.gstdev.cloud.oauth2.resource.server.definition.FrameRequest;
import com.gstdev.cloud.oauth2.resource.server.definition.FrameRequestMatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * <p>Description: Spring Security 6 授权管理器 </p>
 * <p>
 * Spring Security 6 授权管理
 * 1. 由原来的 AccessDecisionManager 和 AccessDecisionVoter，变更为使用 {@link AuthorizationManager}
 * 2. 原来的 SecurityMetadataSource 已经不再使用。其实想要自己扩展，基本逻辑还是一致。只不过给使用者更大的扩展度和灵活度。
 * 3. 原来的 <code>FilterSecurityInterceptor</code>，已经不再使用。改为使用 {@link org.springframework.security.web.access.intercept.AuthorizationFilter}
 *
 * @author : cc
 * @date : 2022/11/8 14:57
 */
public class SecurityAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private static final Logger log = LoggerFactory.getLogger(SecurityAuthorizationManager.class);

    private final SecurityMetadataSourceStorage securityMetadataSourceStorage;
    private final SecurityMatcherConfigurer securityMatcherConfigurer;

    public SecurityAuthorizationManager(SecurityMetadataSourceStorage securityMetadataSourceStorage, SecurityMatcherConfigurer securityMatcherConfigurer) {
        this.securityMetadataSourceStorage = securityMetadataSourceStorage;
        this.securityMatcherConfigurer = securityMatcherConfigurer;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {

        final HttpServletRequest request = object.getRequest();

        String url = request.getRequestURI();
        String method = request.getMethod();

        // 检查是否为静态资源，如果是，则直接放行
        if (securityMatcherConfigurer.isStaticResources(url)) {
            log.trace("[GstDev Cloud] |- Is static resource : [{}], Passed!", url);
            return new AuthorizationDecision(true);
        }

        // 检查是否为白名单资源，如果是，则直接放行
        if (securityMatcherConfigurer.isPermitAllRequest(request)) {
            log.trace("[GstDev Cloud] |- Is white list resource : [{}], Passed!", url);
            return new AuthorizationDecision(true);
        }

        // 检查是否为Feign内部调用，如果是，则直接放行
        String feignInnerFlag = HeaderUtils.getFrameFromIn(request);
        if (StringUtils.isNotBlank(feignInnerFlag)) {
            log.trace("[GstDev Cloud] |- Is feign inner invoke : [{}], Passed!", url);
            return new AuthorizationDecision(true);
        }

        // 检查是否为已经认证的请求，如果是，则根据认证结果授权
        if (securityMatcherConfigurer.isHasAuthenticatedRequest(request)) {
            log.trace("[GstDev Cloud] |- Is has authenticated resource : [{}]", url);
            return new AuthorizationDecision(authentication.get().isAuthenticated());
        }

        // 获取资源的权限配置
        List<FrameConfigAttribute> configAttributes = findConfigAttribute(url, method, request);
        if (CollectionUtils.isEmpty(configAttributes)) {
            log.warn("[GstDev Cloud] |- NO PRIVILEGES : [{}].", url);

            // 如果非严格模式下，并且请求已经认证，则放行
            if (!securityMatcherConfigurer.isStrictMode()) {
                if (authentication.get().isAuthenticated()) {
                    log.debug("[GstDev Cloud] |- Request is authenticated: [{}].", url);
                    return new AuthorizationDecision(true);
                }
            }

            return new AuthorizationDecision(false);
        }

        // 根据权限配置进行授权判断
        for (FrameConfigAttribute configAttribute : configAttributes) {
            WebExpressionAuthorizationManager webExpressionAuthorizationManager = new WebExpressionAuthorizationManager(configAttribute.getAttribute());
            AuthorizationDecision decision = webExpressionAuthorizationManager.check(authentication, object);
            if (decision.isGranted()) {
                log.debug("[GstDev Cloud] |- Request [{}] is authorized!", object.getRequest().getRequestURI());
                return decision;
            }
        }

        return new AuthorizationDecision(false);
    }

    // 根据URL和HTTP方法查找资源的权限配置
    private List<FrameConfigAttribute> findConfigAttribute(String url, String method, HttpServletRequest request) {

        log.debug("[GstDev Cloud] |- Current Request is : [{}] - [{}]", url, method);

        // 从本地存储中获取资源的权限配置
        List<FrameConfigAttribute> configAttributes = this.securityMetadataSourceStorage.getConfigAttribute(url, method);
        if (CollectionUtils.isNotEmpty(configAttributes)) {
            log.debug("[GstDev Cloud] |- Get configAttributes from local storage for : [{}] - [{}]", url, method);
            return configAttributes;
        } else {
            // 从兼容性存储中查找具有通配符路径的资源的权限配置
            LinkedHashMap<FrameRequest, List<FrameConfigAttribute>> compatible = this.securityMetadataSourceStorage.getCompatible();
            if (MapUtils.isNotEmpty(compatible)) {
                // 支持含有**通配符的路径搜索
                for (Map.Entry<FrameRequest, List<FrameConfigAttribute>> entry : compatible.entrySet()) {
                    FrameRequestMatcher requestMatcher = new FrameRequestMatcher(entry.getKey());
                    if (requestMatcher.matches(request)) {
                        log.debug("[GstDev Cloud] |- Request match the wildcard [{}] - [{}]", entry.getKey(), entry.getValue());
                        return entry.getValue();
                    }
                }
            }
        }

        return null;
    }
}
