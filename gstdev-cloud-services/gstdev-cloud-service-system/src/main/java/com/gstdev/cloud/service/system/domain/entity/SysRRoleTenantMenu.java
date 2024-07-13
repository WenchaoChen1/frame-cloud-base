package com.gstdev.cloud.service.system.domain.entity;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.service.system.domain.generator.SysRRoleTenantMenuEmbeddablePK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "sys_r_role_tenant_menu", schema = "public")
@IdClass(SysRRoleTenantMenuEmbeddablePK.class)
public class SysRRoleTenantMenu extends BaseEntity {

    @Id
    @Column(name = "role_id", length = 64)
    private String roleId;
    @Id
    @Column(name = "tenant_menu_id", length = 64)
    private String tenantMenuId;
}
