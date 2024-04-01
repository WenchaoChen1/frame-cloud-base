package com.gstdev.cloud.commons.ass.definition.domain.view.vue;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * <p>Description: 前端选项基础格式 </p>
 *
 * @author : cc
 * @date : 2023/4/24 10:00
 */
public class Option implements Serializable {

  private String label;

  private String value;

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("label", label)
      .add("value", value)
      .toString();
  }
}
