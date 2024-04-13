package com.gstdev.cloud.rest.autoconfigure;

import com.gstdev.cloud.rest.condition.annotation.ConditionalOnUseHttp2ClientAsRestClient;
import com.gstdev.cloud.rest.condition.annotation.ConditionalOnUseHttpClient5AsRestClient;
import com.gstdev.cloud.rest.condition.annotation.ConditionalOnUseSimpleClientAsRestClient;
import feign.hc5.ApacheHttp5Client;
import feign.http2client.Http2Client;
import jakarta.annotation.PostConstruct;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.*;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.http.HttpClient;

/**
 * <p>Description: Rest Template Configuration </p>
 * <p>
 * 准备去除Okhttp3支持
 * <a herf="https://github.com/spring-projects/spring-framework/issues/30919">去除 OkHttp3 支持。</a>
 * <p>
 * {@link ClientHttpRequestFactory} 具体用途参见： {@link ClientHttpRequestFactories}
 * 配置条件, 参见 {@link FeignAutoConfiguration}
 *
 * @author : cc
 * @date : 2020/5/29 17:32
 */
@AutoConfiguration(after = {FeignAutoConfiguration.class})
public class RestTemplateAutoConfiguration {

  private static final Logger log = LoggerFactory.getLogger(RestTemplateAutoConfiguration.class);

  @PostConstruct
  public void postConstruct() {
    log.info("[GstDev Cloud] |- Module [Rest Template] Auto Configure.");
  }

  @Bean
  @ConditionalOnClass({Http2Client.class, HttpClient.class})
  @ConditionalOnMissingBean(HttpClient.class)
  @ConditionalOnUseHttp2ClientAsRestClient
  public ClientHttpRequestFactory jdkClientHttpRequestFactory(HttpClient httpClient) {
    JdkClientHttpRequestFactory factory = new JdkClientHttpRequestFactory(httpClient);
    log.trace("[GstDev Cloud] |- Bean [Jdk Client Http Request Factory] Auto Configure.");
    return factory;
  }

  @Bean
  @ConditionalOnClass(ApacheHttp5Client.class)
  @ConditionalOnMissingBean(CloseableHttpClient.class)
  @ConditionalOnUseHttpClient5AsRestClient
  public ClientHttpRequestFactory httpComponentsClientHttpRequestFactory(CloseableHttpClient closeableHttpClient) {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(closeableHttpClient);
    log.trace("[GstDev Cloud] |- Bean [Http Components Http Request Factory] Auto Configure.");
    return factory;
  }

  @Bean
  @ConditionalOnUseSimpleClientAsRestClient
  @ConditionalOnMissingBean
  public ClientHttpRequestFactory SimpleClientHttpRequestFactory() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    log.trace("[GstDev Cloud] |- Bean [Simple Client Http Request Factory] Auto Configure.");
    return factory;
  }

  /**
   * 使用 @LoadBalanced 注解表示使用 loadbalancer 实现客户端负载均衡
   *
   * @return RestTemplate
   */
  @Bean
  @LoadBalanced
  public RestTemplate getRestTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {

    RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

    /**
     * 默认的 RestTemplate 有个机制是请求状态码非200 就抛出异常，会中断接下来的操作。
     * 如果不想中断对结果数据得解析，可以通过覆盖默认的 ResponseErrorHandler ，
     * 对hasError修改下，让他一直返回true，即是不检查状态码及抛异常了
     */
    ResponseErrorHandler responseErrorHandler = new ResponseErrorHandler() {
      @Override
      public boolean hasError(ClientHttpResponse response) throws IOException {
        return true;
      }

      @Override
      public void handleError(ClientHttpResponse response) throws IOException {

      }
    };

    restTemplate.setErrorHandler(responseErrorHandler);

    log.trace("[GstDev Cloud] |- Bean [Rest Template] Auto Configure.");
    return restTemplate;
  }
}
