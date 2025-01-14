

package com.gstdev.cloud.access.autoconfigure.customizer;


import com.gstdev.cloud.access.core.constants.AccessErrorCodes;
import com.gstdev.cloud.base.definition.constants.ErrorCodeMapperBuilderOrdered;
import com.gstdev.cloud.base.definition.function.ErrorCodeMapperBuilderCustomizer;
import com.gstdev.cloud.base.definition.support.ErrorCodeMapperBuilder;
import org.springframework.core.Ordered;

/**
 * <p>Description: Access 错误代码映射定义 </p>
 *
 * @author : cc
 * @date : 2023/9/26 23:35
 */
public class AccessErrorCodeMapperBuilderCustomizer implements ErrorCodeMapperBuilderCustomizer, Ordered {
    @Override
    public void customize(ErrorCodeMapperBuilder builder) {
        builder.preconditionFailed(
                AccessErrorCodes.ACCESS_CONFIG_ERROR,
                AccessErrorCodes.ACCESS_HANDLER_NOT_FOUND,
                AccessErrorCodes.ACCESS_IDENTITY_VERIFICATION_FAILED,
                AccessErrorCodes.ACCESS_PRE_PROCESS_FAILED_EXCEPTION,
                AccessErrorCodes.ILLEGAL_ACCESS_ARGUMENT,
                AccessErrorCodes.ILLEGAL_ACCESS_SOURCE
        );
    }

    @Override
    public int getOrder() {
        return ErrorCodeMapperBuilderOrdered.ACCESS;
    }
}
