// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.core.constants;

/**
 * <p>Description: 扩展 OAuth2 错误代码 </p>
 *
 * @author : cc
 * @date : 2022/7/9 12:59
 */
public interface OAuth2ErrorKeys {

  String INVALID_REQUEST = "invalid_request";
  String UNAUTHORIZED_CLIENT = "unauthorized_client";
  String ACCESS_DENIED = "access_denied";
  String UNSUPPORTED_RESPONSE_TYPE = "unsupported_response_type";
  String INVALID_SCOPE = "invalid_scope";
  String INSUFFICIENT_SCOPE = "insufficient_scope";
  String INVALID_TOKEN = "invalid_token";
  String SERVER_ERROR = "server_error";
  String TEMPORARILY_UNAVAILABLE = "temporarily_unavailable";
  String INVALID_CLIENT = "invalid_client";
  String INVALID_GRANT = "invalid_grant";
  String UNSUPPORTED_GRANT_TYPE = "unsupported_grant_type";
  String UNSUPPORTED_TOKEN_TYPE = "unsupported_token_type";
  String INVALID_REDIRECT_URI = "invalid_redirect_uri";

  String ACCOUNT_EXPIRED = "AccountExpiredException";
  String ACCOUNT_DISABLED = "DisabledException";
  String ACCOUNT_LOCKED = "LockedException";
  String ACCOUNT_ENDPOINT_LIMITED = "AccountEndpointLimitedException";
  String BAD_CREDENTIALS = "BadCredentialsException";
  String CREDENTIALS_EXPIRED = "CredentialsExpiredException";
  String USERNAME_NOT_FOUND = "UsernameNotFoundException";

  String SESSION_EXPIRED = "SessionExpiredException";

}
