
//package com.gstdev.cloud.commons.ass.definition.support;
//
//import com.gstdev.cloud.commons.ass.definition.domain.Pool;
//import com.gstdev.cloud.commons.ass.definition.exception.BorrowObjectFromPoolErrorException;
//import jakarta.annotation.Nonnull;
//import org.apache.commons.lang3.ObjectUtils;
//import org.apache.commons.pool2.PooledObjectFactory;
//import org.apache.commons.pool2.impl.GenericObjectPool;
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * <p>Description: 对象池抽象定义 </p>
// *
// * @author : cc
// * @date : 2023/11/6 13:36
// */
//public abstract class AbstractObjectPool<T> {
//
//    private static final Logger log = LoggerFactory.getLogger(AbstractObjectPool.class);
//
//    private final GenericObjectPool<T> genericObjectPool;
//
//    protected AbstractObjectPool(@Nonnull PooledObjectFactory<T> pooledObjectFactory, @Nonnull Pool pool) {
//        GenericObjectPoolConfig<T> config = new GenericObjectPoolConfig<>();
//        config.setMaxTotal(pool.getMaxTotal());
//        config.setMaxIdle(pool.getMaxIdle());
//        config.setMinIdle(pool.getMinIdle());
//        config.setMaxWait(pool.getMaxWait());
//        config.setMinEvictableIdleDuration(pool.getMinEvictableIdleDuration());
//        config.setSoftMinEvictableIdleDuration(pool.getSoftMinEvictableIdleDuration());
//        config.setLifo(pool.getLifo());
//        config.setBlockWhenExhausted(pool.getBlockWhenExhausted());
//        genericObjectPool = new GenericObjectPool<>(pooledObjectFactory, config);
//    }
//
//    public T get() {
//        try {
//            T object = genericObjectPool.borrowObject();
//            log.debug("[GstDev Cloud] |- Fetch object from object pool.");
//            return object;
//        } catch (Exception e) {
//            log.error("[GstDev Cloud] |- Can not fetch object from pool.", e);
//            throw new BorrowObjectFromPoolErrorException("Can not fetch object from pool.");
//        }
//    }
//
//    public void close(T client) {
//        if (ObjectUtils.isNotEmpty(client)) {
//            log.debug("[GstDev Cloud] |- Close object in pool.");
//            genericObjectPool.returnObject(client);
//        }
//    }
//}
