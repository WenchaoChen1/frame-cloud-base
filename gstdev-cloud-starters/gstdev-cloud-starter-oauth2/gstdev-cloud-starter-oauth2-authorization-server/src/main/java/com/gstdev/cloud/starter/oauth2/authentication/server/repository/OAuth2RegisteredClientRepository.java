//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev Cloud <support@gstdev.com>
//// Copyright (c) 2022-2025 gstdev.com
////
//// ====================================================
//
//package com.gstdev.cloud.starter.oauth2.authentication.server.repository;
//
//import com.gstdev.cloud.starter.oauth2.authentication.server.domain.OAuth2RegisteredClient;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface OAuth2RegisteredClientRepository extends JpaRepository<OAuth2RegisteredClient, String> {
////  /**
////   * Find by client id optional.
////   *
////   * @param clientId the client id
////   * @return the optional
////   */
////  @EntityGraph(attributePaths = {"clientAuthenticationMethods",
////    "authorizationGrantTypes",
////    "redirectUris",
////    "scopes",
////    "clientSettings",
////    "tokenSettings"})
////  Optional<OAuth2RegisteredClient> findByClientId(String clientId);
////
////  /**
////   * Find client by id o auth 2 client.
////   *
////   * @param id the id
////   * @return the o auth 2 client
////   */
////  @EntityGraph(attributePaths = {"clientAuthenticationMethods",
////    "authorizationGrantTypes",
////    "redirectUris",
////    "scopes",
////    "clientSettings",
////    "tokenSettings"})
////  OAuth2RegisteredClient searchOAuth2ClientById(String id);
//  Optional<OAuth2RegisteredClient> findByClientId(String clientId);
//}
