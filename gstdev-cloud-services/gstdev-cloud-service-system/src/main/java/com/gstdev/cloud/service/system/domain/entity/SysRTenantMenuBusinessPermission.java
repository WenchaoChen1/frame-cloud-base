package com.gstdev.cloud.service.system.domain.entity;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.service.system.domain.generator.SysRTenantMenuBusinessPermissionEmbeddablePK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "sys_r_tenant_menu_business_permission", schema = "public")
@IdClass(SysRTenantMenuBusinessPermissionEmbeddablePK.class)
public class SysRTenantMenuBusinessPermission extends BaseEntity {

    @Id
    @Column(name = "tenant_menu_id", length = 64)
    private String tenantMenuId;
    @Id
    @Column(name = "business_permission_id", length = 64)
    private String businessPermissionId;
}
