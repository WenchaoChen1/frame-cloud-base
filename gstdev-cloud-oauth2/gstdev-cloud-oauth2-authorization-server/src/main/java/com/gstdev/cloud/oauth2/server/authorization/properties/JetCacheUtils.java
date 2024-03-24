//package com.gstdev.cloud.oauth2.server.authorization.properties;
//
//
//import org.apache.commons.lang3.ObjectUtils;
//import org.springframework.boot.autoconfigure.cache.CacheType;
//
//import java.time.Duration;
//
//public class JetCacheUtils {
//  private static volatile JetCacheUtils instance;
//  private JetCacheCreateCacheFactory jetCacheCreateCacheFactory;
//
//  private JetCacheUtils() {
//  }
//
//  public static JetCacheUtils getInstance() {
//    if (ObjectUtils.isEmpty(instance)) {
//      Class var0 = JetCacheUtils.class;
//      synchronized(JetCacheUtils.class) {
//        if (ObjectUtils.isEmpty(instance)) {
//          instance = new JetCacheUtils();
//        }
//      }
//    }
//
//    return instance;
//  }
//
//  public static <K, V> Cache<K, V> create(String name, Duration expire) {
//    return create(name, expire, true);
//  }
//
//  public static <K, V> Cache<K, V> create(String name, Duration expire, Boolean cacheNullValue) {
//    return create(name, (Duration)expire, (Boolean)cacheNullValue, (Boolean)null);
//  }
//
//  public static <K, V> Cache<K, V> create(String name, Duration expire, Boolean cacheNullValue, Boolean syncLocal) {
//    return create(name, CacheType.BOTH, expire, cacheNullValue, syncLocal);
//  }
//
//  public static <K, V> Cache<K, V> create(String name, CacheType cacheType) {
//    return create(name, (CacheType)cacheType, (Duration)null);
//  }
//
//  public static <K, V> Cache<K, V> create(String name, CacheType cacheType, Duration expire) {
//    return create(name, cacheType, expire, true);
//  }
//
//  public static <K, V> Cache<K, V> create(String name, CacheType cacheType, Duration expire, Boolean cacheNullValue) {
//    return create(name, cacheType, expire, cacheNullValue, (Boolean)null);
//  }
//
//  public static <K, V> Cache<K, V> create(String name, CacheType cacheType, Duration expire, Boolean cacheNullValue, Boolean syncLocal) {
//    return getInstance().getJetCacheCreateCacheFactory().create(name, cacheType, expire, cacheNullValue, syncLocal);
//  }
//
//  private void init(JetCacheCreateCacheFactory jetCacheCreateCacheFactory) {
//    this.jetCacheCreateCacheFactory = jetCacheCreateCacheFactory;
//  }
//
//  private JetCacheCreateCacheFactory getJetCacheCreateCacheFactory() {
//    return this.jetCacheCreateCacheFactory;
//  }
//
//  public static void setJetCacheCreateCacheFactory(JetCacheCreateCacheFactory jetCacheCreateCacheFactory) {
//    getInstance().init(jetCacheCreateCacheFactory);
//  }
//}
