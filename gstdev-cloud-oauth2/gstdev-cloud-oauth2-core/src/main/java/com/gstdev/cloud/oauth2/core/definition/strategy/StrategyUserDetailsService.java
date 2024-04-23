// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.core.definition.strategy;

import com.gstdev.cloud.base.definition.domain.oauth2.AccessPrincipal;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import org.springframework.security.core.AuthenticationException;

/**
 * <p>Description: 系统用户服务策略定义 </p>
 *
 * @author : cc
 * @date : 2022/2/17 10:52
 */
public interface StrategyUserDetailsService {

  DefaultSecurityUser findUserDetailsByUsername(String username) throws AuthenticationException;

  DefaultSecurityUser findUserDetailsBySocial(String source, AccessPrincipal accessPrincipal) throws AuthenticationException;
}
