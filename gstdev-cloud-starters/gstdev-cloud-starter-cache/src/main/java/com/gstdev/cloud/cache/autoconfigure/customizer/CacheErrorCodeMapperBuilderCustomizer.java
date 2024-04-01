package com.gstdev.cloud.cache.autoconfigure.customizer;


import com.gstdev.cloud.cache.core.constants.CacheErrorCodes;
import com.gstdev.cloud.commons.ass.definition.constants.ErrorCodeMapperBuilderOrdered;
import com.gstdev.cloud.commons.ass.definition.function.ErrorCodeMapperBuilderCustomizer;
import com.gstdev.cloud.commons.ass.definition.support.ErrorCodeMapperBuilder;
import org.springframework.core.Ordered;

/**
 * <p>Description: Cache 模块内置错误代码生成器 </p>
 *
 * @author : gengwei.zheng
 * @date : 2023/9/26 22:11
 */
public class CacheErrorCodeMapperBuilderCustomizer implements ErrorCodeMapperBuilderCustomizer, Ordered {
  @Override
  public void customize(ErrorCodeMapperBuilder builder) {
    builder.notAcceptable(
      CacheErrorCodes.STAMP_DELETE_FAILED,
      CacheErrorCodes.STAMP_HAS_EXPIRED,
      CacheErrorCodes.STAMP_MISMATCH,
      CacheErrorCodes.STAMP_PARAMETER_ILLEGAL
    );
  }

  @Override
  public int getOrder() {
    return ErrorCodeMapperBuilderOrdered.CACHE;
  }
}
