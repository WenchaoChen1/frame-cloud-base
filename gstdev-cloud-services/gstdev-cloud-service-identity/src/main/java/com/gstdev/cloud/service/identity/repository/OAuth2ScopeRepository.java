package com.gstdev.cloud.service.identity.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.identity.domain.entity.OAuth2Scope;

import java.util.List;

/**
 * <p> Description : OauthScopeRepository </p>
 *
 * @author : cc
 * @date : 2020/3/19 16:57
 */
//@Repository
public interface OAuth2ScopeRepository extends BaseRepository<OAuth2Scope, String> {

    /**
     * 根据范围代码查询应用范围
     *
     * @param scopeCode 范围代码
     * @return 应用范围 {@link OAuth2Scope}
     */
    OAuth2Scope findByScopeCode(String scopeCode);

    /**
     * 根据 scope codes 查询对应的对象列表
     *
     * @param scopeCodes 范围代码
     * @return 对象列表
     */
    List<OAuth2Scope> findByScopeCodeIn(List<String> scopeCodes);
}
