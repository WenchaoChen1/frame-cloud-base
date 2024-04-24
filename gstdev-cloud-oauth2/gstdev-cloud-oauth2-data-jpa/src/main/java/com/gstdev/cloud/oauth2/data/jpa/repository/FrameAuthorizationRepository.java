package com.gstdev.cloud.oauth2.data.jpa.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.oauth2.data.jpa.entity.FrameAuthorization;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * <p>Description: FrameAuthorizationRepository </p>
 *
 * @author : cc
 * @date : 2022/2/25 21:05
 */
@Repository
public interface FrameAuthorizationRepository extends BaseRepository<FrameAuthorization, String> {

  /**
   * 根据 State 查询 OAuth2 认证信息
   *
   * @param state OAuth2 Authorization Code 模式参数 State
   * @return OAuth2 认证信息 {@link FrameAuthorization}
   */
  @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
  Optional<FrameAuthorization> findByState(String state);

  /**
   * 根据 authorizationCode 查询 OAuth2 认证信息
   *
   * @param authorizationCode OAuth2 Authorization Code 模式参数 code
   * @return OAuth2 认证信息 {@link FrameAuthorization}
   */
  @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
  Optional<FrameAuthorization> findByAuthorizationCodeValue(String authorizationCode);

  /**
   * 根据 Access Token 查询 OAuth2 认证信息
   *
   * @param accessToken OAuth2 accessToken
   * @return OAuth2 认证信息 {@link FrameAuthorization}
   */
  @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
  Optional<FrameAuthorization> findByAccessTokenValue(String accessToken);

  /**
   * 根据 Refresh Token 查询 OAuth2 认证信息
   *
   * @param refreshToken OAuth2 refreshToken
   * @return OAuth2 认证信息 {@link FrameAuthorization}
   */
  @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
  Optional<FrameAuthorization> findByRefreshTokenValue(String refreshToken);

  /**
   * 根据 Id Token 查询 OAuth2 认证信息
   *
   * @param idToken OAuth2 idToken
   * @return OAuth2 认证信息 {@link FrameAuthorization}
   */
  @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
  Optional<FrameAuthorization> findByOidcIdTokenValue(String idToken);

  /**
   * 根据 User Code 查询 OAuth2 认证信息
   *
   * @param userCode OAuth2 userCode
   * @return OAuth2 认证信息 {@link FrameAuthorization}
   */
  @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
  Optional<FrameAuthorization> findByUserCodeValue(String userCode);

  /**
   * 根据 Device Code 查询 OAuth2 认证信息
   *
   * @param deviceCode OAuth2 deviceCode
   * @return OAuth2 认证信息 {@link FrameAuthorization}
   */
  @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
  Optional<FrameAuthorization> findByDeviceCodeValue(String deviceCode);

  /**
   * 根据客户端ID和用户名查询未过期Token
   *
   * @param registeredClientId 客户端ID
   * @param principalName      用户名称
   * @param instant      时间
   * @return 认证信息列表
   */
  List<FrameAuthorization> findAllByRegisteredClientIdAndPrincipalNameAndAccessTokenExpiresAtAfter(String registeredClientId, String principalName, Instant instant);

  /**
   * 根据 RefreshToken 过期时间，清理历史 Token信息
   * <p>
   * OAuth2Authorization 表中存在 AccessToken、OidcToken、RefreshToken 等三个过期时间。
   * 正常的删除逻辑应该是三个过期时间都已经过期才行。但是有特殊情况：
   * 1. OidcToken 的过期时间有可能为空，这就增加了 SQL 处理的复杂度。
   * 2. 逻辑上 RefreshToken 的过期应该是最长的(这是默认配置正确的情况)
   * 因此，目前就简单的根据 RefreshToken过期时间进行处理
   *
   * @param instant 时间
   */
  @Modifying
  @Transactional
  void deleteByRefreshTokenExpiresAtBefore(Instant instant);
}
