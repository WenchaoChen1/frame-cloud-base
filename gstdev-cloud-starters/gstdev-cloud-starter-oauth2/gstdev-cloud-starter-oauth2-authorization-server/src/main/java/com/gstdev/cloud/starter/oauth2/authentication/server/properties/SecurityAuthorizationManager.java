//package com.gstdev.cloud.starter.oauth2.authentication.server.properties;//package com.gstdev.cloud.oauth2.server.authorization.properties;
////
////
////import jakarta.servlet.http.HttpServletRequest;
////import org.apache.commons.collections4.CollectionUtils;
////import org.apache.commons.collections4.MapUtils;
////import org.apache.commons.lang3.StringUtils;
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////import org.springframework.security.authorization.AuthorizationDecision;
////import org.springframework.security.authorization.AuthorizationManager;
////import org.springframework.security.core.Authentication;
////import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
////import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
////
////import java.util.Iterator;
////import java.util.LinkedHashMap;
////import java.util.List;
////import java.util.Map;
////import java.util.function.Supplier;
////
////// TODO NUD
////public class SecurityAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
////  private static final Logger log = LoggerFactory.getLogger(SecurityAuthorizationManager.class);
////  private final SecurityMetadataSourceStorage securityMetadataSourceStorage;
////  private final SecurityMatcherConfigurer securityMatcherConfigurer;
////
////  public SecurityAuthorizationManager(SecurityMetadataSourceStorage securityMetadataSourceStorage, SecurityMatcherConfigurer securityMatcherConfigurer) {
////    this.securityMetadataSourceStorage = securityMetadataSourceStorage;
////    this.securityMatcherConfigurer = securityMatcherConfigurer;
////  }
////
////  public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
////    HttpServletRequest request = object.getRequest();
////    String url = request.getRequestURI();
////    String method = request.getMethod();
////    if (WebUtils.isStaticResources(url)) {
////      log.trace("[GstDev Cloud] |- Is static resource : [{}], Passed!", url);
////      return new AuthorizationDecision(true);
////    } else if (WebUtils.isPathMatch(this.securityMatcherConfigurer.getPermitAllList(), url)) {
////      log.trace("[GstDev Cloud] |- Is white list resource : [{}], Passed!", url);
////      return new AuthorizationDecision(true);
////    } else {
////      String feignInnerFlag = HeaderUtils.getHerodotusFromIn(request);
////      if (StringUtils.isNotBlank(feignInnerFlag)) {
////        log.trace("[GstDev Cloud] |- Is feign inner invoke : [{}], Passed!", url);
////        return new AuthorizationDecision(true);
////      } else if (WebUtils.isPathMatch(this.securityMatcherConfigurer.getHasAuthenticatedList(), url)) {
////        log.trace("[GstDev Cloud] |- Is has authenticated resource : [{}]", url);
////        return new AuthorizationDecision(((Authentication)authentication.get()).isAuthenticated());
////      } else {
////        List<HerodotusConfigAttribute> configAttributes = this.findConfigAttribute(url, method, request);
////        if (CollectionUtils.isEmpty(configAttributes)) {
////          log.warn("[GstDev Cloud] |- NO PRIVILEGES : [{}].", url);
////          if (!this.securityMatcherConfigurer.getAuthorizationProperties().getStrict() && ((Authentication)authentication.get()).isAuthenticated()) {
////            log.debug("[GstDev Cloud] |- Request is authenticated: [{}].", url);
////            return new AuthorizationDecision(true);
////          } else {
////            return new AuthorizationDecision(false);
////          }
////        } else {
////          Iterator var8 = configAttributes.iterator();
////
////          AuthorizationDecision decision;
////          do {
////            if (!var8.hasNext()) {
////              return new AuthorizationDecision(false);
////            }
////
////            HerodotusConfigAttribute configAttribute = (HerodotusConfigAttribute)var8.next();
////            WebExpressionAuthorizationManager webExpressionAuthorizationManager = new WebExpressionAuthorizationManager(configAttribute.getAttribute());
////            decision = webExpressionAuthorizationManager.check(authentication, object);
////          } while(!decision.isGranted());
////
////          log.debug("[GstDev Cloud] |- Request [{}] is authorized!", object.getRequest().getRequestURI());
////          return decision;
////        }
////      }
////    }
////  }
////
////  private List<HerodotusConfigAttribute> findConfigAttribute(String url, String method, HttpServletRequest request) {
////    log.debug("[GstDev Cloud] |- Current Request is : [{}] - [{}]", url, method);
////    List<HerodotusConfigAttribute> configAttributes = this.securityMetadataSourceStorage.getConfigAttribute(url, method);
////    if (CollectionUtils.isNotEmpty(configAttributes)) {
////      log.debug("[GstDev Cloud] |- Get configAttributes from local storage for : [{}] - [{}]", url, method);
////      return configAttributes;
////    } else {
////      LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> compatible = this.securityMetadataSourceStorage.getCompatible();
////      if (MapUtils.isNotEmpty(compatible)) {
////        Iterator var6 = compatible.entrySet().iterator();
////
////        while(var6.hasNext()) {
////          Map.Entry<HerodotusRequest, List<HerodotusConfigAttribute>> entry = (Map.Entry)var6.next();
////          HerodotusRequestMatcher requestMatcher = new HerodotusRequestMatcher((HerodotusRequest)entry.getKey());
////          if (requestMatcher.matches(request)) {
////            log.debug("[GstDev Cloud] |- Request match the wildcard [{}] - [{}]", entry.getKey(), entry.getValue());
////            return (List)entry.getValue();
////          }
////        }
////      }
////
////      return null;
////    }
////  }
////}
