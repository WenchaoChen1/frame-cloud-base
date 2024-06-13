package com.gstdev.cloud.service.identity.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.identity.entity.OAuth2Permission;
import com.gstdev.cloud.service.identity.repository.OAuth2PermissionRepository;
import org.springframework.stereotype.Service;

/**
 * <p>Description: OAuth2PermissionService </p>
 *
 * @author : cc
 * @date : 2022/4/1 13:53
 */
@Service
public class OAuth2PermissionService extends BaseServiceImpl<OAuth2Permission, String, OAuth2PermissionRepository>  implements BaseService<OAuth2Permission, String> {

    private OAuth2PermissionRepository authorityRepository;

    public OAuth2PermissionService(OAuth2PermissionRepository authorityRepository) {
        super(authorityRepository);
    }

}
