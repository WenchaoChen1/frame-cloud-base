package com.gstdev.cloud.service.system.domain.entity;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.service.system.domain.generator.SysRRoleBusinessPermissionEmbeddablePK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "sys_r_role_business_permission", schema = "public")
@IdClass(SysRRoleBusinessPermissionEmbeddablePK.class)
public class SysRRoleBusinessPermission extends BaseEntity {

    @Id
    @Column(name = "role_id", length = 64)
    private String roleId;
    @Id
    @Column(name = "business_permission_id", length = 64)
    private String businessPermissionId;
}
