package com.gstdev.cloud.service.system.domain.entity;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.service.system.domain.generator.SysRAccountTenantMenuEmbeddablePK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "sys_r_account_tenant_menu", schema = "public")
@IdClass(SysRAccountTenantMenuEmbeddablePK.class)
public class SysRAccountTenantMenu extends BaseEntity {

    @Id
    @Column(name = "account_id", length = 64)
    private String accountId;
    @Id
    @Column(name = "tenant_menu_id", length = 64)
    private String tenantMenuId;
}
