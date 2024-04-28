package com.gstdev.cloud.data.core.pojo;

import com.gstdev.cloud.base.definition.domain.base.pojo.BaseTreeVoInterface;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BaseTreeVo<V> extends BaseVo implements BaseTreeVoInterface {


    List<V> children;

}
