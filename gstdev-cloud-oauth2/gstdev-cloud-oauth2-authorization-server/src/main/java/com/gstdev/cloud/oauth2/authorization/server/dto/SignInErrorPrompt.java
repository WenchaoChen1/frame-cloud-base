// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.authorization.server.dto;

import com.gstdev.cloud.rest.core.definition.dto.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * <p>Description: 登录提示信息 </p>
 *
 * @author : cc
 * @date : 2022/7/8 20:52
 */
@Schema(title = "登录错误提示信息")
public class SignInErrorPrompt extends BaseDto {

    @NotBlank(message = "登录用户名不能为空")
    @Schema(title = "登录用户名", description = "必须是有效的用户名")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
