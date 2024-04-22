//package com.gstdev.cloud.rest.protect.secure.interceptor;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
///**
// * <p>Description: Xss 过滤器 </p>
// *
// * @author : cc
// * @date : 2021/8/30 23:34
// */
//@Component
//public class XssHttpServletFilter implements Filter {
//
//  private static final Logger log = LoggerFactory.getLogger(XssHttpServletFilter.class);
//
//  @Override
//  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//    HttpServletRequest request = (HttpServletRequest) servletRequest;
//
//    XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(request);
//    log.trace("[GstDev Cloud] |- XssHttpServletFilter wrapper request for [{}].", request.getRequestURI());
//    filterChain.doFilter(xssRequest, servletResponse);
//  }
//}
