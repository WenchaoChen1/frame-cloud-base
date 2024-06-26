package com.gstdev.cloud.message.websocket.interceptor;

import com.gstdev.cloud.base.core.support.BearerTokenResolver;
import com.gstdev.cloud.base.definition.constants.BaseConstants;
import com.gstdev.cloud.base.definition.constants.SymbolConstants;
import com.gstdev.cloud.base.definition.domain.oauth2.PrincipalDetails;
import com.gstdev.cloud.message.websocket.utils.WebSocketUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * <p>Description: WebSocketSessionHandshakeInterceptor </p>
 * <p>
 * 不是开启websocket的必要步骤，根据自身的业务逻辑决定是否添加拦截器
 * <p>
 * 当前主要处理 Token 获取，以及 Token 的验证。如果验证成功，使用返回的用户名进行下一步，如果验证失败返回 false 终止握手。
 *
 * @author : cc
 * @date : 2022/12/4 21:34
 */
public class WebSocketAuthenticationHandshakeInterceptor implements HandshakeInterceptor {

    private static final Logger log = LoggerFactory.getLogger(WebSocketAuthenticationHandshakeInterceptor.class);

    private static final String SEC_WEBSOCKET_PROTOCOL = com.google.common.net.HttpHeaders.SEC_WEBSOCKET_PROTOCOL;

    private final BearerTokenResolver bearerTokenResolver;

    public WebSocketAuthenticationHandshakeInterceptor(BearerTokenResolver bearerTokenResolver) {
        this.bearerTokenResolver = bearerTokenResolver;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        HttpServletRequest httpServletRequest = WebSocketUtils.getHttpServletRequest(request);

        if (ObjectUtils.isNotEmpty(httpServletRequest)) {

            String protocol = httpServletRequest.getHeader(SEC_WEBSOCKET_PROTOCOL);

            String token = determineToken(protocol);

            if (StringUtils.isNotBlank(token)) {
                PrincipalDetails details = bearerTokenResolver.resolve(token);
                attributes.put(BaseConstants.PRINCIPAL, details);
                log.debug("[GstDev Cloud] |- WebSocket fetch the token is [{}].", token);
            } else {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                log.info("[GstDev Cloud] |- Token is invalid for WebSocket, stop handshake.");
                return false;
            }
        }

        return true;
    }

    private String determineToken(String protocol) {
        if (StringUtils.contains(protocol, SymbolConstants.COMMA)) {
            String[] protocols = StringUtils.split(protocol, SymbolConstants.COMMA);
            for (String item : protocols) {
                if (!StringUtils.endsWith(item, ".stomp")) {
                    return item;
                }
            }
        }
        return null;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {

        HttpServletRequest httpServletRequest = WebSocketUtils.getHttpServletRequest(request);
        HttpServletResponse httpServletResponse = WebSocketUtils.getHttpServletResponse(response);

        if (ObjectUtils.isNotEmpty(httpServletRequest) && ObjectUtils.isNotEmpty(httpServletResponse)) {
            httpServletResponse.setHeader(SEC_WEBSOCKET_PROTOCOL, "v10.stomp");
        }

        log.info("[GstDev Cloud] |- WebSocket handshake success!");
    }
}
