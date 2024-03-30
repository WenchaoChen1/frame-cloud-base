package com.gstdev.cloud.oauth2.management.response;

import com.gstdev.cloud.assistant.definition.constants.BaseConstants;
import com.gstdev.cloud.assistant.definition.domain.oauth2.PrincipalDetails;
import com.gstdev.cloud.assistant.core.json.jackson2.utils.Jackson2Utils;
import com.gstdev.cloud.assistant.core.utils.http.SessionUtils;
import com.gstdev.cloud.rest.protect.crypto.processor.HttpCryptoProcessor;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: 自定义 Security 认证成功处理器 </p>
 *
 * @author : cc
 * @date : 2022/2/25 16:53
 */
public class OAuth2AccessTokenResponseHandler implements AuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(OAuth2AccessTokenResponseHandler.class);

    private final HttpMessageConverter<OAuth2AccessTokenResponse> accessTokenHttpResponseConverter =
            new OAuth2AccessTokenResponseHttpMessageConverter();

    private final HttpCryptoProcessor httpCryptoProcessor;

    public OAuth2AccessTokenResponseHandler(HttpCryptoProcessor httpCryptoProcessor) {
        this.httpCryptoProcessor = httpCryptoProcessor;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.debug("[GstDev Cloud] |- OAuth2 authentication success for [{}]", request.getRequestURI());

        OAuth2AccessTokenAuthenticationToken accessTokenAuthentication =
                (OAuth2AccessTokenAuthenticationToken) authentication;

        OAuth2AccessToken accessToken = accessTokenAuthentication.getAccessToken();
        OAuth2RefreshToken refreshToken = accessTokenAuthentication.getRefreshToken();
        Map<String, Object> additionalParameters = accessTokenAuthentication.getAdditionalParameters();

        OAuth2AccessTokenResponse.Builder builder =
                OAuth2AccessTokenResponse.withToken(accessToken.getTokenValue())
                        .tokenType(accessToken.getTokenType())
                        .scopes(accessToken.getScopes());
        if (accessToken.getIssuedAt() != null && accessToken.getExpiresAt() != null) {
            builder.expiresIn(ChronoUnit.SECONDS.between(accessToken.getIssuedAt(), accessToken.getExpiresAt()));
        }

        if (refreshToken != null) {
            builder.refreshToken(refreshToken.getTokenValue());
        }

        if (isOidcUserInfoPattern(additionalParameters)) {
            builder.additionalParameters(additionalParameters);
        } else {
            String sessionId = SessionUtils.analyseSessionId(request);
            Object details = authentication.getDetails();
            if (isHerodotusUserInfoPattern(sessionId, details)) {
                PrincipalDetails authenticationDetails = (PrincipalDetails) details;
                String data = Jackson2Utils.toJson(authenticationDetails);
                String encryptData = httpCryptoProcessor.encrypt(sessionId, data);
                Map<String, Object> parameters = new HashMap<>(additionalParameters);
                parameters.put(BaseConstants.OPEN_ID, encryptData);
                builder.additionalParameters(parameters);
            } else {
                log.warn("[GstDev Cloud] |- OAuth2 authentication can not get use info.");
            }
        }

        OAuth2AccessTokenResponse accessTokenResponse = builder.build();
        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
        this.accessTokenHttpResponseConverter.write(accessTokenResponse, null, httpResponse);
    }

    private boolean isHerodotusUserInfoPattern(String sessionId, Object details) {
        return StringUtils.isNotBlank(sessionId) && ObjectUtils.isNotEmpty(details) && details instanceof PrincipalDetails;
    }

    private boolean isOidcUserInfoPattern(Map<String, Object> additionalParameters) {
        return MapUtils.isNotEmpty(additionalParameters) && additionalParameters.containsKey(OidcParameterNames.ID_TOKEN);
    }
}
