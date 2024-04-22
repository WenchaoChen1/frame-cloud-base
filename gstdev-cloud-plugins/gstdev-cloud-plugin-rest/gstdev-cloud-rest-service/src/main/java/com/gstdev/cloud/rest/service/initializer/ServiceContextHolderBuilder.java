package com.gstdev.cloud.rest.service.initializer;

import com.gstdev.cloud.base.core.context.ServiceContextHolder;
import com.gstdev.cloud.base.core.utils.WellFormedUtils;
import com.gstdev.cloud.rest.condition.properties.EndpointProperties;
import com.gstdev.cloud.rest.condition.properties.PlatformProperties;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.web.ServerProperties;

/**
 * <p>Description: ServiceContextHolder 构建器 </p>
 *
 * @author : cc
 * @date : 2023/10/2 18:41
 */
public class ServiceContextHolderBuilder {

  private PlatformProperties platformProperties;
  private EndpointProperties endpointProperties;
  private ServerProperties serverProperties;

  private ServiceContextHolderBuilder() {

  }

  public static ServiceContextHolderBuilder builder() {
    return new ServiceContextHolderBuilder();
  }

  public ServiceContextHolderBuilder platformProperties(PlatformProperties platformProperties) {
    this.platformProperties = platformProperties;
    return this;
  }

  public ServiceContextHolderBuilder endpointProperties(EndpointProperties endpointProperties) {
    this.endpointProperties = endpointProperties;
    return this;
  }

  public ServiceContextHolderBuilder serverProperties(ServerProperties serverProperties) {
    this.serverProperties = serverProperties;
    return this;
  }

  public ServiceContextHolder build() {
    ServiceContextHolder holder = ServiceContextHolder.getInstance();
//        toServiceContextHolder(platformProperties, holder);
//        toServiceContextHolder(endpointProperties, holder, holder.isDistributedArchitecture());
//        holder.setPort(String.valueOf(this.getPort()));
//        holder.setIp(getHostAddress());
    return holder;
  }

  private String getHostAddress() {
    String address = WellFormedUtils.getHostAddress();
    if (ObjectUtils.isNotEmpty(serverProperties.getAddress())) {
      address = serverProperties.getAddress().getHostAddress();
    }

    if (StringUtils.isNotBlank(address)) {
      return address;
    } else {
      return "localhost";
    }
  }

  private Integer getPort() {
    Integer port = serverProperties.getPort();
    if (ObjectUtils.isNotEmpty(port)) {
      return port;
    } else {
      return 8080;
    }
  }

  private void toServiceContextHolder(PlatformProperties platformProperties, ServiceContextHolder serviceContextHolder) {
    serviceContextHolder.setArchitecture(platformProperties.getArchitecture());
    serviceContextHolder.setDataAccessStrategy(platformProperties.getDataAccessStrategy());
    serviceContextHolder.setProtocol(platformProperties.getProtocol());
  }

  private void toServiceContextHolder(EndpointProperties endpointProperties, ServiceContextHolder serviceContextHolder, boolean isDistributedArchitecture) {
    if (!isDistributedArchitecture) {
      String issuerUri = endpointProperties.getIssuerUri();
      serviceContextHolder.setGatewayServiceUri(issuerUri);
      serviceContextHolder.setUaaServiceUri(issuerUri);
      serviceContextHolder.setUpmsServiceUri(issuerUri);
      serviceContextHolder.setMessageServiceUri(issuerUri);
      serviceContextHolder.setOssServiceUri(issuerUri);
    } else {
      serviceContextHolder.setUaaServiceName(endpointProperties.getUaaServiceName());
      serviceContextHolder.setUpmsServiceName(endpointProperties.getUpmsServiceName());
      serviceContextHolder.setMessageServiceName(endpointProperties.getMessageServiceName());
      serviceContextHolder.setOssServiceName(endpointProperties.getOssServiceName());
      serviceContextHolder.setGatewayServiceUri(endpointProperties.getGatewayServiceUri());
      serviceContextHolder.setUaaServiceUri(endpointProperties.getUaaServiceUri());
      serviceContextHolder.setUpmsServiceUri(endpointProperties.getUpmsServiceUri());
      serviceContextHolder.setMessageServiceUri(endpointProperties.getMessageServiceUri());
      serviceContextHolder.setOssServiceUri(endpointProperties.getOssServiceUri());
    }

    serviceContextHolder.setAuthorizationUri(endpointProperties.getAuthorizationUri());
    serviceContextHolder.setAuthorizationEndpoint(endpointProperties.getAuthorizationEndpoint());
    serviceContextHolder.setAccessTokenUri(endpointProperties.getAccessTokenUri());
    serviceContextHolder.setAccessTokenEndpoint(endpointProperties.getAccessTokenEndpoint());
    serviceContextHolder.setJwkSetUri(endpointProperties.getJwkSetUri());
    serviceContextHolder.setJwkSetEndpoint(endpointProperties.getJwkSetEndpoint());
    serviceContextHolder.setTokenRevocationUri(endpointProperties.getTokenRevocationUri());
    serviceContextHolder.setTokenRevocationEndpoint(endpointProperties.getTokenRevocationEndpoint());
    serviceContextHolder.setTokenIntrospectionUri(endpointProperties.getTokenIntrospectionUri());
    serviceContextHolder.setTokenIntrospectionEndpoint(endpointProperties.getTokenIntrospectionEndpoint());
    serviceContextHolder.setDeviceAuthorizationUri(endpointProperties.getDeviceAuthorizationUri());
    serviceContextHolder.setDeviceAuthorizationEndpoint(endpointProperties.getDeviceAuthorizationEndpoint());
    serviceContextHolder.setDeviceVerificationUri(endpointProperties.getDeviceVerificationUri());
    serviceContextHolder.setDeviceVerificationEndpoint(endpointProperties.getDeviceVerificationEndpoint());
    serviceContextHolder.setOidcClientRegistrationUri(endpointProperties.getOidcClientRegistrationUri());
    serviceContextHolder.setOidcClientRegistrationEndpoint(endpointProperties.getOidcClientRegistrationEndpoint());
    serviceContextHolder.setOidcLogoutUri(endpointProperties.getOidcLogoutUri());
    serviceContextHolder.setOidcLogoutEndpoint(endpointProperties.getOidcLogoutEndpoint());
    serviceContextHolder.setOidcUserInfoUri(endpointProperties.getOidcUserInfoUri());
    serviceContextHolder.setOidcUserInfoEndpoint(endpointProperties.getOidcUserInfoEndpoint());
    serviceContextHolder.setIssuerUri(endpointProperties.getIssuerUri());
  }
}
