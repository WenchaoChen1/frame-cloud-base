package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.service.common.autoconfigure.currentLoginInformation.CurrentLoginInformation;
import com.gstdev.cloud.service.system.service.SysSecurityService;
import com.gstdev.cloud.service.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/security")
public class SysSecurityController {
    
    @Resource
    @Lazy
    private SysUserService userService;
    @Resource
    @Lazy
    private SysSecurityService sysSecurityService;

    @Tag(name = "Login")
    @Operation(summary = "get-account-current-login-information")
    @GetMapping("/get-account-current-login-information")
    public Result<CurrentLoginInformation> getAccountCurrentLoginInformation(String accountId) {
        return Result.success(sysSecurityService.getAccountCurrentLoginInformation(accountId));
    }

    @Tag(name = "Login")
    @Operation(summary = "update-account-current-login-information")
    @PutMapping("/update-account-current-login-information")
    public Result<CurrentLoginInformation> updateAccountCurrentLoginInformation(String accountId) {
        return Result.success(sysSecurityService.updateAccountCurrentLoginInformation(accountId));
    }


    @Tag(name = "User Settings")
    @Operation(summary = "reset-password")
    @PutMapping("/reset-password/{originalPassword}/{newPassword}")
    public Result<String> resetPassword(@NotBlank @PathVariable String originalPassword, @NotBlank @PathVariable String newPassword) {
        userService.resetPassword(originalPassword, newPassword);
        return Result.success();
    }

    @Tag(name = "User Manage")
    @Operation(summary = "user-manage-reset-password")
    @PutMapping("/user-manage-reset-password/{newPassword}/{userId}")
    public Result<String> userManageResetPaaword(@NotBlank @PathVariable String newPassword, @NotBlank @PathVariable String userId) {
        userService.userManageResetPaaword(newPassword, userId);
        return Result.success();
    }

    @Tag(name = "security sign-in")
    @GetMapping("/feign/sign-in/{username}")
    @Operation(summary = "内部调用 feign 根据username获取实体数据")
    public Result<DefaultSecurityUser> feignSignInFindByUsername(@NotBlank @PathVariable("username") String username) {
        DefaultSecurityUser defaultSecurityUser = userService.signInFindByUsername(username);
        return Result.success(defaultSecurityUser);
    }
}
