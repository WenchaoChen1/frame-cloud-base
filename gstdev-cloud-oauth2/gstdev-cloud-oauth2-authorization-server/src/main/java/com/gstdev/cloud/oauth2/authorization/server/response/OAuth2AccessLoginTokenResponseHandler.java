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


//    @Resource
//    private OAuth2AuthenticationProperties authenticationProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("[GstDev Cloud] |- OAuth2 authentication success for [{}]", request.getRequestURI());
//        if (authenticationProperties.getFormLogin().getFrameLoginType().equals(FrameLoginType.LOCAL)) {
        super.onAuthenticationSuccess(request, response, authentication);

//        }
//        if (authenticationProperties.getFormLogin().getFrameLoginType().equals(FrameLoginType.REMOTE)) {
//
//            // 获取登录用户信息
//            // 将token写入响应中返回给客户端
//            response.setContentType("application/json;charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.write("{\"code\":200,\"msg\":\"登录成功\",\"data\":{\"token\":\"" + 111 + "\"}}");
//            out.flush();
//            out.close();
//        }
    }
}
