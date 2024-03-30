//package com.gstdev.cloud.oauth2.authorization.converter;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.core.log.LogMessage;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.util.Assert;
//import org.springframework.util.StringUtils;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Collections;
//
///**
// * @program: frame-cloud-base
// * @description: Jwt 权限数据转换器
// * @author: wenchao.chen
// * @create: 2024/03/28 11:19
// **/
//public class CustomizeJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
//
//  private static final String DEFAULT_AUTHORITY_PREFIX = "SCOPE_";
//  private static final Collection<String> WELL_KNOWN_AUTHORITIES_CLAIM_NAMES = Arrays.asList("scope", "scp");
//  private final Log logger = LogFactory.getLog(getClass());
//  private String authorityPrefix = DEFAULT_AUTHORITY_PREFIX;
//
//  private String authoritiesClaimName;
//
//  /**
//   * Extract {@link GrantedAuthority}s from the given {@link Jwt}.
//   *
//   * @param jwt The {@link Jwt} token
//   * @return The {@link GrantedAuthority authorities} read from the token scopes
//   */
//  @Override
//  public Collection<GrantedAuthority> convert(Jwt jwt) {
//    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//    for (String authority : getAuthorities(jwt)) {
//      grantedAuthorities.add(new HerodotusGrantedAuthority(authority));
//    }
//    return grantedAuthorities;
//  }
//
//  /**
//   * Sets the prefix to use for {@link GrantedAuthority authorities} mapped by this
//   * converter. Defaults to
//   *
//   * @param authorityPrefix The authority prefix
//   * @since 5.2
//   */
//  public void setAuthorityPrefix(String authorityPrefix) {
//    Assert.notNull(authorityPrefix, "authorityPrefix cannot be null");
//    this.authorityPrefix = authorityPrefix;
//  }
//
//  /**
//   * Sets the name of token claim to use for mapping {@link GrantedAuthority
//   * authorities} by this converter. Defaults to
//   *
//   * @param authoritiesClaimName The token claim name to map authorities
//   * @since 5.2
//   */
//  public void setAuthoritiesClaimName(String authoritiesClaimName) {
//    Assert.hasText(authoritiesClaimName, "authoritiesClaimName cannot be empty");
//    this.authoritiesClaimName = authoritiesClaimName;
//  }
//
//  private String getAuthoritiesClaimName(Jwt jwt) {
//    if (this.authoritiesClaimName != null) {
//      return this.authoritiesClaimName;
//    }
//    for (String claimName : WELL_KNOWN_AUTHORITIES_CLAIM_NAMES) {
//      if (jwt.hasClaim(claimName)) {
//        return claimName;
//      }
//    }
//    return null;
//  }
//
//  private Collection<String> getAuthorities(Jwt jwt) {
//    String claimName = getAuthoritiesClaimName(jwt);
//    if (claimName == null) {
//      this.logger.trace("Returning no authorities since could not find any claims that might contain scopes");
//      return Collections.emptyList();
//    }
//    if (this.logger.isTraceEnabled()) {
//      this.logger.trace(LogMessage.format("Looking for scopes in claim %s", claimName));
//    }
//    Object authorities = jwt.getClaim(claimName);
//    if (authorities instanceof String) {
//      if (StringUtils.hasText((String) authorities)) {
//        return Arrays.asList(((String) authorities).split(" "));
//      }
//      return Collections.emptyList();
//    }
//    if (authorities instanceof Collection) {
//      return castAuthoritiesToCollection(authorities);
//    }
//    return Collections.emptyList();
//  }
//
//  @SuppressWarnings("unchecked")
//  private Collection<String> castAuthoritiesToCollection(Object authorities) {
//    return (Collection<String>) authorities;
//  }
//}
