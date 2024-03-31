package cn.herodotus.engine.rest.core.constants;

import com.gstdev.cloud.commons.ass.definition.feedback.NotAcceptableFeedback;

/**
 * <p>Description: Cache 相关错误代码 </p>
 *
 * @author : cc
 * @date : 2022/5/2 13:25
 */
public interface RestErrorCodes {

    NotAcceptableFeedback SESSION_INVALID = new NotAcceptableFeedback("Session已过期，请刷新再试");
    NotAcceptableFeedback REPEAT_SUBMISSION = new NotAcceptableFeedback("提交进行中，请不要重复提交");
    NotAcceptableFeedback FREQUENT_REQUESTS = new NotAcceptableFeedback("请求频繁，请稍后再试");
    NotAcceptableFeedback FEIGN_DECODER_IO_EXCEPTION = new NotAcceptableFeedback("Feign 解析 Fallback 错误信息出错");

}
