package com.gstdev.cloud.oauth2.resource.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @program: frame-cloud-base
 * @description:  URL 路径类别
 * @author: wenchao.chen
 * @create: 2024/03/25 15:10
 **/
public enum Category {
  /**
   * 含有通配符，含有 "*" 或 "?"
   */
  WILDCARD,
  /**
   * 含有占位符，含有 "{" 和 " } "
   */
  PLACEHOLDER,
  /**
   * 不含有任何特殊字符的完整路径
   */
  FULL_PATH;

  public static Category getCategory(String url) {

    if (StringUtils.containsAny(url, new String[]{"*", "?"})) {
      return Category.WILDCARD;
    }

    if (StringUtils.contains(url, "{")) {
      return Category.PLACEHOLDER;
    }

    return Category.FULL_PATH;
  }
}
