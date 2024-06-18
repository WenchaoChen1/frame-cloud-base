package com.gstdev.cloud.service.identity.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.identity.domain.entity.OAuth2Product;
import com.gstdev.cloud.service.identity.repository.OAuth2ProductRepository;
import org.springframework.stereotype.Service;

/**
 * <p>Description: OAuth2ProductService </p>
 *
 * @author : cc
 * @date : 2023/5/15 16:33
 */
@Service
public class OAuth2ProductService extends BaseServiceImpl<OAuth2Product, String, OAuth2ProductRepository> implements BaseService<OAuth2Product, String> {

    private OAuth2ProductRepository productRepository;

    public OAuth2ProductService(OAuth2ProductRepository productRepository) {
        super(productRepository);
    }

}
