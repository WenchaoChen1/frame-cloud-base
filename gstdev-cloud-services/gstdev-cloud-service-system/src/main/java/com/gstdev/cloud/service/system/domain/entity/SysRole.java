// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.entity;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Table(name = "sys_role", schema = "public")
//@Where(clause = "deleted = 0")
//@SQLDelete(sql = "UPDATE sys_role SET deleted=1 WHERE id =?")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
public class SysRole extends BaseEntity {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "role_id", length = 64, nullable = false)
    private String roleId;

    @Column(name = "parent_id", length = 64, nullable = false)
    private String parentId;

    @Column(name = "tenant_id", length = 64, nullable = false)
    private String tenantId;

    @Column(name = "role_name", length = 50, nullable = false)
    private String roleName;

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

    //    @Column(name = "deleted", nullable = false)
//    private Integer deleted = 0;

    @ManyToMany(mappedBy = "roles")
    private List<SysAccount> accounts;

    @ManyToMany
    @JoinTable(name = "sys_r_role_tenant_menu", joinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "role_id")}, inverseJoinColumns = {
            @JoinColumn(name = "tenant_menu_id", referencedColumnName = "tenant_menu_id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id", "tenant_menu_id"})},
            indexes = {@Index(name = "sys_role_tenant_menu_rid_idx", columnList = "role_id"), @Index(name = "sys_role_tenant_menu_tmid_idx", columnList = "tenant_menu_id")})
    private Set<SysTenantMenu> tenantMenus = new HashSet<>();

    /**
     * 用户 - 角色关系定义:
     * (1) 加上fetch=FetchType.LAZY  或 @Fetch(FetchMode.SELECT), 输出结果与上面相同，说明默认设置是fetch=FetchType.LAZY 和 @Fetch(FetchMode.SELECT) 下面四种配置等效，都是N+1条sql的懒加载
     * (2) 加上fetch=FetchType.Eager 和 @Fetch(FetchMode.SELECT), 同样是N+1条sql，不过和上面情况不同的是，N条sql会在criteria.list()时执行
     * (3) 加上@Fetch(FetchMode.JOIN), 那么Hibernate将强行设置为fetch=FetchType.EAGER, 用户设置fetch=FetchType.LAZY将不会生效
     * 从输出可看出，在执行criteria.list()时通过一条sql 获取了所有的City和Hotel。
     * 使用@Fetch(FetchMode.JOIN)需要注意的是：它在Join查询时是Full Join, 所以会有重复City出现
     * (4) 加上@Fetch(FetchMode.SUBSELECT), 那么Hibernate将强行设置为fetch=FetchType.EAGER, 用户设置fetch=FetchType.LAZY将不会生效 从输出可看出，在执行criteria.list()时通过两条sql分别获取City和Hotel
     * <p>
     * {@link :https://www.jianshu.com/p/23bd82a7b96e}
     */

//    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = .REGION_SYS_PERMISSION)
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "sys_r_role_r_permission",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id", "permission_id"})},
            indexes = {@Index(name = "sys_role_permission_rid_idx", columnList = "role_id"), @Index(name = "sys_role_permission_pid_idx", columnList = "permission_id")})
    private Set<SysPermission> permissions = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
//    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "sys_r_role_business_permission",
        joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")},
        inverseJoinColumns = {@JoinColumn(name = "business_permission_id", referencedColumnName = "business_permission_id")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id", "business_permission_id"})},
        indexes = {@Index(name = "sys_role_business_permission_rid_idx", columnList = "role_id"), @Index(name = "sys_role_business_permission_bpid_idx", columnList = "business_permission_id")})
    private Set<SysBusinessPermission> businessPermissions = new HashSet<>();

}



