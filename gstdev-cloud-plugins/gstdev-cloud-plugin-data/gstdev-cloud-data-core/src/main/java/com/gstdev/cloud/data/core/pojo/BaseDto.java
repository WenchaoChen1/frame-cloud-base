package com.gstdev.cloud.data.core.pojo;

import com.gstdev.cloud.base.definition.domain.base.pojo.BaseDtoInterface;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BaseDto implements BaseDtoInterface<String> {
  String id;
}
