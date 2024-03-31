package cn.herodotus.engine.message.core.definition;

import cn.herodotus.engine.message.core.definition.domain.Message;
import cn.herodotus.engine.message.core.definition.event.AbstractApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * <p>Description: 消息发送适配器 </p>
 * <p>
 * 各种类型消息发送组件，基于该接口实现各自的消息发送。
 *
 * @author : cc
 * @date : 2023/10/26 16:46
 */
public interface MessageSendingAdapter<D extends Message, E extends AbstractApplicationEvent<D>> extends ApplicationListener<E> {

}
