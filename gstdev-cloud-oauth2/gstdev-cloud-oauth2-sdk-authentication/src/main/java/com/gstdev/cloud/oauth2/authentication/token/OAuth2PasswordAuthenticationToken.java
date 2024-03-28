package com.gstdev.cloud.oauth2.authentication.token;

import com.gstdev.cloud.oauth2.core.definition.HerodotusGrantType;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>Description: 自定义密码模式认证Token </p>
 *
 * @author : cc
 * @date : 2022/2/22 15:49
 */
public class OAuth2PasswordAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {
  private final Set<String> scopes;

  public OAuth2PasswordAuthenticationToken(Authentication clientPrincipal, @Nullable Set<String> scopes, @Nullable Map<String, Object> additionalParameters) {
    super(HerodotusGrantType.PASSWORD, clientPrincipal, additionalParameters);
    Assert.notNull(clientPrincipal, "clientPrincipal cannot be null");
    this.scopes = Collections.unmodifiableSet(CollectionUtils.isNotEmpty(scopes) ? new HashSet<>(scopes) : Collections.emptySet());
  }

  public Set<String> getScopes() {
    return this.scopes;
  }
}
