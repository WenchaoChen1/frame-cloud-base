package com.gstdev.cloud.oauth2.data.jpa.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.oauth2.data.jpa.entity.FrameRegisteredClient;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>Description: FrameRegisteredClientRepository </p>
 *
 * @author : cc
 * @date : 2022/2/25 21:05
 */
@Repository
public interface FrameRegisteredClientRepository extends BaseRepository<FrameRegisteredClient, String> {

    /**
     * 根据 ClientId 查询 RegisteredClient
     *
     * @param clientId OAuth2 客户端ID
     * @return OAuth2 客户端配置
     */
    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    Optional<FrameRegisteredClient> findByClientId(String clientId);
}
