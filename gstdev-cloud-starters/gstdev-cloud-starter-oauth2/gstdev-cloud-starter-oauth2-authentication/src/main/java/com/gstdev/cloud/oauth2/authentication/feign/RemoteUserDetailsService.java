// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.authentication.feign;

import com.gstdev.cloud.commons.constant.DefaultConstants;
import com.gstdev.cloud.commons.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = DefaultConstants.DEFAULT_USER_DETAIL_URI)
public interface RemoteUserDetailsService {

  @GetMapping("/system/users/userInfo")
  Result<Object> getUserInfoById(@RequestParam("id") String id);
}
