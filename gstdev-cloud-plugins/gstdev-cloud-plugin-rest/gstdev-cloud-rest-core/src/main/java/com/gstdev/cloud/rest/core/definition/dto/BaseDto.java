package com.gstdev.cloud.rest.core.definition.dto;

import com.gstdev.cloud.commons.ass.definition.domain.base.AbstractDto;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: DTO基类定义 </p>
 *
 * @author : cc
 * @date : 2021/4/30 22:30
 */
public abstract class BaseDto extends AbstractDto {

  @Schema(title = "排序值")
  private Integer ranking = 0;

  public Integer getRanking() {
    return ranking;
  }

  public void setRanking(Integer ranking) {
    this.ranking = ranking;
  }
}
