package com.gstdev.cloud.oauth2.authorization.server.autoconfigure;

import com.gstdev.cloud.message.core.logic.strategy.AccountStatusEventManager;
import com.gstdev.cloud.oauth2.authorization.server.autoconfigure.status.DefaultAccountStatusEventManager;
import com.gstdev.cloud.oauth2.management.configuration.OAuth2ManagementConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;


/**
 * @program: frame-cloud-base
 * @description: OAuth2 授权服务器自动配置模块
 * @author: wenchao.chen
 * @create: 2024/03/27 17:04
 **/

//@AutoConfiguration
//@Import({
//  OAuth2AuthenticationConfiguration.class,
//  OAuth2DataJpaConfiguration.class
//})
@AutoConfiguration
@Import({
  OAuth2ManagementConfiguration.class
})
public class OAuth2AuthorizationServerAutoConfiguration {

  private static final Logger log = LoggerFactory.getLogger(OAuth2AuthorizationServerAutoConfiguration.class);

  @PostConstruct
  public void postConstruct() {
    log.info("[GstDev Cloud] |- Module [OAuth2 Authorization Server Starter] Auto Configure.");
  }

  @Bean
  public AccountStatusEventManager accountStatusEventManager() {
    DefaultAccountStatusEventManager manager = new DefaultAccountStatusEventManager();
    log.trace("[GstDev Cloud] |- Bean [GstDev Cloud Account Status Event Manager] Auto Configure.");
    return manager;
  }

}
