//package com.gstdev.cloud.oauth2.authorization.server.converter;
//
//
//import com.gstdev.cloud.base.core.utils.http.SessionUtils;
//import com.gstdev.cloud.base.core.utils.type.ListUtils;
//import com.gstdev.cloud.oauth2.authorization.server.utils.OAuth2EndpointUtils;
//import com.gstdev.cloud.rest.core.exception.SessionInvalidException;
//import com.gstdev.cloud.rest.protect.crypto.processor.HttpCryptoProcessor;
//import jakarta.servlet.http.HttpServletRequest;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.AuthenticationConverter;
//import org.springframework.util.MultiValueMap;
//
//import java.util.*;
//
//public abstract class AbstractAuthenticationConverter implements AuthenticationConverter {
//    private final HttpCryptoProcessor httpCryptoProcessor;
//
//    public AbstractAuthenticationConverter(HttpCryptoProcessor httpCryptoProcessor) {
//        this.httpCryptoProcessor = httpCryptoProcessor;
//    }
//
//    protected String[] decrypt(HttpServletRequest request, String sessionId, List<String> parameters) {
//        if (SessionUtils.isCryptoEnabled(request, sessionId) && CollectionUtils.isNotEmpty(parameters)) {
//            List<String> result = parameters.stream().map((item) -> {
//                return this.decrypt(request, sessionId, item);
//            }).toList();
//            return ListUtils.toStringArray(result);
//        } else {
//            return ListUtils.toStringArray(parameters);
//        }
//    }
//
//    protected String decrypt(HttpServletRequest request, String sessionId, String parameter) {
//        if (SessionUtils.isCryptoEnabled(request, sessionId) && StringUtils.isNotBlank(parameter)) {
//            try {
//                return this.httpCryptoProcessor.decrypt(sessionId, parameter);
//            } catch (SessionInvalidException var5) {
//                OAuth2EndpointUtils.throwError("SessionExpiredException", var5.getMessage(), "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2");
//            }
//        }
//
//        return parameter;
//    }
//
//    //  客户端信息
//    protected Authentication getClientPrincipal() {
//        return SecurityContextHolder.getContext().getAuthentication();
//    }
//
//    protected Map<String, Object> getAdditionalParameters(HttpServletRequest request, MultiValueMap<String, String> parameters) {
//        String sessionId = SessionUtils.analyseSessionId(request);
//        Map<String, Object> additionalParameters = new HashMap();
//        parameters.forEach((key, value) -> {
//            if (!key.equals("grant_type") && !key.equals("scope")) {
//                additionalParameters.put(key, value.size() == 1 ? this.decrypt(request, sessionId, (String) value.get(0)) : this.decrypt(request, sessionId, value));
//            }
//
//        });
//        return additionalParameters;
//    }
//
//    protected Set<String> getRequestedScopes(String scope) {
//        Set<String> requestedScopes = null;
//        if (org.springframework.util.StringUtils.hasText(scope)) {
//            requestedScopes = new HashSet(Arrays.asList(org.springframework.util.StringUtils.delimitedListToStringArray(scope, " ")));
//        }
//
//        return requestedScopes;
//    }
//}
