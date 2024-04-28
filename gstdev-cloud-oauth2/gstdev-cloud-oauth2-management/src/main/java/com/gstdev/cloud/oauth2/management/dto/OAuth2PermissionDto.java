package com.gstdev.cloud.oauth2.management.dto;

import com.gstdev.cloud.rest.core.definition.dto.BaseDto;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * <p>Description: OAuth2 FramePermission Dto </p>
 *
 * @author : cc
 * @date : 2022/4/1 13:55
 */
@Schema(name = "OAuth2 权限请求 Dto")
public class OAuth2PermissionDto extends BaseDto {

    @Schema(name = "权限ID")
    @NotNull(message = "权限ID不能为空")
    private String permissionId;

    @Schema(name = "权限代码")
    @NotNull(message = "权限代码不能为空")
    private String permissionCode;

    @Schema(name = "服务ID")
    @NotNull(message = "服务ID不能为空")
    private String permissionName;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("permissionId", permissionId)
            .add("permissionCode", permissionCode)
            .add("permissionName", permissionName)
            .toString();
    }
}
