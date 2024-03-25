package com.gstdev.cloud.oauth2.authorization.processor;


import com.gstdev.cloud.commons.utils.ListUtils;
import com.gstdev.cloud.commons.utils.WebUtils;
import com.gstdev.cloud.oauth2.authorization.definition.HerodotusConfigAttribute;
import com.gstdev.cloud.oauth2.authorization.definition.HerodotusRequest;
import com.gstdev.cloud.oauth2.authorization.properties.OAuth2AuthorizationProperties;
import com.gstdev.cloud.oauth2.core.constants.SecurityResources;
import com.gstdev.cloud.oauth2.core.enums.PermissionExpression;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.util.LinkedHashMap;
import java.util.List;


/**
 * @program: frame-cloud-base
 * @description: 安全过滤配置处理器
 * 对静态资源、开放接口等静态配置进行处理。整合默认配置和配置文件中的配置
 * @author: wenchao.chen
 * @create: 2024/03/25 15:29
 **/
@Component
public class SecurityMatcherConfigurer {

  private final OAuth2AuthorizationProperties authorizationProperties;
  private final ResourceUrlProvider resourceUrlProvider;

  private final RequestMatcher[] staticRequestMatchers;
  private final RequestMatcher[] permitAllRequestMatchers;
  private final RequestMatcher[] hasAuthenticatedRequestMatchers;
  private final LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> permitAllAttributes;

  public SecurityMatcherConfigurer(OAuth2AuthorizationProperties authorizationProperties, ResourceUrlProvider resourceUrlProvider) {
    this.authorizationProperties = authorizationProperties;
    this.resourceUrlProvider = resourceUrlProvider;
    List<String> staticResources = ListUtils.merge(authorizationProperties.getMatcher().getStaticResources(), SecurityResources.DEFAULT_IGNORED_STATIC_RESOURCES);
    List<String> permitAllResources = ListUtils.merge(authorizationProperties.getMatcher().getPermitAll(), SecurityResources.DEFAULT_PERMIT_ALL_RESOURCES);
    List<String> hasAuthenticatedResources = ListUtils.merge(authorizationProperties.getMatcher().getHasAuthenticated(), SecurityResources.DEFAULT_HAS_AUTHENTICATED_RESOURCES);
    this.permitAllAttributes = createPermitAllAttributes(permitAllResources);
    this.staticRequestMatchers = WebUtils.toRequestMatchers(staticResources);
    this.permitAllRequestMatchers = WebUtils.toRequestMatchers(permitAllResources);
    this.hasAuthenticatedRequestMatchers = WebUtils.toRequestMatchers(hasAuthenticatedResources);
  }

  /**
   * 获取 SecurityFilterChain 中配置的 RequestMatchers 信息。
   * <p>
   * RequestMatchers 可以理解为“静态配置”，将其与平台后端的“动态配置”有机结合。同时，防止因动态配置导致的静态配置失效的问题。
   * <p>
   * 目前只处理了 permitAll 类型。其它内容根据后续使用情况再行添加。
   *
   * @return RequestMatchers 中配置的权限数据
   */
  private LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> createPermitAllAttributes(List<String> permitAllResources) {
    if (CollectionUtils.isNotEmpty(permitAllResources)) {
      LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> result = new LinkedHashMap<>();
      permitAllResources.forEach(item -> {
        result.put(new HerodotusRequest(item), List.of(new HerodotusConfigAttribute(PermissionExpression.PERMIT_ALL.getValue())));
      });
      return result;
    }
    return new LinkedHashMap<>();
  }

  public RequestMatcher[] getStaticRequestMatchers() {
    return staticRequestMatchers;
  }

  public RequestMatcher[] getPermitAllRequestMatchers() {
    return permitAllRequestMatchers;
  }

  public RequestMatcher[] getHasAuthenticatedRequestMatchers() {
    return hasAuthenticatedRequestMatchers;
  }

  public LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> getPermitAllAttributes() {
    return permitAllAttributes;
  }

  /**
   * 判断是否为静态资源
   *
   * @param uri 请求 URL
   * @return 是否为静态资源
   */
  public boolean isStaticResources(String uri) {
    String staticUri = resourceUrlProvider.getForLookupPath(uri);
    return StringUtils.isNotBlank(staticUri);
  }

  public boolean isStrictMode() {
    return authorizationProperties.getStrict();
  }

  public boolean isPermitAllRequest(HttpServletRequest request) {
    return WebUtils.isRequestMatched(getPermitAllRequestMatchers(), request);
  }

  public boolean isHasAuthenticatedRequest(HttpServletRequest request) {
    return WebUtils.isRequestMatched(getHasAuthenticatedRequestMatchers(), request);
  }
}
