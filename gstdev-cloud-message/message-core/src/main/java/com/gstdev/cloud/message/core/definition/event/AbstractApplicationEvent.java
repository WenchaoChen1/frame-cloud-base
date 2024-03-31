package com.gstdev.cloud.message.core.definition.event;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * <p>Description: 自定义 Application Event 基础类 </p>
 *
 * @author : cc
 * @date : 2022/2/4 15:14
 */
public abstract class AbstractApplicationEvent<T> extends ApplicationEvent {

    private final T data;

    public AbstractApplicationEvent(T data) {
        super(data);
        this.data = data;
    }

    public AbstractApplicationEvent(T data, Clock clock) {
        super(data, clock);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
