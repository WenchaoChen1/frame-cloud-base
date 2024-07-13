package com.gstdev.cloud.service.system.domain.entity;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.service.system.domain.generator.SysRAccountRoleEmbeddablePK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "sys_r_account_role", schema = "public")
@IdClass(SysRAccountRoleEmbeddablePK.class)
public class SysRAccountRole extends BaseEntity {

    @Id
    @Column(name = "account_id", length = 64)
    private String accountId;
    @Id
    @Column(name = "role_id", length = 64)
    private String roleId;
}
