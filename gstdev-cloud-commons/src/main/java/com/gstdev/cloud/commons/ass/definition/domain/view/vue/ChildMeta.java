package com.gstdev.cloud.commons.ass.definition.domain.view.vue;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>Description: 子节点 Meta </p>
 *
 * @author : cc
 * @date : 2022/7/14 16:54
 */
public class ChildMeta extends BaseMeta {

    @JsonProperty("isDetailContent")
    private Boolean detailContent;

    public Boolean getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(Boolean detailContent) {
        this.detailContent = detailContent;
    }
}
