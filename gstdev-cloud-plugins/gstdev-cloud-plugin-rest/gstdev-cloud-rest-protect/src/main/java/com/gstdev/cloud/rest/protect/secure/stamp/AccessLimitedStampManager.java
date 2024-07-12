package com.gstdev.cloud.rest.protect.secure.stamp;

import com.gstdev.cloud.rest.condition.constants.RestConstants;
import com.gstdev.cloud.rest.condition.properties.SecureProperties;
import com.gstdev.cloud.cache.jetcache.stamp.AbstractStampManager;

/**
 * <p>Description: 防刷签章管理器 </p>
 * <p>
 * 这里使用Long类型作为值的存储类型，是为了解决该Cache 同时可以存储Duration相关的数据
 *
 * @author : cc
 * @date : 2021/8/25 21:43
 */
public class AccessLimitedStampManager extends AbstractStampManager<String, Long> {

  private final SecureProperties secureProperties;

  public AccessLimitedStampManager(SecureProperties secureProperties) {
    super(RestConstants.CACHE_NAME_TOKEN_ACCESS_LIMITED);
    this.secureProperties = secureProperties;
  }

  public SecureProperties getSecureProperties() {
    return secureProperties;
  }

  @Override
  public Long nextStamp(String key) {
    return 1L;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    super.setExpire(secureProperties.getAccessLimited().getExpire());
  }
}
