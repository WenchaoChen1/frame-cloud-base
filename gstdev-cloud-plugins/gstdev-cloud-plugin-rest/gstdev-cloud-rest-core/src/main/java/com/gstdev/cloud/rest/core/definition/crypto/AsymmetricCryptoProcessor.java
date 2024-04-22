package com.gstdev.cloud.rest.core.definition.crypto;

import com.gstdev.cloud.base.definition.domain.oauth2.SecretKey;

/**
 * <p>Description: 非对称加密 </p>
 *
 * @author : cc
 * @date : 2022/5/1 15:06
 */
public interface AsymmetricCryptoProcessor {

  /**
   * 创建非对称算法，公钥私钥对。
   *
   * @return 非对称算法，公钥私钥对
   */
  SecretKey createSecretKey();


  /**
   * 用私钥解密
   *
   * @param privateKey 非对称算法 KeyPair 私钥
   * @param content    待解密数据
   * @return 解密后的数据
   */
  String decrypt(String content, String privateKey);

  /**
   * 用公钥加密
   *
   * @param publicKey 非对称算法 KeyPair 公钥
   * @param content   待加密数据
   * @return 加密后的数据
   */
  String encrypt(String content, String publicKey);
}
