package com.gstdev.cloud.service.identity.controller;

import com.gstdev.cloud.service.identity.entity.OAuth2Product;
import com.gstdev.cloud.service.identity.service.OAuth2ProductService;
import com.gstdev.cloud.rest.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: OAuth2ProductController </p>
 *
 * @author : cc
 * @date : 2023/5/15 16:37
 */
@RestController
@RequestMapping("/authorize/product")
//@Tags({
//    @Tag(name = "OAuth2 认证服务接口"),
//    @Tag(name = "物联网管理接口"),
//    @Tag(name = "物联网产品接口")
//})
public class OAuth2ProductController extends BaseController<OAuth2Product, String, OAuth2ProductService> {


    public OAuth2ProductController(OAuth2ProductService service) {
        super(service);
    }
}