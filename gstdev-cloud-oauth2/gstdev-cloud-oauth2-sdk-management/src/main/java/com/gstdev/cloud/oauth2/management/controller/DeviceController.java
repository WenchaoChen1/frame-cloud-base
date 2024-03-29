package com.gstdev.cloud.oauth2.management.controller;

import com.gstdev.cloud.assistant.definition.constants.DefaultConstants;
import com.gstdev.cloud.assistant.definition.constants.SymbolConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>Description: 设备激活 </p>
 *
 * @author : cc
 * @date : 2023/3/24 17:09
 */
@Controller
public class DeviceController {

    @GetMapping(DefaultConstants.DEVICE_ACTIVATION_URI)
    public String activate(@RequestParam(value = OAuth2ParameterNames.USER_CODE, required = false) String userCode) {
        if (StringUtils.isNotBlank(userCode)) {
            return "redirect:" + DefaultConstants.DEVICE_VERIFICATION_ENDPOINT + SymbolConstants.QUESTION + OAuth2ParameterNames.USER_CODE + SymbolConstants.EQUAL + userCode;
        }
        return "activation";
    }

    @GetMapping(value = DefaultConstants.DEVICE_VERIFICATION_SUCCESS_URI)
    public String activated() {
        return "activation-allowed";
    }
}
