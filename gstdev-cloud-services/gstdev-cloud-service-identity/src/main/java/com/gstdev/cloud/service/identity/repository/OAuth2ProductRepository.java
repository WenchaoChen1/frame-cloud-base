package com.gstdev.cloud.service.identity.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.identity.domain.entity.OAuth2Product;
import org.springframework.stereotype.Repository;

/**
 * <p>Description: OAuth2ProductRepository </p>
 *
 * @author : cc
 * @date : 2023/5/15 16:29
 */

//@Repository
public interface OAuth2ProductRepository extends BaseRepository<OAuth2Product, String> {
}
