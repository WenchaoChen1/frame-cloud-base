// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gstdev.cloud.data.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Table(name = "sys_tenant_menu", schema = "public")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
public class SysTenantMenu extends BaseEntity {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "tenant_menu_id", length = 64)
    private String tenantMenuId;

    @Column(name = "tenant_id", length = 36, nullable = false)
    private String tenantId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private SysMenu menu;

    @Column(name = "status")
    private Integer status;

    /**
     * 0半选 1全选
     */
    @Column(name = "checked", length = 1)
    private Integer checked;

    @JsonIgnore
    @ManyToMany(mappedBy = "tenantMenus")
    private List<SysRole> roles;
    @JsonIgnore
    @ManyToMany(mappedBy = "tenantMenus",  fetch = FetchType.LAZY)
    private List<SysAccount> accounts;

    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "sys_r_tenant_menu_business_permission",
        joinColumns = {@JoinColumn(name = "tenant_menu_id", referencedColumnName = "tenant_menu_id")},
        inverseJoinColumns = {@JoinColumn(name = "business_permission_id", referencedColumnName = "business_permission_id")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"tenant_menu_id", "business_permission_id"})},
        indexes = {@Index(name = "sys_tenant_menu_business_permission_tmid_idx", columnList = "tenant_menu_id"), @Index(name = "sys_tenant_menu_business_permission_bpid_idx", columnList = "business_permission_id")})
    private Set<SysBusinessPermission> businessPermissions = new HashSet<>();

}
