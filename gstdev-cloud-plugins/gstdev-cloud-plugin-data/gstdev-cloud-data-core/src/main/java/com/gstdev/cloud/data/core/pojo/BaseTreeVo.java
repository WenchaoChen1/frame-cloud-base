package com.gstdev.cloud.data.core.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BaseTreeVo<V> extends BaseVo {


  List<V> children;

}
