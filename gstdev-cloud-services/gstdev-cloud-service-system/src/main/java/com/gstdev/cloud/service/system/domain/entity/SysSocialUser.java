/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君), <herodotus@aliyun.com> Licensed under the AGPL License
 *
 * This file is part of Dante Engine.
 *
 * Dante Engine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dante Engine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.herodotus.cn>.
 */

package com.gstdev.cloud.service.system.domain.entity;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.oauth2.core.definition.domain.SocialUserDetails;
import com.gstdev.cloud.service.system.constants.SystemConstants;
import com.gstdev.cloud.service.system.domain.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: 社会化登录用户 </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/5/16 14:40
 */
@Getter
@Setter
@Entity
@Schema(title = "社会化登录用户")
@Table(name = "sys_social_user", indexes = {@Index(name = "sys_social_user_id_idx", columnList = "social_id")})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = SystemConstants.REGION_SYS_SOCIAL_USER)
public class SysSocialUser extends BaseEntity implements SocialUserDetails {

    @Schema(title = "社会用户ID")
    @Id
    @UuidGenerator
    @Column(name = "social_user_id", length = 64)
    private String socialUserId;

    /**
     * JustAuth中的关键词
     * 以下内容了解后，将会使你更容易地上手JustAuth。
     * <p>
     * source JustAuth支持的第三方平台，比如：GITHUB、GITEE等
     * uuid 一般为第三方平台的用户ID。以下几个平台需特别注意：
     * 钉钉、抖音：uuid 为用户的 unionid
     * 微信公众平台登录、京东、酷家乐、美团：uuid 为用户的 openId
     * 微信开放平台登录、QQ：uuid 为用户的 openId，平台支持获取unionid， unionid 在 AuthToken 中（如果支持），在登录完成后，可以通过 response.getData().getToken().getUnionId() 获取
     * Google：uuid 为用户的 sub，sub为Google的所有账户体系中用户唯一的身份标识符，详见：OpenID Connect (opens new window)
     * 注：建议通过uuid + source的方式唯一确定一个用户，这样可以解决用户身份归属的问题。因为 单个用户ID 在某一平台中是唯一的，但不能保证在所有平台中都是唯一的。
     */
    @Schema(title = "用户第三方系统的唯一id", description = "在调用方集成该组件时，可以用uuid + source唯一确定一个用")
    @Column(name = "uuid", length = 64)
    private String uuid;

    @Schema(title = "用户名")
    @Column(name = "user_name", length = 50)
    private String username;

    @Schema(title = "用户昵称")
    @Column(name = "nick_name", length = 50)
    private String nickname;

    @Schema(title = "用户头像")
    @Column(name = "avatar", length = 1000)
    private String avatar;

    @Schema(title = "用户网址")
    @Column(name = "blog", length = 100)
    private String blog;

    @Schema(title = "所在公司")
    @Column(name = "company", length = 256)
    private String company;

    @Schema(title = "位置")
    @Column(name = "location", length = 512)
    private String location;

    @Schema(title = "用户邮箱")
    @Column(name = "email", length = 50)
    private String email;

    @Schema(title = "用户邮箱")
    @Column(name = "remark", length = 512)
    private String remark;
    /**
     * 性别
     */
    @Schema(title = "性别")
    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Gender gender= Gender.MALE;

    @Schema(title = "第三方用户来源")
    @Column(name = "source")
    private String source;


    @Schema(title = "用户的授权令牌")
    @Column(name = "access_token", columnDefinition = "TEXT")
    private String accessToken;

    @Schema(title = "第三方用户的授权令牌的有效期", description = "部分平台可能没有")
    @Column(name = "expire_in")
    private Integer expireIn;

    @Schema(title = "刷新令牌", description = "部分平台可能没有")
    @Column(name = "refresh_token", columnDefinition = "TEXT")
    private String refreshToken;

    @Schema(title = "第三方用户的刷新令牌的有效期", description = "部分平台可能没有")
    @Column(name = "refresh_token_expire_in")
    private Integer refreshTokenExpireIn;

    @Schema(title = "第三方用户授予的权限", description = "部分平台可能没有")
    @Column(name = "scope", length = 1200)
    private String scope;

    @Schema(title = "个别平台的授权信息", description = "部分平台可能没有")
    @Column(name = "token_type", length = 100)
    private String tokenType;

    @Schema(title = "第三方用户的 ID", description = "部分平台可能没有")
    @Column(name = "uid", length = 64)
    private String uid;

    @Schema(title = "第三方用户的 open id", description = "部分平台可能没有")
    @Column(name = "open_id", length = 64)
    private String openId;

    @Schema(title = "个别平台的授权信息", description = "部分平台可能没有")
    @Column(name = "access_code", length = 64)
    private String accessCode;

    @Schema(title = "第三方用户的 union id", description = "部分平台可能没有")
    @Column(name = "union_id", length = 64)
    private String unionId;

    @Schema(title = "小程序Appid", description = "部分平台可能没有")
    @Column(name = "app_id", length = 64)
    private String appId;

    @Schema(title = "手机号码", description = "部分平台可能没有")
    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

//    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = UpmsConstants.REGION_SYS_USER)
    @Schema(title = "系统用户")
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "sys_user_sys_social_user",
            joinColumns = {@JoinColumn(name = "social_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"social_user_id", "user_id"})},
            indexes = {@Index(name = "sys_user_sys_social_user_oid_idx", columnList = "social_user_id"), @Index(name = "sys_user_sys_social_user_uid_idx", columnList = "user_id")})
    private Set<SysUser> users = new HashSet<>();

}
