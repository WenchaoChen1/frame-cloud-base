package com.gstdev.cloud.service.identity.dto;

import com.google.common.base.MoreObjects;

/**
 * <p>Description: Session响应实体 </p>
 *
 * @author : cc
 * @date : 2021/10/2 11:42
 */
public class Session extends SessionExchange {

    /**
     * 本系统授权码模式校验参数
     */
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("state", state)
            .toString();
    }
}
