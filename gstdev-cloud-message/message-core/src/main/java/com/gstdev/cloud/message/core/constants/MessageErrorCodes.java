package com.gstdev.cloud.message.core.constants;

import com.gstdev.cloud.commons.ass.definition.feedback.NotAcceptableFeedback;

/**
 * <p>Description: WebSocket 统一错误代码定义 </p>
 *
 * @author : cc
 * @date : 2022/12/29 15:57
 */
public interface MessageErrorCodes {

    NotAcceptableFeedback ILLEGAL_CHANNEL = new NotAcceptableFeedback("WebSocket Channel 设置错误");
    NotAcceptableFeedback PRINCIPAL_NOT_FOUND = new NotAcceptableFeedback("WebSocket 无法获取用户身份信息");
}
