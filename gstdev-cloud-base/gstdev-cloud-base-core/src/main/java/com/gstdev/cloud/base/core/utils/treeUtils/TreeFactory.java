package com.gstdev.cloud.base.core.utils.treeUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 树构造工厂
 * @author: YinHeng
 * @date: 2020/11/6 16:27
 **/
public class TreeFactory<K, T extends TreeNode<K, T>> implements Serializable {


  /**
   * 递归向上查找法
   *
   * @param nodeList
   * @return
   */
  public List<T> buildTree(List<T> nodeList) {
    Map<K, T> nodeMap = new LinkedHashMap<>(16);
    for (T node : nodeList) {
      nodeMap.put(node.getId(), node);
    }
    return buildTree(nodeMap);
  }

  /**
   * 递归向上查找父节点
   *
   * @param nodeMap
   * @return
   */
  public List<T> buildTree(Map<K, T> nodeMap) {
    return findParent(nodeMap);
  }

  /**
   * 利用HashMap高效率递归向上查找，并每次减少遍历节点数
   *
   * @param nodeMap
   * @return
   */
  public List<T> findParent(Map<K, T> nodeMap) {

    //该循环结束后可以移除掉的节点
    List<K> removeNodes = new ArrayList<>();

    for (T node : nodeMap.values()) {
      T pNode = nodeMap.get(node.getParentId());
      //如果当前节点的父节点存在，则将当前节点添加到父节点的子节点，并在循环结束后从Map中删除当前节点
      if (pNode != null && !pNode.getId().equals(node.getId())) {
        pNode.addChild(node);
        removeNodes.add(node.getId());
      }
    }
    //删除已经找到父节点的节点
    removeNodes.forEach(nodeMap::remove);

    //再次遍历map，直到删除全部非根节点
    if (removeNodes.size() > 0) {
      findParent(nodeMap);
    }
    return new ArrayList<>(nodeMap.values());
  }


}
