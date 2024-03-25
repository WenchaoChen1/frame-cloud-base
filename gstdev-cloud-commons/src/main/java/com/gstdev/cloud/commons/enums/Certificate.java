package com.gstdev.cloud.commons.enums;

/**
 * @program: frame-cloud-base
 * @description: 证书使用策略
 * @author: wenchao.chen
 * @create: 2024/03/25 15:16
 **/
public enum Certificate {

  /**
   * Spring Authorization Server 默认的 JWK 生成方式
   */
  STANDARD,
  /**
   * 自定义证书 JWK 生成方式
   */
  CUSTOM;
}
