package com.gstdev.cloud.oauth2.authorization.server.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

/**
 * <p>Description: 自定义 Security 认证成功处理器 </p>
 *
 * @author : cc
 * @date : 2022/2/25 16:53
 */
public class OAuth2AccessLoginTokenResponseHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(OAuth2AccessLoginTokenResponseHandler.class);



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {



        super.onAuthenticationSuccess(request, response, authentication);
    }


}
