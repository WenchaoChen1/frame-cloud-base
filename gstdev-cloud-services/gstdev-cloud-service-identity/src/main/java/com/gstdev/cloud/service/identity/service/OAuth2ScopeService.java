package com.gstdev.cloud.service.identity.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.identity.domain.entity.OAuth2Permission;
import com.gstdev.cloud.service.identity.domain.entity.OAuth2Scope;
import com.gstdev.cloud.service.identity.domain.pojo.scope.ScopeManageAssignedPermissionIO;
import com.gstdev.cloud.service.identity.repository.OAuth2PermissionRepository;
import com.gstdev.cloud.service.identity.repository.OAuth2ScopeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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
    private OAuth2PermissionRepository oAuth2PermissionRepository;

    public OAuth2ScopeService(OAuth2ScopeRepository oauthScopesRepository, OAuth2PermissionRepository oAuth2PermissionRepository) {
        super(oauthScopesRepository);
        this.oAuth2PermissionRepository = oAuth2PermissionRepository;
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

    @Transactional
    public void scopeManageAssignedPermission(ScopeManageAssignedPermissionIO scopeManageAssignedPermissionIO) {
        OAuth2Scope scope = findById(scopeManageAssignedPermissionIO.getScopeId());
        Set<OAuth2Permission> permissions = new HashSet<>();
        scopeManageAssignedPermissionIO.getPermissions().forEach(permissionDto -> {
            OAuth2Permission permission = oAuth2PermissionRepository.findById(permissionDto.getPermissionId()).orElse(null);
            if (permission != null) {
                permissions.add(permission);
            }
        });
        scope.setPermissions(permissions);
        saveAndFlush(scope);
    }
}
