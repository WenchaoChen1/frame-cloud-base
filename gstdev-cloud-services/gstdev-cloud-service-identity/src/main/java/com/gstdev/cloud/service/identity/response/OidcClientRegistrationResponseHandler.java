package com.gstdev.cloud.service.identity.response;

import com.gstdev.cloud.service.identity.service.OAuth2DeviceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.oidc.OidcClientRegistration;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcClientRegistrationAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.oidc.http.converter.OidcClientRegistrationHttpMessageConverter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * <p>Description: 客户端自动注册成功后续逻辑处理器 </p>
 *
 * @author : cc
 * @date : 2023/5/23 17:37
 */
public class OidcClientRegistrationResponseHandler implements AuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(OidcClientRegistrationResponseHandler.class);

    private final OAuth2DeviceService deviceService;

    private final HttpMessageConverter<OidcClientRegistration> clientRegistrationHttpMessageConverter =
        new OidcClientRegistrationHttpMessageConverter();

    public OidcClientRegistrationResponseHandler(OAuth2DeviceService deviceService) {
        this.deviceService = deviceService;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OidcClientRegistrationAuthenticationToken clientRegistrationAuthenticationToken =
            (OidcClientRegistrationAuthenticationToken) authentication;

        OidcClientRegistration clientRegistration = clientRegistrationAuthenticationToken.getClientRegistration();

        boolean success = deviceService.sync(clientRegistration);
        if (success) {
            log.info("[GstDev Cloud] |- Sync oidcClientRegistration to device succeed.");
        } else {
            log.warn("[GstDev Cloud] |- Sync oidcClientRegistration to device failed!");
        }

        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
        if (HttpMethod.POST.name().equals(request.getMethod())) {
            httpResponse.setStatusCode(HttpStatus.CREATED);
        } else {
            httpResponse.setStatusCode(HttpStatus.OK);
        }
        this.clientRegistrationHttpMessageConverter.write(clientRegistration, null, httpResponse);
    }
}
