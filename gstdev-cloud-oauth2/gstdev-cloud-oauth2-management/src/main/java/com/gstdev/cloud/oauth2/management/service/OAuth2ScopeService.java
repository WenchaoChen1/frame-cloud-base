package com.gstdev.cloud.oauth2.management.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Permission;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Scope;
import com.gstdev.cloud.oauth2.management.repository.OAuth2ScopeRepository;
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
public class OAuth2ScopeService extends BaseServiceImpl<OAuth2Scope, String>   implements BaseService<OAuth2Scope, String> {

    private OAuth2ScopeRepository oauthScopesRepository;

    public OAuth2ScopeService(OAuth2ScopeRepository oauthScopesRepository) {
        //super(oauthScopesRepository);
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
}
