// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.authorization.server.customizer;

import com.gstdev.cloud.base.definition.constants.ErrorCodeMapperBuilderOrdered;
import com.gstdev.cloud.base.definition.function.ErrorCodeMapperBuilderCustomizer;
import com.gstdev.cloud.base.definition.support.ErrorCodeMapperBuilder;
import com.gstdev.cloud.oauth2.core.constants.OAuth2ErrorCodes;
import org.springframework.core.Ordered;

/**
 * <p>Description: OAuth2 Authentication 模块内置错误代码生成器 </p>
 *
 * @author : cc
 * @date : 2023/9/26 22:11
 */
public class OAuth2ErrorCodeMapperBuilderCustomizer implements ErrorCodeMapperBuilderCustomizer, Ordered {
    @Override
    public void customize(ErrorCodeMapperBuilder builder) {
        builder.notAcceptable(
                OAuth2ErrorCodes.USERNAME_ALREADY_EXISTS
        );
    }

    @Override
    public int getOrder() {
        return ErrorCodeMapperBuilderOrdered.OAUTH2;
    }
}
