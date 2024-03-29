package com.gstdev.cloud.oauth2.management.processor;

import com.gstdev.cloud.oauth2.core.definition.domain.HerodotusGrantedAuthority;
import com.gstdev.cloud.oauth2.core.definition.service.EnhanceClientDetailsService;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Application;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Permission;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Scope;
import com.gstdev.cloud.oauth2.management.service.OAuth2ApplicationService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>Description: 客户端交互处理器 </p>
 *
 * @author : cc
 * @date : 2022/4/1 15:21
 */
public class HerodotusClientDetailsService implements EnhanceClientDetailsService {

    private final OAuth2ApplicationService applicationService;

    public HerodotusClientDetailsService(OAuth2ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Override
    public Set<HerodotusGrantedAuthority> findAuthoritiesById(String clientId) {

        OAuth2Application application = applicationService.findByClientId(clientId);
        if (ObjectUtils.isNotEmpty(application)) {
            Set<OAuth2Scope> scopes = application.getScopes();
            Set<HerodotusGrantedAuthority> result = new HashSet<>();
            if (CollectionUtils.isNotEmpty(scopes)) {
                for (OAuth2Scope scope : scopes) {
                    Set<OAuth2Permission> permissions = scope.getPermissions();
                    if (CollectionUtils.isNotEmpty(permissions)) {
                        Set<HerodotusGrantedAuthority> grantedAuthorities = permissions.stream().map(item -> new HerodotusGrantedAuthority(item.getPermissionCode())).collect(Collectors.toSet());
                        result.addAll(grantedAuthorities);
                    }
                }
            }
            return result;
        }

        return new HashSet<>();
    }
}
