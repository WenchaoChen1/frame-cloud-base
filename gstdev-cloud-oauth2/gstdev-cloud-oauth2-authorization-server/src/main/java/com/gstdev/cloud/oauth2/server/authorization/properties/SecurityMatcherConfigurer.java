package com.gstdev.cloud.oauth2.server.authorization.properties;


import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityMatcherConfigurer {
  private final OAuth2AuthorizationProperties authorizationProperties;
  private List<String> staticResources;
  private List<String> permitAllResources;
  private List<String> hasAuthenticatedResources;
  private List<String> whitelist;


  public SecurityMatcherConfigurer(OAuth2AuthorizationProperties authorizationProperties) {
    this.authorizationProperties = authorizationProperties;
    this.staticResources = new ArrayList();
    this.permitAllResources = new ArrayList();
    this.hasAuthenticatedResources = new ArrayList();
    this.whitelist = new ArrayList();
  }

  public List<String> getStaticResourceList() {
    if (CollectionUtils.isEmpty(this.staticResources)) {
      this.staticResources = ListUtils.merge(this.authorizationProperties.getMatcher().getStaticResources(), WebResources.DEFAULT_IGNORED_STATIC_RESOURCES);
    }

    return this.staticResources;
  }

  public List<String> getPermitAllList() {
    if (CollectionUtils.isEmpty(this.permitAllResources)) {
      this.permitAllResources = ListUtils.merge(this.authorizationProperties.getMatcher().getPermitAll(), WebResources.DEFAULT_PERMIT_ALL_RESOURCES);
    }

    return this.permitAllResources;
  }

  public List<String> getHasAuthenticatedList() {
    if (CollectionUtils.isEmpty(this.hasAuthenticatedResources)) {
      this.hasAuthenticatedResources = ListUtils.merge(this.authorizationProperties.getMatcher().getHasAuthenticated(), WebResources.DEFAULT_HAS_AUTHENTICATED_RESOURCES);
    }

    return this.hasAuthenticatedResources;
  }

  public List<String> getWhiteList() {
    if (CollectionUtils.isEmpty(this.whitelist)) {
      this.whitelist = ListUtils.merge(this.authorizationProperties.getMatcher().getWhitelist(), WebResources.DEFAULT_HAS_AUTHENTICATED_RESOURCES);
    }

    return this.whitelist;
  }

  private RequestMatcher[] toRequestMatchers(List<String> paths) {
    if (CollectionUtils.isNotEmpty(paths)) {
      List<AntPathRequestMatcher> matchers = paths.stream().map(AntPathRequestMatcher::new).toList();
      RequestMatcher[] result = new RequestMatcher[matchers.size()];
      return (RequestMatcher[])matchers.toArray(result);
    } else {
      return new RequestMatcher[0];
    }
  }

  public RequestMatcher[] getStaticResourceArray() {
    return this.toRequestMatchers(this.getStaticResourceList());
  }

  public RequestMatcher[] getPermitAllArray() {
    return this.toRequestMatchers(this.getPermitAllList());
  }
  public RequestMatcher[] getWhiteAllArray() {
    return this.toRequestMatchers(this.getWhiteList());
  }

  public OAuth2AuthorizationProperties getAuthorizationProperties() {
    return this.authorizationProperties;
  }

}
