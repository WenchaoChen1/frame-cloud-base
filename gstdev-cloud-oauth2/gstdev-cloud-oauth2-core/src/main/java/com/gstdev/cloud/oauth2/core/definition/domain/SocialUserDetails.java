// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.core.definition.domain;

/**
 * <p>Description: 社交登录用户信息详情 </p>
 *
 * @author : cc
 * @date : 2021/5/16 17:47
 */
public interface SocialUserDetails {

    /**
     * 获取社交登录唯一标识
     *
     * @return String
     */
    String getUuid();

    /**
     * 获取社交登录分类标识
     *
     * @return String
     */
    String getSource();

    String getPhoneNumber();

    String getAvatar();

    String getUsername();

    String getNickname();

    String getEmail();
}
