package com.gstdev.cloud.oauth2.management.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Permission;
import org.springframework.stereotype.Repository;

/**
 * <p>Description: OAuth2AuthorityRepository </p>
 *
 * @author : cc
 * @date : 2022/4/1 13:52
 */
@Repository
public interface OAuth2PermissionRepository extends BaseRepository<OAuth2Permission, String> {
}
