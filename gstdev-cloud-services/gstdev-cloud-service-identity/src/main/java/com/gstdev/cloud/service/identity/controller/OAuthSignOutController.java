package com.gstdev.cloud.service.identity.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.message.core.logic.event.AccountReleaseFromCacheEvent;
import com.gstdev.cloud.service.identity.domain.dto.SignOut;
import com.gstdev.cloud.service.identity.service.OAuth2ComplianceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: OAuth2 扩展 接口 </p>
 *
 * @author : cc
 * @date : 2022/7/7 17:05
 */
@RestController
@RequestMapping("/oauth2")
//@Tags({
//    @Tag(name = "OAuth2 认证服务接口"),
//    @Tag(name = "OAuth2 扩展接口")
//})
public class OAuthSignOutController {

    @Resource
    private OAuth2AuthorizationService authorizationService;
    @Resource
    private OAuth2ComplianceService complianceService;
    @Resource
    private ApplicationContext applicationContext;

//    public OAuthSignOutController(OAuth2AuthorizationService authorizationService, OAuth2ComplianceService complianceService, ApplicationContext applicationContext) {
//        this.authorizationService = authorizationService;
//        this.complianceService = complianceService;
//        this.applicationContext = applicationContext;
//    }

    @Operation(summary = "注销OAuth2应用", description = "根据接收到的AccessToken,删除后端存储的Token信息,起到注销效果",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/x-www-form-urlencoded")),
            responses = {@ApiResponse(description = "是否成功", content = @Content(mediaType = "application/json"))})
    @Parameters({
            @Parameter(name = "accessToken", required = true, description = "Access Token"),
            @Parameter(name = "Authorization", in = ParameterIn.HEADER, required = true, description = "Basic Token"),
    })
    @PostMapping("/sign-out")
    public Result<String> signOut(@RequestBody SignOut signOut, HttpServletRequest request) {
        if (ObjectUtils.isEmpty(signOut.getAccessToken())) {
            return Result.failure("access token is empty");
        }
        OAuth2Authorization authorization = authorizationService.findByToken(signOut.getAccessToken(), OAuth2TokenType.ACCESS_TOKEN);
        if (ObjectUtils.isNotEmpty(authorization)) {
            authorizationService.remove(authorization);
            complianceService.save(authorization.getPrincipalName(), authorization.getRegisteredClientId(), "Logout", request);
            applicationContext.publishEvent(new AccountReleaseFromCacheEvent(authorization.getPrincipalName()));
            return Result.success("Logout successful");
        }
        return Result.failure("Opt out failed.");
    }
}
