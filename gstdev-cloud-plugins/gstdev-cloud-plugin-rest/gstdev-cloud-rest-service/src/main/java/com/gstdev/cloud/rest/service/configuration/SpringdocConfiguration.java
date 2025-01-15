//package com.gstdev.cloud.rest.service.configuration;
//
//import com.gstdev.cloud.commons.ass.core.annotation.ConditionalOnSwaggerEnabled;
//import com.gstdev.cloud.commons.ass.core.context.ServiceContextHolder;
//import com.gstdev.cloud.commons.ass.definition.constants.BaseConstants;
//import com.gstdev.cloud.rest.core.definition.OpenApiServerResolver;
//import com.gstdev.cloud.rest.condition.properties.SwaggerProperties;
//import com.google.common.collect.ImmutableList;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.v3.oas.annotations.security.*;
//import io.swagger.v3.oas.models.ExternalDocumentation;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import io.swagger.v3.oas.models.servers.Server;
//import jakarta.annotation.PostConstruct;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * <p>Description: 服务信息配置类 </p>
// *
// * @author : cc
// * @date : 2021/6/13 13:40
// */
//@Configuration(proxyBeanMethods = false)
//@ConditionalOnSwaggerEnabled
//@EnableConfigurationProperties(SwaggerProperties.class)
//@SecuritySchemes({
//        @SecurityScheme(name = BaseConstants.OPEN_API_SECURITY_SCHEME_BEARER_NAME, type = SecuritySchemeType.OAUTH2, bearerFormat = "JWT", scheme = "bearer",
//                flows = @OAuthFlows(
//                        password = @OAuthFlow(authorizationUrl = "http://localhost:8101/oauth2/authorize", tokenUrl = "http://localhost:8101/oauth2/token"
//                          , refreshUrl = "http://localhost:8101/oauth2/token", scopes = @OAuthScope(name = "all"))
////                        password = @OAuthFlow(authorizationUrl = "${frame.endpoint.authorization-uri}", tokenUrl = "${frame.endpoint.access-token-uri}", refreshUrl = "${frame.endpoint.access-token-uri}", scopes = @OAuthScope(name = "all")),
////                        clientCredentials = @OAuthFlow(authorizationUrl = "${frame.endpoint.authorization-uri}", tokenUrl = "${frame.endpoint.access-token-uri}", refreshUrl = "${frame.endpoint.access-token-uri}", scopes = @OAuthScope(name = "all"))
////                        authorizationCode = @OAuthFlow(authorizationUrl = "${frame.platform.endpoint.user-authorization-uri}", tokenUrl = "${frame.platform.endpoint.access-token-uri}", refreshUrl = "${frame.platform.endpoint.access-token-uri}", scopes = @OAuthScope(name = "all"))
//                )),
//})
//public class SpringdocConfiguration {
//
//    private static final Logger log = LoggerFactory.getLogger(SpringdocConfiguration.class);
//
//    @PostConstruct
//    public void postConstruct() {
//        log.debug("[GstDev Cloud] |- Module [Open Api] Auto Configure.");
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public OpenApiServerResolver openApiServerResolver() {
//        OpenApiServerResolver resolver = () -> {
//            Server server = new Server();
//            server.setUrl(ServiceContextHolder.getInstance().getUrl());
//            return ImmutableList.of(server);
//        };
//        log.trace("[GstDev Cloud] |- Bean [Open Api Server Resolver] Auto Configure.");
//        return resolver;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public OpenAPI createOpenApi(OpenApiServerResolver openApiServerResolver) {
//        return new OpenAPI()
//                .servers(openApiServerResolver.getServers())
//                .info(new Info().title("Gstdev Cloud")
//                        .description("Gstdev Cloud Microservices Architecture")
//                        .version("Swagger V3")
//                        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/")))
//                .externalDocs(new ExternalDocumentation()
//                        .description("Gstdev Cloud Documentation")
//                        .url(" https://www.frame.cn"));
//    }
//}
