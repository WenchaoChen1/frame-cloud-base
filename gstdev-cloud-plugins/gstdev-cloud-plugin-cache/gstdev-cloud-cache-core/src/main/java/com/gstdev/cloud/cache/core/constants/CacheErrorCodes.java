package com.gstdev.cloud.cache.core.constants;


import com.gstdev.cloud.base.definition.feedback.NotAcceptableFeedback;

/**
 * <p>Description: Cache 相关错误代码 </p>
 *
 * @author : cc
 * @date : 2022/5/2 13:25
 */
public interface CacheErrorCodes {

  NotAcceptableFeedback STAMP_DELETE_FAILED = new NotAcceptableFeedback("从缓存中删除签章失败");
  NotAcceptableFeedback STAMP_HAS_EXPIRED = new NotAcceptableFeedback("签章已过期");
  NotAcceptableFeedback STAMP_MISMATCH = new NotAcceptableFeedback("签章信息无法匹配");
  NotAcceptableFeedback STAMP_PARAMETER_ILLEGAL = new NotAcceptableFeedback("缺少签章身份标记参数");
}
