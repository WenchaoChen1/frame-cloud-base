// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.oauth2.server.authorization.constant;

public interface OAuth2Constants {

  String DEFAULT_AUTHORIZATION_ENDPOINT = "/oauth2/authorize";

  String DEFAULT_TOKEN_ENDPOINT = "/oauth2/token";
  String DEFAULT_TOKEN_REVOCATION_ENDPOINT = "/oauth2/revoke";
  String DEFAULT_TOKEN_INTROSPECTION_ENDPOINT = "/oauth2/introspect";

  String DEFAULT_JWK_SET_ENDPOINT = "/oauth2/jwks";

  String DEFAULT_JKS_JKS_PATH = "jwk.jks";
  String DEFAULT_JKS_ALIAS = "gstdev";
  String DEFAULT_JKS_PASS = "black123";
//  // 证书的路径
//  String DEFAULT_JKS_JKS_PATH = "myjks.jks";
  String DEFAULT_JKS_CER_PATH = "myjks.cer";
//  // 证书别名
//  String DEFAULT_JKS_ALIAS = "myjks";
//  // keystore 密码
//  String DEFAULT_JKS_PASS = "123456";


  String DEFAULT_USER_DETAIL_URI = "";
}
