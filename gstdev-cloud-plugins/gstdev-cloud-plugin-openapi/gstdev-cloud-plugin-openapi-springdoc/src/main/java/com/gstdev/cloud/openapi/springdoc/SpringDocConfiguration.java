package com.gstdev.cloud.openapi.springdoc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
@Slf4j
@EnableConfigurationProperties(SpringDocProperties.class)
@ConditionalOnProperty(prefix = "gstdev.cloud", value = "enabled", matchIfMissing = true)
//@EnableOpenApi
@Configuration
public class SpringDocConfiguration {

  private final SpringDocProperties springDocProperties;

  public SpringDocConfiguration(SpringDocProperties springDocProperties) {
    this.springDocProperties = springDocProperties;
  }

  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("程序员API")
        .description("程序员的大本营")
        .version("v1.0.0")
        .license(new License()
          .name("许可协议")
          .url("https://shusheng007.top"))
        .contact(new Contact()
          .name("书生007")
          .email("wangben850115@gmail.com")))
      .externalDocs(new ExternalDocumentation()
        .description("ShuSheng007博客")
        .url("https://shusheng007.top"));
  }


  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder()
      .group("springshop-public")
      .pathsToMatch("/public/**")
      .build();
  }
  @Bean
  public GroupedOpenApi adminApi() {
    return GroupedOpenApi.builder()
      .group("springshop-admin")
      .pathsToMatch("/admin/**")
//      .addOpenApiMethodFilter(method -> method.isAnnotationPresent(Admin.class))
      .build();
  }



}
