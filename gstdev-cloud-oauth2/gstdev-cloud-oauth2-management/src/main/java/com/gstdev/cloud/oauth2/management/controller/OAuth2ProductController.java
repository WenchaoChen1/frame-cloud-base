package com.gstdev.cloud.oauth2.management.controller;

import com.gstdev.cloud.data.core.service.WriteableService;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Product;
import com.gstdev.cloud.oauth2.management.service.OAuth2ProductService;
import com.gstdev.cloud.rest.core.controller.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
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
@Tags({
    @Tag(name = "OAuth2 认证服务接口"),
    @Tag(name = "物联网管理接口"),
    @Tag(name = "物联网产品接口")
})
public class OAuth2ProductController extends BaseController<OAuth2Product, String, OAuth2ProductService> {


    public OAuth2ProductController(OAuth2ProductService service) {
        super(service);
    }
}
