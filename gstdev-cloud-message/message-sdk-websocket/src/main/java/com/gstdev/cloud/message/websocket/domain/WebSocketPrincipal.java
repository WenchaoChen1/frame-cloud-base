package com.gstdev.cloud.message.websocket.domain;

import com.gstdev.cloud.base.definition.domain.oauth2.PrincipalDetails;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.security.Principal;
import java.util.Set;

/**
 * <p>Description: Websocket登录连接对象 </p>
 * <p>
 * 用于保存websocket连接过程中需要存储的业务参数
 *
 * @author : cc
 * @date : 2021/10/24 18:43
 */
public class WebSocketPrincipal implements Principal {

    private String userId;
    private String username;

    private String employeeId;

    private String avatar;

    private Set<String> roles;

    public WebSocketPrincipal(PrincipalDetails details) {
        this.userId = details.getOpenId();
        this.username = details.getUsername();
        this.employeeId = details.getEmployeeId();
        this.avatar = details.getAvatar();
        this.roles = details.getRoles();
    }

    public WebSocketPrincipal(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 这里的 Name 是发送信息目标的标识。
     * <p>
     * 使用 Username 可控度不高，使用也不方便。直接用ID
     *
     * @return WebSocket 用户的唯一标识
     */
    @Override
    public String getName() {
        return this.userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WebSocketPrincipal that = (WebSocketPrincipal) o;
        return Objects.equal(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("userId", userId)
            .add("username", username)
            .add("employeeId", employeeId)
            .add("avatar", avatar)
            .toString();
    }
}
