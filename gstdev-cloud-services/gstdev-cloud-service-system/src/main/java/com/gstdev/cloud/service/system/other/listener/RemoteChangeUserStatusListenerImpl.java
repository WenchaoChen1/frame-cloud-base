package com.gstdev.cloud.service.system.other.listener;

import com.gstdev.cloud.base.core.json.jackson2.utils.Jackson2Utils;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.message.core.logic.domain.UserStatus;
import com.gstdev.cloud.service.common.autoconfigure.bus.RemoteChangeUserStatusEvent;
import com.gstdev.cloud.service.system.service.SysUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  <p>Description: 远程用户状态变更监听 </p>
 *
 * @author WenchaoChen
 * @data 2025/1/15 10:00
 */
public class RemoteChangeUserStatusListenerImpl implements RemoteChangeUserStatusListener {

    private static final Logger log = LoggerFactory.getLogger(RemoteChangeUserStatusListenerImpl.class);

    private final SysUserService sysUserService;

    @Autowired
    public RemoteChangeUserStatusListenerImpl(SysUserService sysUserService) {
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
