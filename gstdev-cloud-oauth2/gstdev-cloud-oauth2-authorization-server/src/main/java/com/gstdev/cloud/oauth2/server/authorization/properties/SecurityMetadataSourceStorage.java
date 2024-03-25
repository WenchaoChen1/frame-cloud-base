//package com.gstdev.cloud.oauth2.server.authorization.properties;
//
//
//import org.apache.commons.collections4.MapUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.time.Duration;
//import java.util.Iterator;
//import java.util.LinkedHashMap;
//import java.util.List;
//
//public class SecurityMetadataSourceStorage {
//  private static final Logger log = LoggerFactory.getLogger(SecurityMetadataSourceStorage.class);
//  private static final String KEY_COMPATIBLE = "COMPATIBLE";
//  private final Cache<String, LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>>> compatible;
//  private final Cache<HerodotusRequest, List<HerodotusConfigAttribute>> indexable;
//
//  public SecurityMetadataSourceStorage() {
//    this.compatible = JetCacheUtils.create("cache:security:metadata:compatible:", CacheType.BOTH, (Duration)null, true, true);
//    this.indexable = JetCacheUtils.create("cache:security:metadata:indexable:", CacheType.BOTH, (Duration)null, true, true);
//  }
//
//  private LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> readFromCompatible() {
//    LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> compatible = (LinkedHashMap)this.compatible.get("COMPATIBLE");
//    return MapUtils.isNotEmpty(compatible) ? compatible : new LinkedHashMap();
//  }
//
//  private void writeToCompatible(LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> compatible) {
//    this.compatible.put("COMPATIBLE", compatible);
//  }
//
//  private List<HerodotusConfigAttribute> readFromIndexable(HerodotusRequest herodotusRequest) {
//    return (List)this.indexable.get(herodotusRequest);
//  }
//
//  private void writeToIndexable(HerodotusRequest herodotusRequest, List<HerodotusConfigAttribute> configAttributes) {
//    this.indexable.put(herodotusRequest, configAttributes);
//  }
//
//  public List<HerodotusConfigAttribute> getConfigAttribute(String url, String method) {
//    HerodotusRequest herodotusRequest = new HerodotusRequest(url, method);
//    return this.readFromIndexable(herodotusRequest);
//  }
//
//  public LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> getCompatible() {
//    return this.readFromCompatible();
//  }
//
//  private void appendToCompatible(HerodotusRequest herodotusRequest, List<HerodotusConfigAttribute> configAttributes) {
//    LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> compatible = this.getCompatible();
//    compatible.put(herodotusRequest, configAttributes);
//    log.trace("[GstDev Cloud] |- Append [{}] to Compatible cache, current size is [{}]", herodotusRequest, compatible.size());
//    this.writeToCompatible(compatible);
//  }
//
//  private void appendToCompatible(LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> configAttributes) {
//    configAttributes.forEach(this::appendToCompatible);
//  }
//
//  private void appendToIndexable(HerodotusRequest herodotusRequest, List<HerodotusConfigAttribute> configAttributes) {
//    this.writeToIndexable(herodotusRequest, configAttributes);
//  }
//
//  private void appendToIndexable(LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> configAttributes) {
//    configAttributes.forEach(this::appendToIndexable);
//  }
//
//  public void addToStorage(LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> configAttributes, boolean isIndexable) {
//    if (MapUtils.isNotEmpty(configAttributes)) {
//      if (isIndexable) {
//        this.appendToIndexable(configAttributes);
//      } else {
//        this.appendToCompatible(configAttributes);
//      }
//    }
//
//  }
//
//  public void addToStorage(LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> matchers, LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> configAttributes, boolean isIndexable) {
//    LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> result = new LinkedHashMap();
//    if (MapUtils.isNotEmpty(matchers) && MapUtils.isNotEmpty(configAttributes)) {
//      result = this.checkConflict(matchers, configAttributes);
//    }
//
//    this.addToStorage(result, isIndexable);
//  }
//
//  private LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> checkConflict(LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> matchers, LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> configAttributes) {
//    LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> result = new LinkedHashMap(configAttributes);
//    Iterator var4 = matchers.keySet().iterator();
//
//    while(var4.hasNext()) {
//      HerodotusRequest matcher = (HerodotusRequest)var4.next();
//      Iterator var6 = configAttributes.keySet().iterator();
//
//      while(var6.hasNext()) {
//        HerodotusRequest item = (HerodotusRequest)var6.next();
//        HerodotusRequestMatcher requestMatcher = new HerodotusRequestMatcher(matcher);
//        if (requestMatcher.matches(item)) {
//          result.remove(item);
//          log.trace("[GstDev Cloud] |- Pattern [{}] is conflict with [{}], so remove it.", item.getPattern(), matcher.getPattern());
//        }
//      }
//    }
//
//    return result;
//  }
//}
