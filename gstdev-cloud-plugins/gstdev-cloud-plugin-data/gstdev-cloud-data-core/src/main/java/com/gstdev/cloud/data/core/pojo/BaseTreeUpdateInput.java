package com.gstdev.cloud.data.core.pojo;

import com.gstdev.cloud.base.definition.domain.base.pojo.BaseTreeUpdateInputInterface;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BaseTreeUpdateInput extends BaseUpdateInput implements BaseTreeUpdateInputInterface {

    String id;

}
