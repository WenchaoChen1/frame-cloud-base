

package com.gstdev.cloud.access.all.event;

import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * <p>Description: 自动登录事件 </p>
 * <p>
 * JustAuth 接收到 Callback以后，统一走系统 /oauth/token 接口获取 Token
 *
 * @author : cc
 * @date : 2022/1/26 14:35
 */
public class AutomaticSignInEvent extends ApplicationEvent {

    private final Map<String, Object> callbackParams;

    public AutomaticSignInEvent(Map<String, Object> callbackParams) {
        super(callbackParams);
        this.callbackParams = callbackParams;
    }

    public Map<String, Object> getCallbackParams() {
        return callbackParams;
    }
}
