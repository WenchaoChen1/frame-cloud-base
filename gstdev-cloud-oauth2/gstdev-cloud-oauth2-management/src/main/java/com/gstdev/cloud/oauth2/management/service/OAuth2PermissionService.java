package com.gstdev.cloud.oauth2.management.service;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Permission;
import com.gstdev.cloud.oauth2.management.repository.OAuth2PermissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: OAuth2PermissionService </p>
 *
 * @author : cc
 * @date : 2022/4/1 13:53
 */
@Service
public class OAuth2PermissionService extends BaseServiceImpl<OAuth2Permission, String> {

    private OAuth2PermissionRepository authorityRepository;

    public OAuth2PermissionService(OAuth2PermissionRepository authorityRepository) {
        super(authorityRepository);
    }

}
