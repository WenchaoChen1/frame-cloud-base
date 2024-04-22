//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev Tech <support@gstdev.com>
//// Copyright (c) 2020-2025 gstdev.com
////
//// ====================================================
//
//package com.gstdev.cloud.commons.concurrent;
//
//import com.gstdev.cloud.commons.web.SpringContextHolder;
//
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//public class ThreadPoolExecutorUtils {
//
//  public static ThreadPoolExecutor getPoll() {
//    AsyncTaskProperties properties = SpringContextHolder.getBean(AsyncTaskProperties.class);
//
//    return new ThreadPoolExecutor(
//      properties.getCorePoolSize(),
//      properties.getMaxPoolSize(),
//      properties.getKeepAliveSeconds(),
//      TimeUnit.SECONDS,
//      new ArrayBlockingQueue<>(properties.getQueueCapacity()),
//      new TheadFactoryName()
//    );
//  }
//}
