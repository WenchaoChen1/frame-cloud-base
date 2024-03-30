package com.gstdev.cloud.oauth2.authentication.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface AuthorizationRepository extends JpaRepository<com.gstdev.cloud.oauth2.authentication.domain.Authorization, String> {
  Optional<com.gstdev.cloud.oauth2.authentication.domain.Authorization> findByState(String state);

  Optional<com.gstdev.cloud.oauth2.authentication.domain.Authorization> findByAuthorizationCodeValue(String authorizationCode);

  Optional<com.gstdev.cloud.oauth2.authentication.domain.Authorization> findByAccessTokenValue(String accessToken);

  Optional<com.gstdev.cloud.oauth2.authentication.domain.Authorization> findByRefreshTokenValue(String refreshToken);

  Optional<com.gstdev.cloud.oauth2.authentication.domain.Authorization> findByOidcIdTokenValue(String idToken);

  Optional<com.gstdev.cloud.oauth2.authentication.domain.Authorization> findByUserCodeValue(String userCode);

  Optional<com.gstdev.cloud.oauth2.authentication.domain.Authorization> findByDeviceCodeValue(String deviceCode);

//  @Query("select a from oauth2_authorization  a where a.state = :token" +
  @Query("select a from Authorization  a where a.state = :token" +
    " or a.authorizationCodeValue = :token" +
    " or a.accessTokenValue = :token" +
    " or a.refreshTokenValue = :token" +
    " or a.oidcIdTokenValue = :token" +
    " or a.userCodeValue = :token" +
    " or a.deviceCodeValue = :token"
  )
  Optional<com.gstdev.cloud.oauth2.authentication.domain.Authorization> findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValueOrOidcIdTokenValueOrUserCodeValueOrDeviceCodeValue(@Param("token") String token);
}
