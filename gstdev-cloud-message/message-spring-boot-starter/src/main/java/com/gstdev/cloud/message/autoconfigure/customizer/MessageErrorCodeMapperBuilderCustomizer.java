package com.gstdev.cloud.message.autoconfigure.customizer;

import com.gstdev.cloud.commons.ass.definition.constants.ErrorCodeMapperBuilderOrdered;
import com.gstdev.cloud.commons.ass.definition.function.ErrorCodeMapperBuilderCustomizer;
import com.gstdev.cloud.commons.ass.definition.support.ErrorCodeMapperBuilder;
import com.gstdev.cloud.message.core.constants.MessageErrorCodes;
import org.springframework.core.Ordered;

/**
 * <p>Description: Message 错误代码映射定义 </p>
 *
 * @author : cc
 * @date : 2023/9/26 23:27
 */
public class MessageErrorCodeMapperBuilderCustomizer implements ErrorCodeMapperBuilderCustomizer, Ordered {
  @Override
  public void customize(ErrorCodeMapperBuilder builder) {
    builder.notAcceptable(MessageErrorCodes.ILLEGAL_CHANNEL, MessageErrorCodes.PRINCIPAL_NOT_FOUND);
  }

  @Override
  public int getOrder() {
    return ErrorCodeMapperBuilderOrdered.MESSAGE;
  }
}
