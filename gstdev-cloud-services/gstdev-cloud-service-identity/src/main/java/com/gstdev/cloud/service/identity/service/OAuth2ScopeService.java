package com.gstdev.cloud.service.identity.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.identity.domain.entity.OAuth2Permission;
import com.gstdev.cloud.service.identity.domain.entity.OAuth2Scope;
import com.gstdev.cloud.service.identity.repository.OAuth2ScopeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p> Description : OauthScopeService </p>
 *
 * @author : cc
 * @date : 2020/3/19 17:00
 */
@Service
public class OAuth2ScopeService extends BaseServiceImpl<OAuth2Scope, String, OAuth2ScopeRepository> implements BaseService<OAuth2Scope, String> {

    private OAuth2ScopeRepository oauthScopesRepository;

    public OAuth2ScopeService(OAuth2ScopeRepository oauthScopesRepository) {
        super(oauthScopesRepository);
    }


    public OAuth2Scope assigned(String scopeId, Set<OAuth2Permission> permissions) {

        OAuth2Scope oldScope = findById(scopeId);
        oldScope.setPermissions(permissions);

        return saveAndFlush(oldScope);
    }

    public OAuth2Scope findByScopeCode(String scopeCode) {
        return oauthScopesRepository.findByScopeCode(scopeCode);
    }

    public List<OAuth2Scope> findByScopeCodeIn(List<String> scopeCodes) {
        return oauthScopesRepository.findByScopeCodeIn(scopeCodes);
    }

    public Set<String> getScopePermissionIdByScopeId(String id) {
        return findById(id).getPermissions().stream().map(OAuth2Permission::getPermissionId).collect(java.util.stream.Collectors.toSet());
    }
}
