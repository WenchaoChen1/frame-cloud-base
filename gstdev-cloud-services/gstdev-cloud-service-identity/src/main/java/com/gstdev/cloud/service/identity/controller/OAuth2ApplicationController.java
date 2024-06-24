package com.gstdev.cloud.service.identity.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.BasePage;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.controller.Controller;
import com.gstdev.cloud.service.identity.domain.entity.OAuth2Application;
import com.gstdev.cloud.service.identity.domain.pojo.application.*;
import com.gstdev.cloud.service.identity.mapper.Oauth2ApplicationMapper;
import com.gstdev.cloud.service.identity.service.OAuth2ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>Description: OAuth2应用管理接口 </p>
 *
 * @author : cc
 * @date : 2022/3/1 18:52
 */
@RestController
@RequestMapping("/v1/authorize/application")
public class OAuth2ApplicationController implements Controller<OAuth2Application, String> {

    @Resource
    private Oauth2ApplicationMapper applicationMapper;
    @Resource
    private OAuth2ApplicationService applicationService;

    @Override
    public OAuth2ApplicationService getService() {
        return applicationService;
    }

    // ********************************* application Manage *****************************************

    @Tag(name = "Application Manage")
    @GetMapping("/get-application-manage-page")
    @Operation(summary = "get-application-manage-page")
    public Result<Map<String, Object>> getApplicationManagePage(ApplicationManageQO applicationManageQO, BasePage basePage) {
        return result(applicationMapper.toApplicationManagePageVO(getService().findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, applicationManageQO, criteriaBuilder), basePage)));
    }

    @Tag(name = "Application Manage")
    @GetMapping("/get-application-manage-detail/{id}")
    @Operation(summary = "get-application-manage-detail")
    public Result<ApplicationManageDetailVO> getApplicationManageDetail(@PathVariable String id) {
        return result(applicationMapper.toApplicationManageDetailVO(getService().findById(id)));
    }

    @Tag(name = "Application Manage")
    @PostMapping("/insert-application-manage")
    @Operation(summary = "insert-application-manage")
    public Result insertApplicationManage(@RequestBody @Validated InsertApplicationManageIO insertApplicationManageIO) {
        this.getService().insertAndUpdate(applicationMapper.toEntity(insertApplicationManageIO));
        return result();
    }

    @Tag(name = "Application Manage")
    @PutMapping("/update-application-manage")
    @Operation(summary = "update-application-manage")
    public Result updateApplicationManage(@RequestBody @Validated UpdateApplicationManageIO updateApplicationManageIO) {
        OAuth2Application application = this.getService().findById(updateApplicationManageIO.getApplicationId());
        applicationMapper.copy(updateApplicationManageIO, application);
        this.getService().insertAndUpdate(application);
        return result();
    }

    @Tag(name = "Application Manage")
    @Operation(summary = "删除一条数据")
    @DeleteMapping("/delete-application-manage/{id}")
    public Result deleteApplicationManage(@PathVariable String id) {
        Result<String> result = result(String.valueOf(id));
        getService().deleteById(id);
        return result;
    }

    @Tag(name = "Application Manage")
    @Operation(summary = "删除多条数据")
    @DeleteMapping("/delete-all-application-manage")
    public Result deleteAllApplicationManage(List<String> id) {
        Result<String> result = result(String.valueOf(id));
        getService().deleteAllById(id);
        return result;
    }

    /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/



    @Tag(name = "Application Manage")
    @PostMapping("/update-application-manage-assigned-scope")
    @Operation(summary = "update-application-manage-assigned-scope")
    public Result updateApplicationManageAssignedScope(@RequestBody ApplicationManageAssignedScopeIO applicationManageAssignedScopeIO) {
        this.getService().updateApplicationManageAssignedScope(applicationManageAssignedScopeIO);
        return result();
    }

    @Tag(name = "Application Manage")
    @GetMapping("/get-application-scope-id-by-application-id/{id}")
    @Operation(summary = "get-application-scope-id-by-application-id")
    public Result<Set<String>> getApplicationScopeIdByApplicationId(@PathVariable String id) {
        return result(this.getService().getApplicationScopeIdByApplicationId(id));
    }
}
