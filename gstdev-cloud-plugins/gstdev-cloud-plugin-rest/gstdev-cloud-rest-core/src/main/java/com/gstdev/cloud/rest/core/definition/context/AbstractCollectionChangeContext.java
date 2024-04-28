package com.gstdev.cloud.rest.core.definition.context;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 实体集合属性变更监听器 </p>
 *
 * @author : cc
 * @date : 2021/8/11 18:12
 */
public abstract class AbstractCollectionChangeContext extends AbstractApplicationContextAware {

    private List<String> before;
    private List<String> after;

    public void setBefore(List<String> before) {
        this.before = before;
    }

    public void setAfter(List<String> after) {
        this.after = after;
    }

    protected List<String> getChangedItems() {
        if (CollectionUtils.isNotEmpty(this.before) && CollectionUtils.isNotEmpty(this.after)) {
            return new ArrayList<>(CollectionUtils.disjunction(this.before, this.after));
        }

        if (CollectionUtils.isNotEmpty(this.before) && CollectionUtils.isEmpty(this.after)) {
            return this.before;
        }

        if (CollectionUtils.isEmpty(this.before) && CollectionUtils.isNotEmpty(this.after)) {
            return this.after;
        }

        return new ArrayList<>();
    }
}
