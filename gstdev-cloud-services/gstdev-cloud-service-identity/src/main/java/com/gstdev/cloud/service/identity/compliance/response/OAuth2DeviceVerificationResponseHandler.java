package com.gstdev.cloud.service.identity.compliance.response;

import com.gstdev.cloud.base.definition.constants.DefaultConstants;
import com.gstdev.cloud.service.identity.service.OAuth2DeviceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2DeviceVerificationAuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;

/**
 * <p>Description: 设备验证成功后续逻辑处理器 </p>
 *
 * @author : cc
 * @date : 2023/5/3 9:35
 */
public class OAuth2DeviceVerificationResponseHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(OAuth2DeviceVerificationResponseHandler.class);

    private final OAuth2DeviceService deviceService;

    public OAuth2DeviceVerificationResponseHandler(OAuth2DeviceService deviceService) {
        super(DefaultConstants.DEVICE_VERIFICATION_SUCCESS_URI);
        this.deviceService = deviceService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2DeviceVerificationAuthenticationToken deviceVerificationAuthenticationToken =
                (OAuth2DeviceVerificationAuthenticationToken) authentication;

        log.info("[GstDev Cloud] |- Device verification authentication token is : [{}]", deviceVerificationAuthenticationToken);

        String clientId = deviceVerificationAuthenticationToken.getClientId();

        if (StringUtils.isNotBlank(clientId)) {
            boolean success = deviceService.activate(clientId, true);
            log.info("[GstDev Cloud] |- The activation status of the device is : [{}]", success);
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
