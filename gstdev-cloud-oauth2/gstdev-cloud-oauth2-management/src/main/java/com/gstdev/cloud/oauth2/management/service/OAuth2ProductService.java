package com.gstdev.cloud.oauth2.management.service;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Product;
import com.gstdev.cloud.oauth2.management.repository.OAuth2ProductRepository;
import org.springframework.stereotype.Service;

/**
 * <p>Description: OAuth2ProductService </p>
 *
 * @author : cc
 * @date : 2023/5/15 16:33
 */
@Service
public class OAuth2ProductService extends BaseService<OAuth2Product, String> {

  private final OAuth2ProductRepository productRepository;

  public OAuth2ProductService(OAuth2ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public BaseRepository<OAuth2Product, String> getRepository() {
    return productRepository;
  }
}
