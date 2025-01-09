

package com.gstdev.cloud.access.all.controller;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.gstdev.cloud.access.all.dto.WxappProfile;
import com.gstdev.cloud.access.all.processor.AccessHandlerStrategyFactory;
import com.gstdev.cloud.access.core.definition.AccessResponse;
import com.gstdev.cloud.base.core.enums.AccountType;
import com.gstdev.cloud.base.definition.domain.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: 微信小程序平台认证 </p>
 *
 * @author : cc
 * @date : 2021/5/28 11:40
 */
@RestController
@Tag(name = "微信小程序平台认证接口")
public class WxappAccessController {

    @Autowired
    private AccessHandlerStrategyFactory accessHandlerStrategyFactory;

    @Operation(summary = "微信小程序登录", description = "利用wx.login获取code，进行小程序登录")
    @Parameters({
            @Parameter(name = "socialDetails", required = true, description = "社交登录自定义参数实体"),
    })
    @PostMapping("/open/identity/wxapp")
    public Result<WxMaJscode2SessionResult> login(@Validated @RequestBody WxappProfile wxappProfile) {
        AccessResponse response = accessHandlerStrategyFactory.preProcess(AccountType.WXAPP, wxappProfile.getCode(), wxappProfile.getAppId());
        if (ObjectUtils.isNotEmpty(response)) {
            return Result.success("微信小程序登录成功", response.getSession());
        } else {
            return Result.failure("微信小程序登录失败");
        }
    }
}
