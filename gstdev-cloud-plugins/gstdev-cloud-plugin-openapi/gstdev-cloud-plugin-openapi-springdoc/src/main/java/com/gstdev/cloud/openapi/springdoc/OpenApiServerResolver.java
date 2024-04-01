package com.gstdev.cloud.openapi.springdoc;

import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

/**
 * <p>Description: OpenApi Server 处理器 </p>
 * <p>
 * 单体和分布式架构，提供给OpenAPI展示的地址不同。
 *
 * @author : cc
 * @date : 2022/1/16 18:56
 */
public interface OpenApiServerResolver {

  /**
   * 获取 Open Api 所需的 Server 地址。
   *
   * @return Open Api Servers 值
   */
  List<Server> getServers();
}
