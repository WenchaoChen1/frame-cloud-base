package com.gstdev.cloud.oauth2.management.compliance.listener;

import com.gstdev.cloud.base.definition.constants.SymbolConstants;
import com.gstdev.cloud.oauth2.core.constants.OAuth2Constants;
import com.gstdev.cloud.oauth2.management.compliance.OAuth2AccountStatusManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.nio.charset.StandardCharsets;

/**
 * <p>Description: 账户锁定状态监听 </p>
 *
 * @author : cc
 * @date : 2022/7/8 11:11
 */
public class AccountAutoEnableListener extends KeyExpirationEventMessageListener {

  private static final Logger log = LoggerFactory.getLogger(AccountAutoEnableListener.class);

  private final OAuth2AccountStatusManager accountStatusManager;

  public AccountAutoEnableListener(RedisMessageListenerContainer listenerContainer, OAuth2AccountStatusManager accountStatusManager) {
    super(listenerContainer);
    this.accountStatusManager = accountStatusManager;
  }

  @Override
  public void onMessage(Message message, byte[] pattern) {
    String key = new String(message.getBody(), StandardCharsets.UTF_8);
    if (StringUtils.contains(key, OAuth2Constants.CACHE_NAME_TOKEN_LOCKED_USER_DETAIL)) {
      String userId = StringUtils.substringAfterLast(key, SymbolConstants.COLON);
      log.info("[GstDev Cloud] |- Parse the user [{}] at expired redis cache key [{}]", userId, key);
      if (StringUtils.isNotBlank(userId)) {
        log.debug("[GstDev Cloud] |- Automatically unlock user account [{}]", userId);
        accountStatusManager.enable(userId);
      }
    }
  }
}
