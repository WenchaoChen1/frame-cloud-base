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
//import com.google.common.util.concurrent.ListenableFuture;
//import com.google.common.util.concurrent.ListeningExecutorService;
//import com.google.common.util.concurrent.MoreExecutors;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
//import java.util.concurrent.Callable;
//import java.util.concurrent.Executors;
//
//public abstract class AbstractListeningExecutor implements ListeningExecutor {
//
//  private ListeningExecutorService service;
//
//  @PostConstruct
//  public void init() {
//    this.service = MoreExecutors.listeningDecorator(Executors.newWorkStealingPool(getThreadPollSize()));
//  }
//
//  @PreDestroy
//  public void destroy() {
//    if (this.service != null) {
//      this.service.shutdown();
//    }
//  }
//
//  @Override
//  public <T> ListenableFuture<T> executeAsync(Callable<T> task) {
//    return service.submit(task);
//  }
//
//  @Override
//  public void execute(Runnable command) {
//    service.execute(command);
//  }
//
//  public ListeningExecutorService executor() {
//    return service;
//  }
//
//  protected abstract int getThreadPollSize();
//}
