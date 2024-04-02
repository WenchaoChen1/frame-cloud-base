//package com.gstdev.cloud.rest.protect.secure.stamp;
//
//import com.gstdev.cloud.rest.condition.constants.RestConstants;
//import com.gstdev.cloud.rest.condition.properties.SecureProperties;
//import com.gstdev.cloud.cache.jetcache.stamp.AbstractStampManager;
//import org.dromara.hutool.core.data.id.IdUtil;
//
///**
// * <p>Description: 幂等Stamp管理 </p>
// *
// * @author : cc
// * @date : 2021/8/22 16:05
// */
//public class IdempotentStampManager extends AbstractStampManager<String, String> {
//
//  private final SecureProperties secureProperties;
//
//  public IdempotentStampManager(SecureProperties secureProperties) {
//    super(RestConstants.CACHE_NAME_TOKEN_IDEMPOTENT);
//    this.secureProperties = secureProperties;
//  }
//
//  public SecureProperties getSecureProperties() {
//    return secureProperties;
//  }
//
//  @Override
//  public String nextStamp(String key) {
//    return IdUtil.fastSimpleUUID();
//  }
//
//  @Override
//  public void afterPropertiesSet() throws Exception {
//    super.setExpire(secureProperties.getIdempotent().getExpire());
//  }
//}
