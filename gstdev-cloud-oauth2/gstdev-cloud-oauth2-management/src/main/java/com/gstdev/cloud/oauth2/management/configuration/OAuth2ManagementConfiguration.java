package com.gstdev.cloud.oauth2.management.configuration;

import com.gstdev.cloud.oauth2.authorization.server.configuration.OAuth2AuthenticationConfiguration;
import com.gstdev.cloud.oauth2.authorization.server.stamp.SignInFailureLimitedStampManager;
import com.gstdev.cloud.oauth2.data.jpa.configuration.OAuth2DataJpaConfiguration;
import com.gstdev.cloud.oauth2.management.compliance.listener.AuthenticationSuccessListener;
import com.gstdev.cloud.oauth2.management.response.OAuth2DeviceVerificationResponseHandler;
import com.gstdev.cloud.oauth2.management.response.OidcClientRegistrationResponseHandler;
import com.gstdev.cloud.oauth2.management.service.OAuth2ComplianceService;
import com.gstdev.cloud.oauth2.management.service.OAuth2DeviceService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p>Description: OAuth2 Manager 模块配置 </p>
 * <p>
 * {@link org.springframework.security.oauth2.jwt.JwtTimestampValidator}
 *
 * @author : cc
 * @date : 2022/2/26 12:35
 */
@Configuration(proxyBeanMethods = false)
@Import({OAuth2DataJpaConfiguration.class, OAuth2AuthenticationConfiguration.class, OAuth2ComplianceConfiguration.class})
@EntityScan(basePackages = {
  "com.gstdev.cloud.oauth2.management.entity"
})
@EnableJpaRepositories(basePackages = {
  "com.gstdev.cloud.oauth2.management.repository",
})
@ComponentScan(basePackages = {
  "com.gstdev.cloud.oauth2.management.service",
  "com.gstdev.cloud.oauth2.management.controller",
})
public class OAuth2ManagementConfiguration {

  private static final Logger log = LoggerFactory.getLogger(OAuth2ManagementConfiguration.class);

  @PostConstruct
  public void postConstruct() {
    log.debug("[GstDev Cloud] |- Module [OAuth2 Management] Auto Configure.");
  }

  @Bean
  @ConditionalOnMissingBean
  public AuthenticationSuccessListener authenticationSuccessListener(SignInFailureLimitedStampManager stampManager, OAuth2ComplianceService complianceService, OAuth2DeviceService deviceService) {
    AuthenticationSuccessListener listener = new AuthenticationSuccessListener(stampManager, complianceService);
    log.trace("[GstDev Cloud] |- Bean [OAuth2 Authentication Success Listener] Auto Configure.");
    return listener;
  }

  @Bean
  @ConditionalOnMissingBean
  public OAuth2DeviceVerificationResponseHandler oauth2DeviceVerificationResponseHandler(OAuth2DeviceService oauth2DeviceService) {
    OAuth2DeviceVerificationResponseHandler handler = new OAuth2DeviceVerificationResponseHandler(oauth2DeviceService);
    log.trace("[GstDev Cloud] |- Bean [OAuth2 Device Verification Response Handler] Auto Configure.");
    return handler;
  }

  @Bean
  @ConditionalOnMissingBean
  public OidcClientRegistrationResponseHandler oidcClientRegistrationResponseHandler(OAuth2DeviceService oauth2DeviceService) {
    OidcClientRegistrationResponseHandler handler = new OidcClientRegistrationResponseHandler(oauth2DeviceService);
    log.trace("[GstDev Cloud] |- Bean [Oidc Client Registration Response Handler] Auto Configure.");
    return handler;
  }

}
