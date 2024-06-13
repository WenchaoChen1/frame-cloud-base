package com.gstdev.cloud.service.identity.entity;

import com.gstdev.cloud.data.core.entity.BaseSysEntity;
import com.gstdev.cloud.oauth2.core.constants.OAuth2Constants;
import com.gstdev.cloud.service.identity.generator.OAuth2PermissionUuidGenerator;
import com.google.common.base.MoreObjects;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

/**
 * <p>Description: 客户端权限 </p>
 *
 * @author : cc
 * @date : 2022/4/1 13:39
 */
@Entity
@Table(name = "oauth2_permission", indexes = {@Index(name = "oauth2_permission_id_idx", columnList = "permission_id")})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = OAuth2Constants.REGION_OAUTH2_PERMISSION)
public class OAuth2Permission extends BaseSysEntity {

    @Id
    @OAuth2PermissionUuidGenerator
    @Column(name = "permission_id", length = 64)
    private String permissionId;

    @Column(name = "permission_code", length = 128)
    private String permissionCode;

    @Column(name = "permission_name", length = 128)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OAuth2Permission that = (OAuth2Permission) o;
        return Objects.equals(permissionId, that.permissionId) && Objects.equals(permissionCode, that.permissionCode) && Objects.equals(permissionName, that.permissionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), permissionId, permissionCode, permissionName);
    }
}
