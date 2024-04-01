package com.gstdev.cloud.message.core.definition.strategy;

/**
 * <p>Description: 应用策略事件 </p>
 *
 * @author : cc
 * @date : 2022/3/29 7:26
 */
public interface ApplicationStrategyEventManager<T> extends StrategyEventManager<T> {

  /**
   * 目的服务名称
   *
   * @return 服务名称
   */
  String getDestinationServiceName();

  /**
   * 发送事件
   *
   * @param data 事件携带数据
   */
  default void postProcess(T data) {
    postProcess(getDestinationServiceName(), data);
  }
}
