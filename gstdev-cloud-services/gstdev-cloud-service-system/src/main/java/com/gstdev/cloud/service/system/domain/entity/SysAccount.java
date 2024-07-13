package com.gstdev.cloud.service.system.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: zcy
 * @date: 2022/12/6
 * @description: 账户
 */
@Getter
@Setter
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
@Table(name = "sys_account", schema = "public")
//@Where(clause = "is_deleted = false")
//@SQLDelete(sql = "UPDATE sys_account SET is_deleted=true WHERE id =?")
public class SysAccount extends BaseEntity {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "account_id", length = 64, nullable = false)
    private String accountId;
    @Column(name = "tenant_id", length = 36, nullable = false)
    private String tenantId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "identity", length = 100)
    private String identity;

    //  super:0 看到所有数据最大权限,admin:1只能看到当前租户的所有权限，user：需要根据role来获取权限
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private SysAccountType type = SysAccountType.USER;

    @Schema(title = "数据状态")
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DataItemStatus status = DataItemStatus.ENABLE;

//    @Column(name = "is_deleted", nullable = false)
//    private Boolean isDeleted = false;


//    @ElementCollection(targetClass = SysAccountPermissionType.class)
//    @CollectionTable(name = "sys_account_permission_type", joinColumns = @JoinColumn(name = "account_id"))
//    @Column(name = "account_permission_type")
//    @Enumerated(EnumType.STRING) // 使用字符串类型存储枚举值
//    private Set<SysAccountPermissionType> accountPermissionTypes= EnumSet.of(SysAccountPermissionType.ACCOUNT_TYPE);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private SysUser user;

    @JsonIgnore
    @ManyToMany(mappedBy = "accounts")
    private List<SysDepart> departs;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
//    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "sys_r_account_role", joinColumns = {
            @JoinColumn(name = "account_id", referencedColumnName = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"account_id", "role_id"})},
            indexes = {@Index(name = "sys_account_role_aid_idx", columnList = "account_id"), @Index(name = "sys_account_role_rid_idx", columnList = "role_id")})
    private List<SysRole> roles ;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
//    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "sys_r_account_tenant_menu",
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "tenant_menu_id", referencedColumnName = "tenant_menu_id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"account_id", "tenant_menu_id"})},
            indexes = {@Index(name = "sys_account_tenant_menu_aid_idx", columnList = "account_id"), @Index(name = "sys_account_tenant_menu_tmid_idx", columnList = "tenant_menu_id")})
    private Set<SysTenantMenu> tenantMenus = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
//    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "sys_r_account_business_permission",
        joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "account_id")},
        inverseJoinColumns = {@JoinColumn(name = "business_permission_id", referencedColumnName = "business_permission_id")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"account_id", "business_permission_id"})},
        indexes = {@Index(name = "sys_account_business_permission_aid_idx", columnList = "account_id"), @Index(name = "sys_account_business_permission_bpid_idx", columnList = "business_permission_id")})
    private Set<SysBusinessPermission> businessPermissions = new HashSet<>();

}
