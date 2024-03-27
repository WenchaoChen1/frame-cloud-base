package com.gstdev.cloud.oauth2.server.authorization.configuration;

import com.gstdev.cloud.oauth2.authentication.configuration.OAuth2AuthenticationConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;


/**
 * @program: frame-cloud-base
 * @description: OAuth2 授权服务器自动配置模块
 * @author: wenchao.chen
 * @create: 2024/03/27 17:04
 **/

@AutoConfiguration
@Import({
  OAuth2AuthenticationConfiguration.class
})
public class OAuth2AuthorizationServerAutoConfiguration {

  private static final Logger log = LoggerFactory.getLogger(OAuth2AuthorizationServerAutoConfiguration.class);

  @PostConstruct
  public void postConstruct() {
    log.info("[GstDev Cloud] |- Module [OAuth2 Authorization Server Starter] Auto Configure.");
  }


}
