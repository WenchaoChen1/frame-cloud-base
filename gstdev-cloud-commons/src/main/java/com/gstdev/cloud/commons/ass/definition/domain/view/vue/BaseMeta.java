package com.gstdev.cloud.commons.ass.definition.domain.view.vue;

import cn.herodotus.engine.assistant.definition.domain.base.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>Description: Vue Router Meta 属性定义 </p>
 *
 * @author : cc
 * @date : 2022/7/14 16:51
 */
public class BaseMeta implements Entity {

    private String title;
    private String icon;
    @JsonProperty("isNotKeepAlive")
    private Boolean notKeepAlive = false;
    @JsonProperty("isIgnoreAuth")
    private Boolean ignoreAuth = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getNotKeepAlive() {
        return notKeepAlive;
    }

    public void setNotKeepAlive(Boolean notKeepAlive) {
        this.notKeepAlive = notKeepAlive;
    }

    public Boolean getIgnoreAuth() {
        return ignoreAuth;
    }

    public void setIgnoreAuth(Boolean ignoreAuth) {
        this.ignoreAuth = ignoreAuth;
    }
}
