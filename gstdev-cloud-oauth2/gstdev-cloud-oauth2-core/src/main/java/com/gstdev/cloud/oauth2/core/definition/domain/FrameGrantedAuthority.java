package com.gstdev.cloud.oauth2.core.definition.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>Description: 自定义 GrantedAuthority </p>
 *
 * @author : cc
 * @date : 2022/3/5 0:12
 */
public class FrameGrantedAuthority implements GrantedAuthority {

    private String authority;

    public FrameGrantedAuthority() {
    }

    public FrameGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FrameGrantedAuthority that = (FrameGrantedAuthority) o;
        return Objects.equal(authority, that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(authority);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("authority", authority)
            .toString();
    }
}
