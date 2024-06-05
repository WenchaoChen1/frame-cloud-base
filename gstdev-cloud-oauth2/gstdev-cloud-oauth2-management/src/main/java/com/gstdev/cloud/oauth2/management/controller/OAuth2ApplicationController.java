package com.gstdev.cloud.oauth2.management.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Application;
import com.gstdev.cloud.oauth2.management.service.OAuth2ApplicationService;
import com.gstdev.cloud.rest.core.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

/**
 * <p>Description: OAuth2应用管理接口 </p>
 *
 * @author : cc
 * @date : 2022/3/1 18:52
 */
@RestController
@RequestMapping("/authorize/application")
//@Tags({
//    @Tag(name = "OAuth2 认证服务接口"),
//    @Tag(name = "OAuth2 应用管理接口")
//})
public class OAuth2ApplicationController extends BaseController<OAuth2Application, String, OAuth2ApplicationService> {
    public OAuth2ApplicationController(OAuth2ApplicationService service) {
        super(service);
    }

    @Operation(summary = "给应用分配Scope", description = "给应用分配Scope")
    @Parameters({
        @Parameter(name = "appKey", required = true, description = "appKey"),
        @Parameter(name = "scopes[]", required = true, description = "Scope对象组成的数组")
    })
    @PutMapping
    public Result<OAuth2Application> authorize(@RequestParam(name = "applicationId") String scopeId, @RequestParam(name = "scopes[]") String[] scopes) {
        OAuth2Application application = getService().authorize(scopeId, scopes);
        return result(application);
    }

    @PostMapping("/aaa")
    public Result aaa(Duration refreshTokenValidity ) {
        return null;
    }
}
