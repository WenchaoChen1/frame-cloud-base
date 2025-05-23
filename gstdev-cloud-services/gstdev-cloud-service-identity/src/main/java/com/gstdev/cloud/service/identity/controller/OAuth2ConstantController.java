package com.gstdev.cloud.service.identity.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.service.identity.service.OAuth2ConstantService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.MapUtils;
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
@RequestMapping("/v1/authorize/constant")
//@Tags({
//    @Tag(name = "OAuth2 认证服务接口"),
//    @Tag(name = "常量接口")
//})
public class OAuth2ConstantController {

    @Resource
    private OAuth2ConstantService constantService;

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
