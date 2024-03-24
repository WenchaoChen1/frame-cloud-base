//package com.gstdev.cloud.oauth2.server.authorization.customizer;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.apache.commons.lang3.ObjectUtils;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
//import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
//import org.springframework.session.FindByIndexNameSessionRepository;
//public class HerodotusSessionAuthenticationStrategy  extends RegisterSessionAuthenticationStrategy {
//  public HerodotusSessionAuthenticationStrategy(SessionRegistry sessionRegistry) {
//    super(sessionRegistry);
//  }
//
//  public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
//    if (ObjectUtils.isNotEmpty(authentication) && authentication.isAuthenticated() && authentication instanceof BearerTokenAuthentication) {
//      request.getSession().setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, authentication.getName());
//    }
//
//    super.onAuthentication(authentication, request, response);
//  }
//}
