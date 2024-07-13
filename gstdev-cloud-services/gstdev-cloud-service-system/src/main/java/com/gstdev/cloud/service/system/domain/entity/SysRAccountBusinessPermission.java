package com.gstdev.cloud.service.system.domain.entity;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.service.system.domain.generator.SysRAccountBusinessPermissionEmbeddablePK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "sys_r_account_business_permission", schema = "public")
@IdClass(SysRAccountBusinessPermissionEmbeddablePK.class)
public class SysRAccountBusinessPermission extends BaseEntity {

    @Id
    @Column(name = "account_id", length = 64)
    private String accountId;
    @Id
    @Column(name = "business_permission_id", length = 64)
    private String businessPermissionId;
}
