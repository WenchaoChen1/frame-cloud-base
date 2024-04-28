package com.gstdev.cloud.sentinel.alibaba.autoconfigure;

import com.alibaba.csp.sentinel.adapter.spring.webflux.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.gstdev.cloud.base.core.json.jackson2.utils.Jackson2Utils;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.sentinel.alibaba.autoconfigure.enhance.FrameSentinelFeign;
import feign.Feign;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * <p>Description: 基础设施 Sentinel 配置 </p>
 *
 * @author : cc
 * @date : 2022/2/5 17:57
 * @see <a href="https://blog.csdn.net/ttzommed/article/details/90669320">参考文档</a>
 */
@AutoConfiguration
@ConditionalOnClass
public class AlibabaSentinelAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AlibabaSentinelAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- Module [Facility Alibaba Sentinel Starter] Auto Configure.");
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "feign.sentinel.enabled")
    public Feign.Builder feignSentinelBuilder() {
        return FrameSentinelFeign.builder();
    }

    /**
     * 限流、熔断统一处理类
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    public static class WebMvcHandler {
        @Bean
        public BlockExceptionHandler webmvcBlockExceptionHandler() {
            return (request, response, e) -> {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                Result<String> result = Result.failure("Too many request, please retry later.");
                response.getWriter().print(Jackson2Utils.toJson(result));
            };
        }

    }

    /**
     * 限流、熔断统一处理类
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    public static class WebfluxHandler {
        @Bean
        public BlockRequestHandler webfluxBlockExceptionHandler() {
            return (exchange, t) ->
                ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(Result.failure(t.getMessage())));
        }
    }
}
