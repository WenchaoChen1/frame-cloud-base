package com.gstdev.cloud.service.system.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.generator.SysBusinessPermissionUuidGenerator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

/**
 * <p>Description: 系统权限实体 </p>
 */
@Getter
@Setter
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
@Table(name = "sys_business_permission", schema = "public")
public class SysBusinessPermission extends BaseEntity {

    @Id
    @SysBusinessPermissionUuidGenerator
    @Column(name = "business_permission_id", length = 64)
    private String businessPermissionId;

    @Column(name = "parent_id", length = 64, nullable = false)
    private String parentId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "tenant_id", length = 64, nullable = false)
    private String tenantId;

    @Column(name = "code", length = 50, nullable = false)
    private String code;

    @Schema(title = "数据状态")
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DataItemStatus status = DataItemStatus.ENABLE;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "businessPermissions",  fetch = FetchType.LAZY)
    private List<SysTenantMenu> sysTenantMenus;
    @JsonIgnore
    @ManyToMany(mappedBy = "businessPermissions",  fetch = FetchType.LAZY)
    private List<SysRole> sysRoles;
    @JsonIgnore
    @ManyToMany(mappedBy = "businessPermissions",  fetch = FetchType.LAZY)
    private List<SysAccount> sysAccounts;
}
