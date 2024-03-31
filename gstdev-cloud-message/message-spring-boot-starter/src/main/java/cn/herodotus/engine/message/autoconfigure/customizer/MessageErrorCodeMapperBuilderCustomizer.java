package cn.herodotus.engine.message.autoconfigure.customizer;

import cn.herodotus.engine.assistant.definition.constants.ErrorCodeMapperBuilderOrdered;
import cn.herodotus.engine.assistant.definition.function.ErrorCodeMapperBuilderCustomizer;
import cn.herodotus.engine.assistant.definition.support.ErrorCodeMapperBuilder;
import cn.herodotus.engine.message.core.constants.MessageErrorCodes;
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
