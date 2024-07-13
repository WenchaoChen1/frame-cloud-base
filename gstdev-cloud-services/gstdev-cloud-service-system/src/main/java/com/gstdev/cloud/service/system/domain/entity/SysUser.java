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
import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.SysUserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.List;


@Getter
@Setter
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
@Table(name = "sys_user", schema = "public")
//@Where(clause = "deleted = 0")
//@SQLDelete(sql = "UPDATE public.sys_user SET deleted=1 WHERE id =?")
public class SysUser extends BaseEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "user_id", length = 64, nullable = false)
    private String userId;

    @Schema(title = "用户名")
    @Column(name = "username", length = 128, nullable = false, unique = true)
    private String username;

    @Schema(title = "EMAIL")
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Schema(title = "手机号码")
    @Column(name = "phone_number", length = 256)
    private String phoneNumber;

    @Schema(title = "密码", description = "BCryptPasswordEncoder")
    @Column(name = "password", length = 500)
    private String password;

    @Schema(title = "昵称")
    @Column(name = "nick_name", length = 64)
    private String nickname;

    @Schema(title = "头像")
    @Column(name = "avatar", length = 36)
    private String avatar;

    @Schema(title = "性别")
    @Column(name = "gender", length = 1)
    private Integer gender = 0;

    @Schema(title = "账户过期日期")
    @Column(name = "account_expire_at")
    private Instant accountExpireAt;

    @Schema(title = "密码过期日期")
    @Column(name = "credentials_expire_at")
    private Instant credentialsExpireAt;

//    @Column(name = "deleted", nullable = false)
//    private Integer deleted = 0;

    @Schema(title = "数据状态")
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DataItemStatus status = DataItemStatus.ENABLE;


    //  super:0 看到所有数据最大权限,admin:1只能看到当前租户的所有权限，user：需要根据role来获取权限
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private SysUserType type = SysUserType.USER;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<SysAccount> account;


    //-----------------自定义-----------
    @Column(name = "first_name", length = 60)
    private String firstName;

    @Column(name = "last_name", length = 60)
    private String lastName;

}
