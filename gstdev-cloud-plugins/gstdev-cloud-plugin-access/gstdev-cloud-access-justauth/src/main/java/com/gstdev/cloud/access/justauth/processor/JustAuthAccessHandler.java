

package com.gstdev.cloud.access.justauth.processor;

import com.gstdev.cloud.access.core.definition.AccessHandler;
import com.gstdev.cloud.access.core.definition.AccessResponse;
import com.gstdev.cloud.access.core.definition.AccessUserDetails;
import com.gstdev.cloud.access.core.exception.AccessIdentityVerificationFailedException;
import com.gstdev.cloud.base.definition.domain.oauth2.AccessPrincipal;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.apache.commons.lang3.ObjectUtils;

/**
 * <p>Description: JustAuth 接入处理器 </p>
 *
 * @author : cc
 * @date : 2022/1/25 17:45
 */
public class JustAuthAccessHandler implements AccessHandler {

    private final JustAuthProcessor justAuthProcessor;

    public JustAuthAccessHandler(JustAuthProcessor justAuthProcessor) {
        this.justAuthProcessor = justAuthProcessor;
    }

    @Override
    public AccessResponse preProcess(String core, String... params) {
        String url = justAuthProcessor.getAuthorizeUrl(core);

        AccessResponse accessResponse = new AccessResponse();
        accessResponse.setAuthorizeUrl(url);
        return accessResponse;
    }

    @Override
    public AccessUserDetails loadUserDetails(String source, AccessPrincipal accessPrincipal) {
        AuthRequest authRequest = justAuthProcessor.getAuthRequest(source);

        AuthCallback authCallback = AuthCallback.builder()
                .code(accessPrincipal.getCode())
                .auth_code(accessPrincipal.getAuth_code())
                .state(accessPrincipal.getState())
                .authorization_code(accessPrincipal.getAuthorization_code())
                .oauth_token(accessPrincipal.getOauth_token())
                .oauth_verifier(accessPrincipal.getOauth_verifier())
                .build();

        AuthResponse<AuthUser> response = authRequest.login(authCallback);
        if (response.ok()) {
            AuthUser authUser = response.getData();
            return convertAuthUserToAccessUserDetails(authUser);
        }

        throw new AccessIdentityVerificationFailedException(response.getMsg());
    }

    private AccessUserDetails convertAuthUserToAccessUserDetails(AuthUser authUser) {
        AccessUserDetails sysSocialUser = new AccessUserDetails();
        sysSocialUser.setUuid(authUser.getUuid());
        sysSocialUser.setUsername(authUser.getUsername());
        sysSocialUser.setNickname(authUser.getNickname());
        sysSocialUser.setAvatar(authUser.getAvatar());
        sysSocialUser.setBlog(authUser.getBlog());
        sysSocialUser.setCompany(authUser.getCompany());
        sysSocialUser.setLocation(authUser.getLocation());
        sysSocialUser.setEmail(authUser.getEmail());
        sysSocialUser.setRemark(authUser.getRemark());
        sysSocialUser.setGender(authUser.getGender());
        sysSocialUser.setSource(authUser.getSource());
        AuthToken authToken = authUser.getToken();
        if (ObjectUtils.isNotEmpty(authToken)) {
            setAccessUserInfo(sysSocialUser, authToken.getAccessToken(), authToken.getExpireIn(), authToken.getRefreshToken(), authToken.getRefreshTokenExpireIn(), authToken.getScope(), authToken.getTokenType(), authToken.getUid(), authToken.getOpenId(), authToken.getAccessCode(), authToken.getUnionId());
        }

        return sysSocialUser;
    }

    private void setAccessUserInfo(AccessUserDetails accessUserDetails, String accessToken, Integer expireIn, String refreshToken, Integer refreshTokenExpireIn, String scope, String tokenType, String uid, String openId, String accessCode, String unionId) {
        accessUserDetails.setAccessToken(accessToken);
        accessUserDetails.setExpireIn(expireIn);
        accessUserDetails.setRefreshToken(refreshToken);
        accessUserDetails.setRefreshTokenExpireIn(refreshTokenExpireIn);
        accessUserDetails.setScope(scope);
        accessUserDetails.setTokenType(tokenType);
        accessUserDetails.setUid(uid);
        accessUserDetails.setOpenId(openId);
        accessUserDetails.setAccessCode(accessCode);
        accessUserDetails.setUnionId(unionId);
    }
}
