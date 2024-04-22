package com.gstdev.cloud.rest.protect.crypto.processor;

import com.gstdev.cloud.rest.core.definition.crypto.SymmetricCryptoProcessor;
import org.dromara.hutool.core.codec.binary.Base64;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.core.util.ByteUtil;
import org.dromara.hutool.core.util.RandomUtil;
import org.dromara.hutool.crypto.SecureUtil;
import org.dromara.hutool.crypto.symmetric.AES;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: AES 加密算法处理器 </p>
 *
 * @author : cc
 * @date : 2022/5/2 16:56
 */
public class AESCryptoProcessor implements SymmetricCryptoProcessor {

  private static final Logger log = LoggerFactory.getLogger(AESCryptoProcessor.class);

  @Override
  public String createKey() {
    return RandomUtil.randomStringUpper(16);
  }

  @Override
  public String decrypt(String data, String key) {
    AES aes = SecureUtil.aes(ByteUtil.toUtf8Bytes(key));
    byte[] result = aes.decrypt(Base64.decode(ByteUtil.toUtf8Bytes(data)));
    log.trace("[GstDev Cloud] |- AES crypto decrypt data, value is : [{}]", result);
    return StrUtil.utf8Str(result);
  }

  @Override
  public String encrypt(String data, String key) {
    AES aes = SecureUtil.aes(ByteUtil.toUtf8Bytes(key));
    byte[] result = aes.encrypt(ByteUtil.toUtf8Bytes(data));
    log.trace("[GstDev Cloud] |- AES crypto encrypt data, value is : [{}]", result);
    return StrUtil.utf8Str(result);
  }
}
