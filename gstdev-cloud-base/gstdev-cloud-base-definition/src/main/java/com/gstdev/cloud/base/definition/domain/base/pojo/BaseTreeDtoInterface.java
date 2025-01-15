package com.gstdev.cloud.base.definition.domain.base.pojo;

import com.gstdev.cloud.base.definition.domain.base.Entity;

import java.util.List;

/**
 * @author WenchaoChen
 */
public interface BaseTreeDtoInterface<D, ID> extends Entity {


    /**
     * 获取节点id
     *
     * @return 节点id
     * @author xuyuxiang
     * @date 2020/7/9 18:36
     */
    ID getId();

    /**
     * 设置节点id
     *
     * @param id
     */
    void setId(ID id);

    /**
     * 获取节点父id
     *
     * @return 节点父id
     * @author xuyuxiang
     * @date 2020/7/9 18:36
     */
    ID getParentId();

    /**
     * 设置父节点
     *
     * @param pid
     */
    void setParentId(ID pid);

    /**
     * 获取孩子
     *
     * @return
     */
    List<D> getChildren();

    /**
     * 设置children
     *
     * @param children 子节点集合
     * @author xuyuxiang
     * @date 2020/7/9 18:36
     */
    void setChildren(List<D> children);

    /**
     * 添加子节点
     *
     * @param node
     */
    void addChild(D node);
}
