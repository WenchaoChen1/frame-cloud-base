package com.gstdev.cloud.oauth2.management.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.oauth2.management.service.OAuth2ConstantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>Description: OAuth2 常量 Controller </p>
 *
 * @author : cc
 * @date : 2022/3/17 15:00
 */
@RestController
@RequestMapping("/authorize/constant")
//@Tags({
//    @Tag(name = "OAuth2 认证服务接口"),
//    @Tag(name = "常量接口")
//})
public class OAuth2ConstantController {

    private final OAuth2ConstantService constantService;

    @Autowired
    public OAuth2ConstantController(OAuth2ConstantService constantService) {
        this.constantService = constantService;
    }

    @Operation(summary = "获取服务常量", description = "获取服务涉及的常量以及信息")
    @GetMapping(value = "/enums")
    public Result<Map<String, Object>> findAllEnums() {
        Map<String, Object> allEnums = constantService.getAllEnums();
        if (MapUtils.isNotEmpty(allEnums)) {
            return Result.success("获取服务常量成功", allEnums);
        } else {
            return Result.failure("获取服务常量失败");
        }
    }
}
