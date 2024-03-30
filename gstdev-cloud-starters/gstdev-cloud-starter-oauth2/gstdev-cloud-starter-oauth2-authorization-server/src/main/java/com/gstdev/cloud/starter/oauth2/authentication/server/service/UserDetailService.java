// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.starter.oauth2.authentication.server.service;

import com.gstdev.cloud.starter.oauth2.authentication.server.model.UserAuth;

public interface UserDetailService<T> {
  UserAuth loadUserByUsername(String username);

  T loadUserByUserId(String userId);
}
