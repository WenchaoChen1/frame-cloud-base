package com.gstdev.cloud.oauth2.management.controller;

import com.gstdev.cloud.assistant.definition.domain.Result;
import com.gstdev.cloud.data.core.service.WriteableService;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Application;
import com.gstdev.cloud.oauth2.management.service.OAuth2ApplicationService;
import com.gstdev.cloud.rest.core.controller.BaseWriteableRestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: OAuth2应用管理接口 </p>
 *
 * @author : cc
 * @date : 2022/3/1 18:52
 */
@RestController
@RequestMapping("/authorize/application")
@Tags({
        @Tag(name = "OAuth2 认证服务接口"),
        @Tag(name = "OAuth2 应用管理接口")
})
public class OAuth2ApplicationController extends BaseWriteableRestController<OAuth2Application, String> {

    private final OAuth2ApplicationService applicationService;

    public OAuth2ApplicationController(OAuth2ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Override
    public WriteableService<OAuth2Application, String> getWriteableService() {
        return this.applicationService;
    }

    @Operation(summary = "给应用分配Scope", description = "给应用分配Scope")
    @Parameters({
            @Parameter(name = "appKey", required = true, description = "appKey"),
            @Parameter(name = "scopes[]", required = true, description = "Scope对象组成的数组")
    })
    @PutMapping
    public Result<OAuth2Application> authorize(@RequestParam(name = "applicationId") String scopeId, @RequestParam(name = "scopes[]") String[] scopes) {
        OAuth2Application application = applicationService.authorize(scopeId, scopes);
        return result(application);
    }
}
