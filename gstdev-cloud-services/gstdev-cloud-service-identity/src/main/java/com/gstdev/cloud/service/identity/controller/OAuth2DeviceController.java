package com.gstdev.cloud.service.identity.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.service.identity.domain.entity.OAuth2Device;
import com.gstdev.cloud.service.identity.service.OAuth2DeviceService;
import com.gstdev.cloud.rest.core.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: OAuth2DeviceController </p>
 *
 * @author : cc
 * @date : 2023/5/15 16:58
 */
@RestController
@RequestMapping("/v1/authorize/device")
//@Tags({
//    @Tag(name = "OAuth2 认证服务接口"),
//    @Tag(name = "物联网管理接口"),
//    @Tag(name = "物联网设备接口")
//})
public class OAuth2DeviceController extends BaseController<OAuth2Device, String, OAuth2DeviceService> {


    public OAuth2DeviceController(OAuth2DeviceService service) {
        super(service);
    }

    @Operation(summary = "给设备分配Scope", description = "给设备分配Scope")
    @Parameters({
        @Parameter(name = "deviceId", required = true, description = "设备ID"),
        @Parameter(name = "scopes[]", required = true, description = "Scope对象组成的数组")
    })
    @PutMapping
    public Result<OAuth2Device> authorize(@RequestParam(name = "deviceId") String deviceId, @RequestParam(name = "scopes[]") String[] scopes) {
        OAuth2Device device = getService().authorize(deviceId, scopes);
        return result(device);
    }
}
