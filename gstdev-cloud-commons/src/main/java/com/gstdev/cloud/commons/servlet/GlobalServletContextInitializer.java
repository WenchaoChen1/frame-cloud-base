//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev <support@gstdev.com>
//// Copyright (c) 2020-2025 gstdev.com
////
//// ====================================================
//
//package com.gstdev.cloud.commons.servlet;
//
//import com.gstdev.cloud.commons.listener.GlobalServletRequestListener;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.web.servlet.ServletContextInitializer;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import jakarta.servlet.ServletContext;
//import jakarta.servlet.ServletException;
//
//@Slf4j
//@Configuration
//public class GlobalServletContextInitializer implements ServletContextInitializer {
//
//  private final Environment env;
//
//  public GlobalServletContextInitializer(Environment env) {
//    this.env = env;
//  }
//
//  @Override
//  public void onStartup(ServletContext servletContext) throws ServletException {
//    if (env.getActiveProfiles().length != 0) {
//      log.info("[GstDev Cloud] |- Web application configuration, using profiles: {}", (Object[]) env.getActiveProfiles());
//    }
//
//    servletContext.addListener(GlobalServletRequestListener.class);
//  }
//}
