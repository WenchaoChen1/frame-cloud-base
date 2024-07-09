package com.gstdev.cloud.base.core.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

public class SecureUtil {

    static {
        // 向Java安全提供者列表中添加BouncyCastle提供者
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 生成输入字符串的MD5哈希值
     *
     * @param input 输入字符串
     * @return MD5哈希值
     */
    public static String md5(String input) {
        return hash(input, "MD5");
    }

    /**
     * 生成输入字符串的SHA-256哈希值
     *
     * @param input 输入字符串
     * @return SHA-256哈希值
     */
    public static String sha256(String input) {
        return hash(input, "SHA-256");
    }


    /**
     * 生成输入字符串的SHA3-224哈希值
     *
     * @param input 输入字符串
     * @return SHA3-224哈希值
     */
    public static String sha3_224(String input) {
        return hash(input, "SHA3-224", "BC");
    }

    /**
     * 生成输入字符串的SHA3-256哈希值
     *
     * @param input 输入字符串
     * @return SHA3-256哈希值
     */
    public static String sha3_256(String input) {
        return hash(input, "SHA3-256", "BC");
    }

    /**
     * 生成输入字符串的SHA3-384哈希值
     *
     * @param input 输入字符串
     * @return SHA3-384哈希值
     */
    public static String sha3_384(String input) {
        return hash(input, "SHA3-384", "BC");
    }

    /**
     * 生成输入字符串的SHA3-512哈希值
     *
     * @param input 输入字符串
     * @return SHA3-512哈希值
     */
    public static String sha3_512(String input) {
        return hash(input, "SHA3-512", "BC");
    }

    /**
     * 通用的哈希生成方法
     *
     * @param input     输入字符串
     * @param algorithm 哈希算法
     * @return 哈希值
     */
    private static String hash(String input, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] digest = md.digest(input.getBytes());
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通用的哈希生成方法（指定提供者）
     *
     * @param input     输入字符串
     * @param algorithm 哈希算法
     * @param provider  提供者
     * @return 哈希值
     */
    private static String hash(String input, String algorithm, String provider) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm, provider);
            byte[] digest = md.digest(input.getBytes());
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "Hello, world!";
        System.out.println("MD5: " + md5(input));
        System.out.println("SHA-256: " + sha256(input));
        System.out.println("SHA3-224: " + sha3_224(input));
        System.out.println("SHA3-256: " + sha3_256(input));
        System.out.println("SHA3-384: " + sha3_384(input));
        System.out.println("SHA3-512: " + sha3_512(input));
    }
}