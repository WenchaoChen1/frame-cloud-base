// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.authorization.server.configurer;

import com.gstdev.cloud.oauth2.authorization.server.provider.OAuth2FormLoginAuthenticationToken;
import com.gstdev.cloud.oauth2.authorization.server.utils.OAuth2EndpointUtils;
import com.gstdev.cloud.oauth2.core.utils.SymmetricUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: OAuth2 表单登录过滤器 </p>
 *
 * @author : cc
 * @date : 2022/4/12 11:08
 */
public class OAuth2FormLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(OAuth2FormLoginAuthenticationFilter.class);
    private boolean postOnly = true;

    public OAuth2FormLoginAuthenticationFilter() {
        super();
    }

    public OAuth2FormLoginAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        OAuth2FormLoginAuthenticationToken authRequest = getAuthenticationToken(request);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private OAuth2FormLoginAuthenticationToken getAuthenticationToken(
            HttpServletRequest request) {
        // 参数提取验证
        MultiValueMap<String, String> parameters = OAuth2EndpointUtils.getParameters(request);
        // username (REQUIRED) 用户名验证
        OAuth2EndpointUtils.checkRequiredParameter(parameters, OAuth2ParameterNames.USERNAME);

        // password (REQUIRED) 密码验证
        OAuth2EndpointUtils.checkRequiredParameter(parameters, OAuth2ParameterNames.PASSWORD);

        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String key = request.getParameter("symmetric");

        if (StringUtils.isBlank(username)) {
            username = "";
        }

        if (StringUtils.isBlank(password)) {
            password = "";
        }

        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            byte[] byteKey = SymmetricUtils.getDecryptedSymmetricKey(key);
            username = SymmetricUtils.decrypt(username, byteKey);
            password = SymmetricUtils.decrypt(password, byteKey);
            log.debug("[GstDev Cloud] |- Decrypt Username is : [{}], Password is : [{}]", username, password);
        }

        OAuth2FormLoginAuthenticationToken oAuth2FormLoginAuthenticationToken = new OAuth2FormLoginAuthenticationToken(username, password);
        Map<String, String> singleValueMap = parameters.toSingleValueMap();
        Map<String, Object> map = new HashMap<>();
//        singleValueMap.forEach((key1,value)->{
//            map.put(key1,value);
//        });
        map.put(OAuth2ParameterNames.USERNAME, username);
        map.put(OAuth2ParameterNames.PASSWORD, password);
        oAuth2FormLoginAuthenticationToken.setAdditionalParameters(map);
        return oAuth2FormLoginAuthenticationToken;
    }


    @Override
    public void setPostOnly(boolean postOnly) {
        super.setPostOnly(postOnly);
        this.postOnly = postOnly;
    }

    /**
     * 重写该方法，避免在日志Debug级别会输出错误信息的问题。
     *
     * @param request  请求
     * @param response 响应
     * @param failed   失败内容
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        getRememberMeServices().loginFail(request, response);
        getFailureHandler().onAuthenticationFailure(request, response, failed);
    }
}
