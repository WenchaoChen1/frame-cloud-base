package com.gstdev.cloud.openapi.springdoc;

import com.google.common.collect.ImmutableList;
import com.gstdev.cloud.commons.ass.core.annotation.ConditionalOnSwaggerEnabled;
import com.gstdev.cloud.commons.ass.core.context.ServiceContextHolder;
import com.gstdev.cloud.commons.ass.definition.constants.BaseConstants;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 服务信息配置类 </p>
 *
 * @author : cc
 * @date : 2021/6/13 13:40
 */
@Configuration(proxyBeanMethods = false)
//@ConditionalOnSwaggerEnabled
@EnableConfigurationProperties(SpringDocProperties.class)
//@SecuritySchemes({
//  @SecurityScheme(name = BaseConstants.OPEN_API_SECURITY_SCHEME_BEARER_NAME, type = SecuritySchemeType.OAUTH2, bearerFormat = "JWT", scheme = "bearer",
//    flows = @OAuthFlows(
//      password = @OAuthFlow(authorizationUrl = "http://localhost:8101/oauth2/authorize", tokenUrl = "http://localhost:8101/oauth2/token"
//        , refreshUrl = "http://localhost:8101/oauth2/token", scopes = @OAuthScope(name = "all"))
////                        password = @OAuthFlow(authorizationUrl = "${herodotus.endpoint.authorization-uri}", tokenUrl = "${herodotus.endpoint.access-token-uri}", refreshUrl = "${herodotus.endpoint.access-token-uri}", scopes = @OAuthScope(name = "all")),
////                        clientCredentials = @OAuthFlow(authorizationUrl = "${herodotus.endpoint.authorization-uri}", tokenUrl = "${herodotus.endpoint.access-token-uri}", refreshUrl = "${herodotus.endpoint.access-token-uri}", scopes = @OAuthScope(name = "all"))
////                        authorizationCode = @OAuthFlow(authorizationUrl = "${herodotus.platform.endpoint.user-authorization-uri}", tokenUrl = "${herodotus.platform.endpoint.access-token-uri}", refreshUrl = "${herodotus.platform.endpoint.access-token-uri}", scopes = @OAuthScope(name = "all"))
//    )),
//})
public class SpringDocConfiguration {

  private static final Logger log = LoggerFactory.getLogger(SpringDocConfiguration.class);
  private final SpringDocProperties springDocProperties;

  public SpringDocConfiguration(SpringDocProperties springDocProperties) {
    this.springDocProperties = springDocProperties;
  }

  @PostConstruct
  public void postConstruct() {
    log.debug("[GstDev Cloud] |- Module [Open Api] Auto Configure.");
  }

  @Bean
  @ConditionalOnMissingBean
  public OpenApiServerResolver openApiServerResolver() {
    OpenApiServerResolver resolver = () -> {
      Server server = new Server();
      server.setUrl(ServiceContextHolder.getInstance().getUrl());
        Server server1 = new Server();
        server1.setUrl(ServiceContextHolder.getInstance().getUrl());
      return ImmutableList.of(server);
    };
    log.trace("[GstDev Cloud] |- Bean [Open Api Server Resolver] Auto Configure.");
    return resolver;
  }

  @Bean
  @ConditionalOnMissingBean
  public OpenAPI createOpenApi(OpenApiServerResolver openApiServerResolver) {
    return new OpenAPI()
      .servers(openApiServerResolver.getServers())
      .info(new Info().title("GstDev Cloud")
        .description("GstDev Cloud Microservices Architecture")
        .version("Swagger V3")
        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/")))
      .externalDocs(new ExternalDocumentation()
        .description("GstDev Cloud Documentation")
        .url(" https://www.gstdev.com"));
  }
}
