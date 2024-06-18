package com.gstdev.cloud.service.identity.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.oauth2.SecretKey;
import com.gstdev.cloud.oauth2.authorization.server.dto.SignInErrorPrompt;
import com.gstdev.cloud.oauth2.authorization.server.dto.SignInErrorStatus;
import com.gstdev.cloud.oauth2.authorization.server.stamp.SignInFailureLimitedStampManager;
import com.gstdev.cloud.service.identity.domain.dto.Session;
import com.gstdev.cloud.service.identity.domain.dto.SessionCreate;
import com.gstdev.cloud.service.identity.domain.dto.SessionExchange;
import com.gstdev.cloud.service.identity.service.InterfaceSecurityService;
import com.gstdev.cloud.rest.core.annotation.Crypto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gengwei.zheng
 * @see <a href="https://conkeyn.iteye.com/blog/2296406">参考文档</a>
 */
@RestController
//@Tags({
//    @Tag(name = "OAuth2 认证服务器接口"),
//    @Tag(name = "OAuth2 认证服务器开放接口"),
//    @Tag(name = "OAuth2 身份认证辅助接口")
//})
public class IdentityController {

    private final InterfaceSecurityService interfaceSecurityService;
    private final SignInFailureLimitedStampManager signInFailureLimitedStampManager;

    public IdentityController(InterfaceSecurityService interfaceSecurityService, SignInFailureLimitedStampManager signInFailureLimitedStampManager) {
        this.interfaceSecurityService = interfaceSecurityService;
        this.signInFailureLimitedStampManager = signInFailureLimitedStampManager;
    }

    @Operation(summary = "获取后台加密公钥", description = "根据未登录时的身份标识，在后台创建RSA/SM2公钥和私钥。身份标识为前端的唯一标识，如果为空，则在后台创建一个",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
        responses = {@ApiResponse(description = "自定义Session", content = @Content(mediaType = "application/json"))})
    @Parameters({
        @Parameter(name = "sessionCreate", required = true, description = "Session创建请求参数", schema = @Schema(implementation = SessionCreate.class)),
    })
    @PostMapping("/open/identity/session")
    public Result<Session> create(@Validated @RequestBody SessionCreate sessionCreate, HttpServletRequest request) {

        String sessionId = sessionCreate.getSessionId();
        if (StringUtils.isEmpty(sessionId)) {
            HttpSession session = request.getSession();
            sessionId = session.getId();
        }

        SecretKey secretKey = interfaceSecurityService.createSecretKey(sessionCreate.getClientId(), sessionCreate.getClientSecret(), sessionId);
        if (ObjectUtils.isNotEmpty(secretKey)) {
            Session session = new Session();
            session.setSessionId(secretKey.getIdentity());
            session.setPublicKey(secretKey.getPublicKey());
            session.setState(secretKey.getState());

            return Result.content(session);
        }

        return Result.failure();
    }

    @Operation(summary = "获取AES秘钥", description = "用后台publicKey，加密前台publicKey，到后台换取AES秘钥",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
        responses = {@ApiResponse(description = "加密后的AES", content = @Content(mediaType = "application/json"))})
    @Parameters({
        @Parameter(name = "sessionExchange", required = true, description = "秘钥交换", schema = @Schema(implementation = SessionExchange.class)),
    })
    @PostMapping("/open/identity/exchange")
    public Result<String> exchange(@Validated @RequestBody SessionExchange sessionExchange) {

        String encryptedAesKey = interfaceSecurityService.exchange(sessionExchange.getSessionId(), sessionExchange.getPublicKey());
        if (StringUtils.isNotEmpty(encryptedAesKey)) {
            return Result.content(encryptedAesKey);
        }

        return Result.failure();
    }

    @Crypto(responseEncrypt = false)
    @Operation(summary = "获取登录出错剩余次数", description = "获取登录出错剩余次数",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
        responses = {@ApiResponse(description = "加密后的AES", content = @Content(mediaType = "application/json"))})
    @Parameters({
        @Parameter(name = "signInErrorPrompt", required = true, description = "提示信息所需参数", schema = @Schema(implementation = SignInErrorPrompt.class)),
    })
    @PostMapping("/open/identity/prompt")
    public Result<SignInErrorStatus> prompt(@Validated @RequestBody SignInErrorPrompt signInErrorPrompt) {
        SignInErrorStatus signInErrorStatus = signInFailureLimitedStampManager.errorStatus(signInErrorPrompt.getUsername());
        return Result.content(signInErrorStatus);
    }
}
