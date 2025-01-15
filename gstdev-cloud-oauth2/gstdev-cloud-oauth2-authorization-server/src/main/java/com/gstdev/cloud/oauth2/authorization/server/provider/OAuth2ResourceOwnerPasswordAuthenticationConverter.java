package com.gstdev.cloud.oauth2.authorization.server.provider;

import com.gstdev.cloud.oauth2.authorization.server.utils.OAuth2EndpointUtils;
import com.gstdev.cloud.oauth2.core.definition.FrameGrantType;
import com.gstdev.cloud.rest.protect.crypto.processor.HttpCryptoProcessor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.MultiValueMap;

/**
 * <p>Description: 自定义密码模式认证转换器 </p>
 * <p>
 * {@code AuthenticationConverter} 类似于以前的 {@code AbstractTokenGranter}
 * 主要用途是从请求中获取参数，并拼装Token类
 *
 * @author : cc
 * @date : 2022/2/22 17:03
 */
public final class OAuth2ResourceOwnerPasswordAuthenticationConverter extends AbstractAuthenticationConverter {
    public OAuth2ResourceOwnerPasswordAuthenticationConverter(HttpCryptoProcessor httpCryptoProcessor) {
        super(httpCryptoProcessor);
    }

    @Nullable
    @Override
    public Authentication convert(HttpServletRequest request) {
        // grant_type (REQUIRED)
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (!FrameGrantType.PASSWORD.getValue().equals(grantType)) {
            return null;
        }
        // 参数提取验证
        MultiValueMap<String, String> parameters = OAuth2EndpointUtils.getParameters(request);

        // scope (OPTIONAL)令牌申请访问范围验证
        String scope = OAuth2EndpointUtils.checkOptionalParameter(parameters, OAuth2ParameterNames.SCOPE);

        // username (REQUIRED) 用户名验证
        OAuth2EndpointUtils.checkRequiredParameter(parameters, OAuth2ParameterNames.USERNAME);

        // password (REQUIRED) 密码验证
        OAuth2EndpointUtils.checkRequiredParameter(parameters, OAuth2ParameterNames.PASSWORD);

        return new OAuth2ResourceOwnerPasswordAuthenticationToken(getClientPrincipal(), getRequestedScopes(scope), getAdditionalParameters(request, parameters));
    }


//  @Override
//  public Authentication convert(HttpServletRequest request) {
//    // 授权类型 (必需)
//    String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
//    if (!AuthorizationGrantType.PASSWORD.getValue().equals(grantType)) {
//      return null;
//    }
//
//    // 参数提取验证
//    MultiValueMap<String, String> parameters = OAuth2EndpointUtils.getParameters(request);
//
//    // 客户端信息
//    Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();
//    if (clientPrincipal == null) {
//      OAuth2EndpointUtils.throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ErrorCodes.INVALID_CLIENT, OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
//    }
//
//
//    // 令牌申请访问范围验证 (可选)
//    String scope = parameters.getFirst(OAuth2ParameterNames.SCOPE);
//    if (StringUtils.hasText(scope) && parameters.get(OAuth2ParameterNames.SCOPE).size() != 1) {
//      OAuth2EndpointUtils.throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.SCOPE, OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
//    }
//    Set<String> requestedScopes = null;
//    if (StringUtils.hasText(scope)) {
//      requestedScopes = new HashSet<>(Arrays.asList(StringUtils.delimitedListToStringArray(scope, " ")));
//    }
//
//    // 用户名验证(必需)
//    String username = parameters.getFirst(OAuth2ParameterNames.USERNAME);
//    if (!StringUtils.hasText(username) || parameters.get(OAuth2ParameterNames.USERNAME).size() != 1) {
//      OAuth2EndpointUtils.throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.USERNAME, OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
//    }
//
//    // 密码验证(必需)
//    String password = parameters.getFirst(OAuth2ParameterNames.PASSWORD);
////    if (StrUtil.isBlank(password)) {
//    if (!StringUtils.hasText(password) || parameters.get(OAuth2ParameterNames.PASSWORD).size() != 1) {
//      OAuth2EndpointUtils.throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.PASSWORD, OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
//    }
//
//    // 附加参数(保存用户名/密码传递给 PasswordAuthenticationProvider 用于身份认证)
//    // @formatter:off
//    Map<String, Object> additionalParameters = parameters.entrySet()
//      .stream()
//      .filter(item -> !item.getKey().equals(OAuth2ParameterNames.GRANT_TYPE) && !item.getKey().equals(OAuth2ParameterNames.SCOPE))
//      .collect(Collectors.toMap(Map.Entry::getKey, item -> item.getValue().get(0)));
//    // @formatter:on
//
//    return new OAuth2PasswordAuthenticationToken(clientPrincipal, requestedScopes, additionalParameters);
//  }
}
