package com.gstdev.cloud.service.identity.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.BasePage;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.controller.Controller;
import com.gstdev.cloud.service.identity.domain.application.ApplicationManageQO;
import com.gstdev.cloud.service.identity.domain.application.InsertApplicationManageIO;
import com.gstdev.cloud.service.identity.domain.application.UpdateApplicationManageIO;
import com.gstdev.cloud.service.identity.domain.base.Oauth2ApplicationVo;
import com.gstdev.cloud.service.identity.entity.OAuth2Application;
import com.gstdev.cloud.service.identity.mapper.Oauth2ApplicationMapper;
import com.gstdev.cloud.service.identity.service.OAuth2ApplicationService;
import com.gstdev.cloud.rest.core.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: OAuth2应用管理接口 </p>
 *
 * @author : cc
 * @date : 2022/3/1 18:52
 */
@RestController
@RequestMapping("/v1/authorize/application")
//@Tags({
//    @Tag(name = "OAuth2 认证服务接口"),
//    @Tag(name = "OAuth2 应用管理接口")
//})
public class OAuth2ApplicationController extends BaseController<OAuth2Application, String, OAuth2ApplicationService> {
    public OAuth2ApplicationController(OAuth2ApplicationService service) {
        super(service);
    }

    @Resource
    private Oauth2ApplicationMapper applicationMapper;

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

    // ********************************* application Manage *****************************************

    @GetMapping("/get-application-manage-page")
    @Operation(summary = "get-application-manage-page")
    public Result<Map<String, Object>> getApplicationManagePage(ApplicationManageQO applicationManageQO, BasePage basePage) {
        return result(applicationMapper.toApplicationManagePageVO(getService().findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, applicationManageQO, criteriaBuilder), basePage)));
    }

    @GetMapping("/get-application-manage-detail/{id}")
    @Operation(summary = "get-application-manage-detail")
    public Result<Oauth2ApplicationVo> getApplicationManageDetail(@PathVariable String id) {
        return result(applicationMapper.toVo(getService().findById(id)));
    }

    @PostMapping("/insert-application-manage")
    @Operation(summary = "insert-application-manage")
    public Result insertApplicationManage(@RequestBody @Validated InsertApplicationManageIO insertApplicationManageIO) {
        this.getService().insertAndUpdate(applicationMapper.toEntity(insertApplicationManageIO));
        return result();
    }

    @PutMapping("/update-application-manage")
    @Operation(summary = "update-application-manage")
    public Result updateApplicationManage(@RequestBody @Validated UpdateApplicationManageIO updateApplicationManageIO) {
        OAuth2Application application = this.getService().findById(updateApplicationManageIO.getApplicationId());
        applicationMapper.copy(updateApplicationManageIO, application);
        this.getService().insertAndUpdate(application);
        return result();
    }


//    @Operation(summary = "删除一条数据")
    @DeleteMapping("delete-application-manage/{id}")
    public Result deleteApplicationManage(@PathVariable String id) {
        Result<String> result = result(String.valueOf(id));
        getService().deleteById(id);
        return result;
    }

//    @Operation(summary = "删除多条数据")
    @DeleteMapping("delete-all-application-manage")
    public Result deleteAllApplicationManage(List<String> id) {
        Result<String> result = result(String.valueOf(id));
        getService().deleteAllById(id);
        return result;
    }
}
