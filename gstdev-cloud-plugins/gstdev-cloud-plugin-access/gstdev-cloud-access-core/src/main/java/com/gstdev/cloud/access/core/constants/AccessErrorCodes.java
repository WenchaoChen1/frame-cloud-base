

package com.gstdev.cloud.access.core.constants;


import com.gstdev.cloud.base.definition.feedback.PreconditionFailedFeedback;

/**
 * <p>Description: Access 模块错误代码 </p>
 *
 * @author : cc
 * @date : 2022/9/2 17:50
 */
public interface AccessErrorCodes {

    PreconditionFailedFeedback ACCESS_CONFIG_ERROR = new PreconditionFailedFeedback("Access 模块配置错误");
    PreconditionFailedFeedback ACCESS_HANDLER_NOT_FOUND = new PreconditionFailedFeedback("Access 模块接入处理器未找到错误");
    PreconditionFailedFeedback ACCESS_IDENTITY_VERIFICATION_FAILED = new PreconditionFailedFeedback("接入身份认证错误");
    PreconditionFailedFeedback ACCESS_PRE_PROCESS_FAILED_EXCEPTION = new PreconditionFailedFeedback("接入预操作失败错误");

    PreconditionFailedFeedback ILLEGAL_ACCESS_ARGUMENT = new PreconditionFailedFeedback("社交登录参数错误");
    PreconditionFailedFeedback ILLEGAL_ACCESS_SOURCE = new PreconditionFailedFeedback("社交登录Source参数错误");
}
