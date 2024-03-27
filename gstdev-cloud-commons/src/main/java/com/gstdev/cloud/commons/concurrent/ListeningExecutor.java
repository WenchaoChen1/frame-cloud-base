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
//
//import java.util.concurrent.Callable;
//import java.util.concurrent.Executor;
//
//public interface ListeningExecutor extends Executor {
//
//  <T> ListenableFuture<T> executeAsync(Callable<T> task);
//
//  default <T> ListenableFuture<T> submit(Callable<T> task) {
//    return executeAsync(task);
//  }
//}
