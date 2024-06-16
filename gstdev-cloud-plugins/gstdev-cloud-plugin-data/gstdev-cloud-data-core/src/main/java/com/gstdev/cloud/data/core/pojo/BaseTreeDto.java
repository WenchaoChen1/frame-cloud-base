package com.gstdev.cloud.data.core.pojo;

import com.gstdev.cloud.base.core.utils.treeUtils.TreeNode;
import com.gstdev.cloud.base.definition.domain.base.pojo.BaseTreeDtoInterface;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
//public class BaseTreeDto<D extends BaseDto> extends BaseDto  {
public class BaseTreeDto<D extends BaseDto & TreeNode<String, D>> extends BaseDto implements TreeNode<String, D>, BaseTreeDtoInterface<D, String> {
    public List<D> children;
    String parentId;

    @Override
    public void addChild(D node) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(node);
    }
    @Override
    public void setChildren(List<D> children) {
        if (children == null) {
            children = new ArrayList<>();
        }
        this.children = children;
    }


}
