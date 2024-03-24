package com.gstdev.cloud.oauth2.server.authorization.properties;

import com.google.common.collect.Lists;

import java.util.List;

public class WebResources {
  public static final List<String> DEFAULT_IGNORED_STATIC_RESOURCES =
    Lists.newArrayList(new String[]{
      "/error/**", "/plugins/**", "/herodotus/**", "/static/**", "/webjars/**"
      , "/assets/**", "/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs",
      "/v3/api-docs/**", "/openapi.json", "/favicon.ico"});
  public static final List<String> DEFAULT_PERMIT_ALL_RESOURCES = Lists.newArrayList(new String[]{"/open/**", "/stomp/ws", "/oauth2/sign-out", "/login*"});
  public static final List<String> DEFAULT_HAS_AUTHENTICATED_RESOURCES = Lists.newArrayList(new String[]{"/engine-rest/**"});

  public WebResources() {
  }
}
