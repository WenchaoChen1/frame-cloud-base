package com.gstdev.cloud.message.core.logic.event;

import com.gstdev.cloud.message.core.definition.event.AbstractApplicationEvent;
import com.gstdev.cloud.message.core.logic.domain.UserStatus;

import java.time.Clock;

/**
 * <p>Description: 本地用户状态变更事件 </p>
 *
 * @author : cc
 * @date : 2022/7/10 16:15
 */
public class ChangeUserStatusEvent extends AbstractApplicationEvent<UserStatus> {

    public ChangeUserStatusEvent(UserStatus data) {
        super(data);
    }

    public ChangeUserStatusEvent(UserStatus data, Clock clock) {
        super(data, clock);
    }
}
