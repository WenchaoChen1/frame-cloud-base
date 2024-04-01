package com.gstdev.cloud.message.core.logic.event;

import com.gstdev.cloud.message.core.definition.event.AbstractApplicationEvent;

import java.time.Clock;

/**
 * <p>Description: 从账户状态缓存中释放账号事件 </p>
 *
 * @author : cc
 * @date : 2023/5/14 14:26
 */
public class AccountReleaseFromCacheEvent extends AbstractApplicationEvent<String> {

  public AccountReleaseFromCacheEvent(String data) {
    super(data);
  }

  public AccountReleaseFromCacheEvent(String data, Clock clock) {
    super(data, clock);
  }
}
