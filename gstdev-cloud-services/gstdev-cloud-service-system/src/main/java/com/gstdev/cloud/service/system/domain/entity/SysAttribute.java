package com.gstdev.cloud.service.system.domain.entity;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.generator.SysAttributeUuidGenerator;
import com.gstdev.cloud.service.system.domain.listener.SysAttributeEntityListener;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: 系统安全属性实体 </p>
 */
@Schema(title = "系统安全属性数据")
@Getter
@Setter
@Entity
@Table(name = "sys_attribute", indexes = {@Index(name = "sys_attribute_id_idx", columnList = "attribute_id")})
@EntityListeners(value = {SysAttributeEntityListener.class})
public class SysAttribute extends BaseEntity {

    @Schema(title = "元数据ID")
    @Id
    @SysAttributeUuidGenerator
    @Column(name = "attribute_id", length = 64)
    private String attributeId;

    @Schema(title = "默认权限代码")
    @Column(name = "attribute_code", length = 128)
    private String attributeCode;

    @Schema(title = "请求方法")
    @Column(name = "request_method", length = 20)
    private String requestMethod;

    @Schema(title = "服务ID")
    @Column(name = "service_id", length = 128)
    private String serviceId;

    @Schema(title = "接口所在类")
    @Column(name = "class_name", length = 512)
    private String className;

    @Schema(title = "接口对应方法")
    @Column(name = "method_name", length = 128)
    private String methodName;

    @Schema(title = "请求URL")
    @Column(name = "url", length = 2048)
    private String url;

    @Schema(title = "表达式", description = "Security表达式字符串，通过该值设置动态权限")
    @Column(name = "web_expression", length = 128)
    private String webExpression;

    /**
     * 角色描述,UI界面显示使用
     */
    @Schema(title = "备注")
    @Column(name = "description", length = 512)
    private String description;

    @Schema(title = "数据状态")
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DataItemStatus status = DataItemStatus.ENABLE;


    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "sys_r_attribute_menu",
            joinColumns = {@JoinColumn(name = "attribute_id", referencedColumnName = "attribute_id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"attribute_id", "menu_id"})},
            indexes = {@Index(name = "sys_attribute_menu_aid_idx", columnList = "attribute_id"), @Index(name = "sys_attribute_menu_mid_idx", columnList = "menu_id")})
    private Set<SysMenu> menus = new HashSet<>();

    @Schema(title = "属性对应权限", description = "根据属性关联权限数据")
    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "sys_r_attribute_permission",
        joinColumns = {@JoinColumn(name = "attribute_id")},
        inverseJoinColumns = {@JoinColumn(name = "permission_id")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"attribute_id", "permission_id"})},
        indexes = {@Index(name = "sys_attribute_permission_aid_idx", columnList = "attribute_id"), @Index(name = "sys_attribute_permission_pid_idx", columnList = "permission_id")})
    private Set<SysPermission> permissions = new HashSet<>();

    public void addPermissions(Set<SysPermission> permissions) {
        this.permissions.addAll(permissions);
    }

    public void addPermissions(SysPermission permissions) {
        this.permissions.add(permissions);
    }

    public void addMenus(Set<SysMenu> menus) {
        this.menus.addAll(menus);
    }

    public void addMenus(SysMenu menus) {
        this.menus.add(menus);
    }
}
