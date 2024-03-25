package com.gstdev.cloud.commons.constant;

/**
 * @program: frame-cloud-base
 * @description: 默认常量合集
 * @author: wenchao.chen
 * @create: 2024/03/25 14:21
 **/
public interface DefaultConstants {

//  String DEFAULT_AUTHORIZATION_ENDPOINT = "/oauth2/authorize";
//
//  String DEFAULT_TOKEN_ENDPOINT = "/oauth2/token";
//  String DEFAULT_TOKEN_REVOCATION_ENDPOINT = "/oauth2/revoke";
//  String DEFAULT_TOKEN_INTROSPECTION_ENDPOINT = "/oauth2/introspect";
//
//  String DEFAULT_JWK_SET_ENDPOINT = "/oauth2/jwks";

//  String DEFAULT_AUTHORIZATION_ENDPOINT = "/oauth2/v1/authorize";
//  String DEFAULT_DEVICE_AUTHORIZATION_ENDPOINT = "/oauth2/v1/device_authorization";
//  String DEFAULT_DEVICE_VERIFICATION_ENDPOINT = "/oauth2/v1/device_verification";
//  String DEFAULT_TOKEN_ENDPOINT = "/oauth2/v1/token";
//  String DEFAULT_TOKEN_REVOCATION_ENDPOINT = "/oauth2/v1/revoke";
//  String DEFAULT_TOKEN_INTROSPECTION_ENDPOINT = "/oauth2/v1/introspect";
//  String DEFAULT_JWK_SET_ENDPOINT = "/oauth2/v1/jwks";
//  String DEFAULT_OIDC_LOGOUNT_ENDPOINT = "/connect/v1/logout";
//  String DEFAULT_OIDC_USER_INFO_ENDPOINT = "/connect/v1/userinfo";
//  String DEFAULT_OIDC_CLIENT_REGISTRATION_ENDPOINT = "/connect/v1/register";


  String DEFAULT_AUTHORIZATION_ENDPOINT = "/oauth2/authorize";
  String DEFAULT_DEVICE_AUTHORIZATION_ENDPOINT = "/oauth2/device_authorization";
  String DEFAULT_DEVICE_VERIFICATION_ENDPOINT = "/oauth2/device_verification";
  String DEFAULT_TOKEN_ENDPOINT = "/oauth2/token";
  String DEFAULT_TOKEN_REVOCATION_ENDPOINT = "/oauth2/revoke";
  String DEFAULT_TOKEN_INTROSPECTION_ENDPOINT = "/oauth2/introspect";
  String DEFAULT_JWK_SET_ENDPOINT = "/oauth2/jwks";
  String DEFAULT_OIDC_LOGOUNT_ENDPOINT = "/connect/logout";
  String DEFAULT_OIDC_USER_INFO_ENDPOINT = "/connect/userinfo";
  String DEFAULT_OIDC_CLIENT_REGISTRATION_ENDPOINT = "/connect/register";

  //  String DEFAULT_JKS_JKS_PATH = "jwk.jks";
//  String DEFAULT_JKS_ALIAS = "gstdev";
//  String DEFAULT_JKS_PASS = "black123";
  // 证书的路径
  String DEFAULT_JKS_JKS_PATH = "myjks.jks";
  String DEFAULT_JKS_CER_PATH = "myjks.cer";
  // 证书别名
  String DEFAULT_JKS_ALIAS = "myjks";
  // keystore 密码
  String DEFAULT_JKS_PASS = "123456";


  String DEFAULT_USER_DETAIL_URI = "";
}
