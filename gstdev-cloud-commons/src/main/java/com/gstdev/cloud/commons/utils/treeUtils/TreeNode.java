package com.gstdev.cloud.commons.utils.treeUtils;


import java.util.List;

/**
 * 树节点接口
 */
public interface TreeNode<K, T extends TreeNode<K, T>> {
  /**
   * 获取节点id
   *
   * @return 节点id
   * @author xuyuxiang
   * @date 2020/7/9 18:36
   */
  K getId();

  /**
   * 设置节点id
   *
   * @param id
   */
  void setId(K id);

  /**
   * 获取节点父id
   *
   * @return 节点父id
   * @author xuyuxiang
   * @date 2020/7/9 18:36
   */
  K getParentId();

  /**
   * 设置父节点
   *
   * @param pid
   */
  void setParentId(K pid);

  /**
   * 获取孩子
   *
   * @return
   */
  List<T> getChildren();

  /**
   * 设置children
   *
   * @param children 子节点集合
   * @author xuyuxiang
   * @date 2020/7/9 18:36
   */
  void setChildren(List<T> children);

  /**
   * 添加子节点
   *
   * @param node
   */
  void addChild(T node);
}
