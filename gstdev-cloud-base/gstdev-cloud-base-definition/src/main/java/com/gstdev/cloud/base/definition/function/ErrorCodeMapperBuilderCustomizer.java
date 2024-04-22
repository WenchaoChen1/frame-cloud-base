package com.gstdev.cloud.base.definition.function;


import com.gstdev.cloud.base.definition.support.ErrorCodeMapperBuilder;

/**
 * <p>Description: ErrorCodeMapperBuilder 回调接口</p>
 * <p>
 * 实现了该接口的Bean，可以在自动配置阶段，通过ErrorCodeMapperBuilder进一步扩展错误码
 *
 * @author : cc
 * @date : 2023/9/24 23:06
 */
@FunctionalInterface
public interface ErrorCodeMapperBuilderCustomizer {

  /**
   * 自定义 ErrorCodeMapperBuilder
   *
   * @param builder 被扩展的 {@link ErrorCodeMapperBuilder}
   */
  void customize(ErrorCodeMapperBuilder builder);
}
