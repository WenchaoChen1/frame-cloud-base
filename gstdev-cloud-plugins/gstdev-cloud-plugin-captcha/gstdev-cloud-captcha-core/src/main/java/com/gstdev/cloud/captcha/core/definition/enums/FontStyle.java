package com.gstdev.cloud.captcha.core.definition.enums;

import java.awt.*;

/**
 * <p>Description: 字体风格 </p>
 * <p>
 * 定义此类的目的：
 * 1. 设置字体风格和字体大小，最初都是使用int类型参数，很容混淆出错，增加个枚举类型以示区别
 * 2. 枚举类型让配置参数配置更便捷。
 *
 * @author : cc
 * @date : 2024/12/23 10:33
 */
public enum FontStyle {

  /**
   * 字体风格
   */
  PLAIN(Font.PLAIN),
  BOLD(Font.BOLD),
  ITALIC(Font.ITALIC);

  private final int mapping;

  FontStyle(int mapping) {
    this.mapping = mapping;
  }

  public int getMapping() {
    return mapping;
  }
}
