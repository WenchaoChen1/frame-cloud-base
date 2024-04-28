package com.gstdev.cloud.oauth2.data.jpa.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.oauth2.data.jpa.entity.FrameAuthorizationConsent;
import com.gstdev.cloud.oauth2.data.jpa.generator.FrameAuthorizationConsentId;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>Description: FrameAuthorizationConsentRepository </p>
 *
 * @author : cc
 * @date : 2022/2/25 20:54
 */
@Repository
public interface FrameAuthorizationConsentRepository extends BaseRepository<FrameAuthorizationConsent, FrameAuthorizationConsentId> {

    /**
     * 根据 client id 和 principalName 查询 OAuth2 确认信息
     *
     * @param registeredClientId 注册OAuth2客户端ID
     * @param principalName      用户名
     * @return OAuth2 认证确认信息 {@link FrameAuthorizationConsent}
     */
    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    Optional<FrameAuthorizationConsent> findByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);

    /**
     * 根据 client id 和 principalName 删除 OAuth2 确认信息
     *
     * @param registeredClientId 注册OAuth2客户端ID
     * @param principalName      用户名
     */
    void deleteByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);
}
