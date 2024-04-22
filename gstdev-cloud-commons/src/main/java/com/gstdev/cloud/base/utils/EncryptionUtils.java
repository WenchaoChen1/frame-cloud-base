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
//import org.bouncycastle.crypto.digests.SHA3Digest;
//
//public class EncryptionUtils {
//
//  private EncryptionUtils() {
//  }
//
//  public static String trimNewLines(String input) {
//    return input.replaceAll("-----BEGIN CERTIFICATE-----", "")
//      .replaceAll("-----END CERTIFICATE-----", "")
//      .replaceAll("\n", "")
//      .replaceAll("\r", "");
//  }
//
//  public static String getSha3Hash(String data) {
//    String trimmedData = trimNewLines(data);
//    byte[] dataBytes = trimmedData.getBytes();
//
//    SHA3Digest md = new SHA3Digest(256);
//    md.reset();
//    md.update(dataBytes, 0, dataBytes.length);
//
//    byte[] hashedBytes = new byte[256 / 8];
//    md.doFinal(hashedBytes, 0);
//
//    String sha3Hash = ByteUtils.toHexString(hashedBytes);
//
//    return sha3Hash;
//  }
//}
//
