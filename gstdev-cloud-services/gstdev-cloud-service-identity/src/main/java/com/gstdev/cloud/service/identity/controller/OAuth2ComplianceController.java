package com.gstdev.cloud.service.identity.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.BasePage;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.controller.Controller;
import com.gstdev.cloud.service.identity.domain.entity.OAuth2Compliance;
import com.gstdev.cloud.service.identity.domain.pojo.compliance.ComplianceManagePageQO;
import com.gstdev.cloud.service.identity.mapper.Oauth2ComplianceMapper;
import com.gstdev.cloud.service.identity.service.OAuth2ComplianceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>Description: OAuth2ComplianceController </p>
 *
 * @author : cc
 * @date : 2022/7/7 22:56
 */
@RestController
@RequestMapping("/v1/authorize/compliance")
//@Tags({
//    @Tag(name = "OAuth2 认证服务接口"),
//    @Tag(name = "OAuth2 应用安全合规接口"),
//    @Tag(name = "OAuth2 审计管理接口")
//})
public class OAuth2ComplianceController implements Controller<OAuth2Compliance, String> {

    @Resource
    private OAuth2ComplianceService service;
    @Resource
    private Oauth2ComplianceMapper mapper;

    @Override
    public OAuth2ComplianceService getService() {
        return this.service;
    }

    public Oauth2ComplianceMapper getMapper() {
        return this.mapper;
    }


    // ********************************* application Manage *****************************************


    @Tag(name = "Compliance Manage")
    @GetMapping("/get-compliance-manage-page")
    @Operation(summary = "get-compliance-manage-page")
    public Result<Map<String, Object>> getApplicationManagePage(ComplianceManagePageQO complianceManagePageQO, BasePage basePage) {
        return result(getMapper().toComplianceManagePageVO(getService().findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, complianceManagePageQO, criteriaBuilder), basePage)));
    }
}
