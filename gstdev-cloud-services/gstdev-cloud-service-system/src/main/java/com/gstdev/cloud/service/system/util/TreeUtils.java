package com.gstdev.cloud.service.system.util;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TreeUtils {
    // 构建树的方法
    public static <ID, T extends TreeNode<ID, T>> List<T> buildTree(
            List<T> items,
            Function<T, ID> idGetter,
            Function<T, ID> pidGetter) {
        return buildTree(items, idGetter, pidGetter, null);
    }  // 构建树的方法

    public static <ID, T extends TreeNode<ID, T>> List<T> buildTree(
            List<T> items,
            Function<T, ID> idGetter,
            Function<T, ID> pidGetter,
            Comparator<T> comparator) {

        // 用于存储所有节点的映射关系
        Map<ID, T> nodeMap = items.stream()
                .collect(Collectors.toConcurrentMap(idGetter, Function.identity()));

        // 用于存储根节点列表
        List<T> rootNodes = new CopyOnWriteArrayList<>();

        // 根据 pid 建立父子关系
        items.parallelStream().forEach(item -> {
            ID pid = pidGetter.apply(item);
            T node = nodeMap.get(idGetter.apply(item));
            if (pid == null || !nodeMap.containsKey(pid)) {
                rootNodes.add(node);
            } else {
                nodeMap.get(pid).addChild(node);
            }
        });
        // 如果 comparator 不为 null，则对所有节点的子节点进行排序
        if (comparator != null) {
            nodeMap.values().parallelStream().forEach(node -> {
                List<T> children = node.getChildren();
                if (children != null) {
                    node.setChildren(children.stream().sorted(comparator).collect(Collectors.toList()));
                }
            });

            // 对根节点列表进行排序
            rootNodes.sort(comparator);
        }

        // 对根节点列表返回
        return rootNodes;
    }

    // 查找指定节点
    public static <ID, T extends TreeNode<ID, T>> T findNodeById(T root, ID id) {
        if (root == null || id == null) {
            return null;
        }
        if (id.equals(root.getId())) {
            return root;
        }
        return findNodeById(root.getChildren(), id);
    }

    public static <ID, T extends TreeNode<ID, T>> T findNodeById(List<T> children, ID id) {
        if (children == null || id == null) {
            return null;
        }
        for (T child : children) {
            T result = findNodeById(child, id);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    // 查找多个节点
    public static <ID, T extends TreeNode<ID, T>> List<T> findNodesById(List<T> rootNodes, List<ID> ids) {
        Set<ID> idSet = new HashSet<>(ids); // 使用 Set 以提高查找速度
        List<T> resultNodes = new ArrayList<>();
        for (T root : rootNodes) {
            findNodesById(root, idSet, resultNodes);
        }
        return resultNodes;
    }

    private static <ID, T extends TreeNode<ID, T>> void findNodesById(T root, Set<ID> ids, List<T> resultNodes) {
        if (root == null || ids == null || resultNodes == null) {
            return;
        }
        if (ids.contains(root.getId())) {
            resultNodes.add(root);
        }
        for (T child : root.getChildren()) {
            findNodesById(child, ids, resultNodes);
        }
    }


}
