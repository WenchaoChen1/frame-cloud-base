package com.gstdev.cloud.oauth2.management.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Application;

/**
 * <p>Description: OAuth2ApplicationRepository </p>
 *
 * @author : cc
 * @date : 2022/3/1 18:05
 */
public interface OAuth2ApplicationRepository extends BaseRepository<OAuth2Application, String> {

    /**
     * 根据 Client ID 查询 OAuth2Application
     *
     * @param clientId OAuth2Application 中的 clientId
     * @return {@link OAuth2Application}
     */
    OAuth2Application findByClientId(String clientId);
}
