package com.gstdev.cloud.service.system.util;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TreeNode<ID, T extends com.gstdev.cloud.base.core.utils.treeUtils.TreeNode<ID, T>> implements com.gstdev.cloud.base.core.utils.treeUtils.TreeNode<ID, T> {

    private ID id;
    private ID parentId;
    // 子节点列表
    private List<T> children = null;

    @Override
    // 获取子节点列表
    public List<T> getChildren() {
        return children;
    }

    @Override
    // 设置子节点列表
    public void setChildren(List<T> children) {
        if (children == null) {
            children = new CopyOnWriteArrayList<>();
        }
        this.children = new CopyOnWriteArrayList<>(children);
    }

    @Override
    // 添加子节点
    public void addChild(T child) {
        if (children == null) {
            children = new CopyOnWriteArrayList<>();
        }
        children.add(child);
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public ID getParentId() {
        return parentId;
    }

    public void setParentId(ID parentId) {
        this.parentId = parentId;
    }
}
