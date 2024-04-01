package com.gstdev.cloud.commons.ass.core.json.jackson2.modules;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * <p>Description: Java 封装类 Jackson 处理定义 Module </p>
 *
 * @author : cc
 * @date : 2023/4/29 16:35
 */
public class EncapsulationClassJackson2Module extends SimpleModule {

  public EncapsulationClassJackson2Module() {
    this.addSerializer(Long.class, ToStringSerializer.instance);
    this.addSerializer(Long.TYPE, ToStringSerializer.instance);
  }
}
