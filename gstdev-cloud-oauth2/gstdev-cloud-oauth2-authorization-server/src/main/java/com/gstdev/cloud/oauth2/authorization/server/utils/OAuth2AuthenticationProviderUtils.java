package com.gstdev.cloud.oauth2.authorization.server.utils;


import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;

/**
 * @author : wenchao.chen
 * @program: frame-cloud-base
 * @description: <p>Description: Utility methods for the OAuth 2.0 AuthenticationProvider's. </p>
 * @date : 2024/3/31 15:01
 */

// TODO NU
public class OAuth2AuthenticationProviderUtils {

    private OAuth2AuthenticationProviderUtils() {
    }

    /**
     * 获取已认证的客户端，否则抛出无效客户端异常。
     *
     * @param authentication 身份认证对象
     * @return 已认证的客户端身份认证令牌
     * @throws OAuth2AuthenticationException 如果客户端未认证，则抛出异常
     */
    public static OAuth2ClientAuthenticationToken getAuthenticatedClientElseThrowInvalidClient(Authentication authentication) {
        OAuth2ClientAuthenticationToken clientPrincipal = null;
        if (OAuth2ClientAuthenticationToken.class.isAssignableFrom(authentication.getPrincipal().getClass())) {
            clientPrincipal = (OAuth2ClientAuthenticationToken) authentication.getPrincipal();
        }
        if (clientPrincipal != null && clientPrincipal.isAuthenticated()) {
            return clientPrincipal;
        }
        throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT);
    }

    /**
     * 使授权无效。
     *
     * @param authorization 要使无效的授权
     * @param token         要使无效的令牌
     * @return 已无效的授权
     */
    public static <T extends OAuth2Token> OAuth2Authorization invalidate(OAuth2Authorization authorization, T token) {

        // 创建授权构建器
        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.from(authorization)
                // 标记令牌为无效
                .token(token,
                        (metadata) ->
                                metadata.put(OAuth2Authorization.Token.INVALIDATED_METADATA_NAME, true));

        if (OAuth2RefreshToken.class.isAssignableFrom(token.getClass())) {
            // 如果是刷新令牌，则同时标记关联的访问令牌和授权码为无效
            authorizationBuilder.token(
                    authorization.getAccessToken().getToken(),
                    (metadata) ->
                            metadata.put(OAuth2Authorization.Token.INVALIDATED_METADATA_NAME, true));
            // 获取授权中的授权码令牌
            OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode =
                    authorization.getToken(OAuth2AuthorizationCode.class);
            // 如果授权码令牌存在且未被标记为无效
            if (authorizationCode != null && !authorizationCode.isInvalidated()) {
                // 标记授权码令牌为无效，并将此操作添加到授权构建器中
                authorizationBuilder.token(
                        authorizationCode.getToken(),
                        (metadata) ->
                                metadata.put(OAuth2Authorization.Token.INVALIDATED_METADATA_NAME, true));
            }
        }
        // @formatter:on

        return authorizationBuilder.build();
    }
}
