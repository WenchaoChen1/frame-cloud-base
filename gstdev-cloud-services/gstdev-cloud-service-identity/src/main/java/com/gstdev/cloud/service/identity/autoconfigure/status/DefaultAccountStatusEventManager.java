package com.gstdev.cloud.service.identity.autoconfigure.status;


import com.gstdev.cloud.base.core.context.ServiceContextHolder;
import com.gstdev.cloud.message.core.logic.domain.UserStatus;
import com.gstdev.cloud.message.core.logic.event.ChangeUserStatusEvent;
import com.gstdev.cloud.message.core.logic.strategy.AccountStatusEventManager;

/**
 * <p>Description: 用户状态变更处理器 </p>
 *
 * @author : cc
 * @date : 2022/7/10 17:25
 */
public class DefaultAccountStatusEventManager implements AccountStatusEventManager {
    @Override
    public String getDestinationServiceName() {
        return ServiceContextHolder.getInstance().getSystemServiceName();
    }

    @Override
    public void postLocalProcess(UserStatus data) {
        publishEvent(new ChangeUserStatusEvent(data));
    }

    @Override
    public void postRemoteProcess(String data, String originService, String destinationService) {
//        publishEvent(new RemoteChangeUserStatusEvent(data, originService, destinationService));
    }
}
