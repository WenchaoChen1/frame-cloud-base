package com.gstdev.cloud.service.system.bus.listener;

import com.gstdev.cloud.base.core.json.jackson2.utils.Jackson2Utils;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.message.core.logic.domain.UserStatus;
import com.gstdev.cloud.service.common.autoconfigure.bus.RemoteChangeUserStatusEvent;
import com.gstdev.cloud.service.system.service.SysUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <p>Description: 远程用户状态变更监听 </p>
 *
 */
@Component
public class RemoteChangeUserStatusListener implements ApplicationListener<RemoteChangeUserStatusEvent> {

    private static final Logger log = LoggerFactory.getLogger(RemoteChangeUserStatusListener.class);

    private final SysUserService sysUserService;

    @Autowired
    public RemoteChangeUserStatusListener(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Override
    public void onApplicationEvent(RemoteChangeUserStatusEvent event) {

        log.info("[Gstdev Cloud] |- Request mapping gather REMOTE listener, response event!");

        String data = event.getData();
        log.debug("[Gstdev Cloud] |- Fetch data [{}]", data);
        if (ObjectUtils.isNotEmpty(data)) {
            UserStatus userStatus = Jackson2Utils.toObject(data, UserStatus.class);
            if (ObjectUtils.isNotEmpty(userStatus)) {
                DataItemStatus dataItemStatus = DataItemStatus.valueOf(userStatus.getStatus());
                if (ObjectUtils.isNotEmpty(dataItemStatus)) {
                    sysUserService.changeStatus(userStatus.getUserId(), dataItemStatus);
                }
            }
        }
    }
}
