// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.entity;

import com.gstdev.cloud.data.core.entity.BaseTreeEntity;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.SysTenantPermissionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.EnumSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "sys_tenant", schema = "public")
//@Where(clause = "deleted = 0")
//@SQLDelete(sql = "UPDATE tenant SET deleted=1 WHERE id =?")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
public class SysTenant extends BaseTreeEntity {

    @Column(name = "parent_id", length = 36)
    private String parentId;

    @Column(name = "tenant_code", length = 50, nullable = false)
    private String tenantCode;

    @Column(name = "tenant_name", length = 100)
    private String tenantName;

    @Column(name = "description", length = 1000)
    private String description;

    @Schema(title = "数据状态")
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DataItemStatus status = DataItemStatus.ENABLE;

    @Column(name = "type")//comment 'development 0;platform 1;tenant 2'
    private Integer type = 0;


    @ElementCollection(targetClass = SysTenantPermissionType.class)
    @CollectionTable(name = "sys_tenant_permission_type", joinColumns = @JoinColumn(name = "tenant_id"))
    @Column(name = "tenant_permission_type")
    @Enumerated(EnumType.ORDINAL) // 使用字符串类型存储枚举值
    private Set<SysTenantPermissionType> tenantPermissionTypes= EnumSet.of(SysTenantPermissionType.ACCOUNT_TYPE);
//    @Column(name = "deleted")
//    private Integer deleted = 0;

//-----------------自定义-----------


}
