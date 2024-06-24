package com.gstdev.cloud.springframework.openfeign.constants;

import com.gstdev.cloud.base.definition.feedback.NotAcceptableFeedback;

/**
 * <p>Description: Cache 相关错误代码 </p>
 */
public interface OpenFeignErrorCodes {
    NotAcceptableFeedback FEIGN_DECODER_IO_EXCEPTION = new NotAcceptableFeedback("Feign 解析 Fallback 错误信息出错");

}
