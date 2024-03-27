//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev Tech <support@gstdev.com>
//// Copyright (c) 2020-2025 gstdev.com
////
//// ====================================================
//
//package com.gstdev.cloud.commons.utils;
//
//public class NumUtils {
//
//  public static Integer decode2int(String str, Integer defaultValue) {
//    if (str == null) {
//      return defaultValue;
//    }
//
//    try {
//      return Long.decode(str).intValue();
//    } catch (Exception e) {
//      return defaultValue;
//    }
//  }
//
//  public static String toHex(int hex) {
//    String str = Integer.toHexString(hex);
//    if (str.length() == 1) {
//      return "0" + str;
//    }
//
//    return str;
//  }
//}
