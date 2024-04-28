package com.gstdev.cloud.message.core.definition.event;

import com.gstdev.cloud.message.core.definition.domain.StreamMessage;

import java.time.Clock;

/**
 * <p>Description: Spring Cloud Stream 类型消息 </p>
 *
 * @author : cc
 * @date : 2023/10/26 15:17
 */
public class StreamMessageSendingEvent<T extends StreamMessage> extends AbstractApplicationEvent<T> {
    public StreamMessageSendingEvent(T data) {
        super(data);
    }

    public StreamMessageSendingEvent(T data, Clock clock) {
        super(data, clock);
    }
}
