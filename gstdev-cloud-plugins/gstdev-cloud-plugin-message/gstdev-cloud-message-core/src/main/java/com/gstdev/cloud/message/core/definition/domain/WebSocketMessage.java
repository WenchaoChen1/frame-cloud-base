package com.gstdev.cloud.message.core.definition.domain;

import com.google.common.base.MoreObjects;

/**
 * <p>Description: WebSocket 模版消息 </p>
 *
 * @author : cc
 * @date : 2023/10/26 20:18
 */
public class WebSocketMessage extends TemplateMessage {

    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("user", user)
                .toString();
    }
}
