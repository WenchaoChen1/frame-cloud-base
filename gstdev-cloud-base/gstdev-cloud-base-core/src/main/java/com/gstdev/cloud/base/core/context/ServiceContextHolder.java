package com.gstdev.cloud.base.core.context;


import com.gstdev.cloud.base.core.enums.Architecture;
import com.gstdev.cloud.base.core.enums.Protocol;
import com.gstdev.cloud.base.core.enums.Target;
import com.gstdev.cloud.base.core.utils.WellFormedUtils;
import com.gstdev.cloud.base.definition.constants.DefaultConstants;
import com.gstdev.cloud.base.definition.constants.SymbolConstants;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 * <p>Description: 服务上下文信息工具类 </p>
 *
 * @author : cc
 * @date : 2022/1/14 17:28
 */

@Getter
@Setter
public class ServiceContextHolder {

    private static volatile ServiceContextHolder instance;

    /**
     * 平台架构类型，默认：DISTRIBUTED（分布式架构）
     */
    private Architecture architecture = Architecture.DISTRIBUTED;
    /**
     * 数据访问策略，默认：
     */
    private Target dataAccessStrategy = Target.REMOTE;

    /**
     * 协议头类型
     */
    private Protocol protocol = Protocol.HTTP;
    /**
     * 服务端口号
     */
    private String port;
    /**
     * 服务IP地址
     */
    private String ip;
    /**
     * 服务地址，格式：ip:port
     */
    private String address;
    /**
     * 服务Url，格式：http://ip:port
     */
    private String url;
    /**
     * 应用名称，与spring.application.name一致
     */
    private String applicationName;
    /**
     * 留存一份ApplicationContext
     */
    private ApplicationContext applicationContext;


    /**
     * identity中心服务名称
     */
    private String identityServiceName;
    /**
     * system中心服务名称
     */
    private String systemServiceName;
    /**
     * 消息服务名称
     */
    private String messageServiceName;
    /**
     * 对象存储服务名称
     */
    private String ossServiceName;

    /**
     * 统一网关服务地址。可以是IP+端口，可以是域名
     */
    private String gatewayServiceUri;

    /**
     * 统一认证中心服务地址
     */
    private String identityServiceUri;
    /**
     * 统一权限管理服务地址
     */
    private String systemServiceUri;

    /**
     * 消息服务地址
     */
    private String messageServiceUri;
    /**
     * 对象存储服务地址
     */
    private String ossServiceUri;


    /**
     * OAuth2 Authorization Code 模式认证端点，/oauth2/authorize uri 地址，可修改为自定义地址
     */
    private String authorizationUri;
    /**
     * OAuth2 Authorization Code 模式认证端点，/oauth2/authorize端点地址，可修改为自定义地址
     */
    private String authorizationEndpoint = DefaultConstants.AUTHORIZATION_ENDPOINT;
    /**
     * OAuth2 /oauth2/token 申请 Token uri 地址，可修改为自定义地址
     */
    private String accessTokenUri;
    /**
     * OAuth2 /oauth2/token 申请 Token 端点地址，可修改为自定义地址
     */
    private String accessTokenEndpoint = DefaultConstants.TOKEN_ENDPOINT;
    /**
     * OAuth2 /oauth2/jwks uri 地址，可修改为自定义地址
     */
    private String jwkSetUri;
    /**
     * OAuth2 /oauth2/jwks 端点地址，可修改为自定义地址
     */
    private String jwkSetEndpoint = DefaultConstants.JWK_SET_ENDPOINT;
    /**
     * OAuth2 /oauth2/revoke 撤销 Token uri 地址，可修改为自定义地址
     */
    private String tokenRevocationUri;
    /**
     * OAuth2 /oauth2/revoke 撤销 Token 端点地址，可修改为自定义地址
     */
    private String tokenRevocationEndpoint = DefaultConstants.TOKEN_REVOCATION_ENDPOINT;
    /**
     * OAuth2 /oauth2/introspect 查看 Token uri地址，可修改为自定义地址
     */
    private String tokenIntrospectionUri;
    /**
     * OAuth2 /oauth2/introspect 查看 Token 端点地址，可修改为自定义地址
     */
    private String tokenIntrospectionEndpoint = DefaultConstants.TOKEN_INTROSPECTION_ENDPOINT;
    /**
     * OAuth2 /oauth2/device_authorization 设备授权认证 uri地址，可修改为自定义地址
     */
    private String deviceAuthorizationUri;
    /**
     * OAuth2 /oauth2/device_authorization 设备授权认证端点地址，可修改为自定义地址
     */
    private String deviceAuthorizationEndpoint = DefaultConstants.DEVICE_AUTHORIZATION_ENDPOINT;
    /**
     * OAuth2 /oauth2/device_verification 设备授权校验 uri地址，可修改为自定义地址
     */
    private String deviceVerificationUri;
    /**
     * OAuth2 /oauth2/device_verification 设备授权校验端点地址，可修改为自定义地址
     */
    private String deviceVerificationEndpoint = DefaultConstants.DEVICE_VERIFICATION_ENDPOINT;
    /**
     * OAuth2 OIDC /connect/register uri 地址，可修改为自定义地址
     */
    private String oidcClientRegistrationUri;
    /**
     * OAuth2 OIDC /connect/register 端点地址，可修改为自定义地址
     */
    private String oidcClientRegistrationEndpoint = DefaultConstants.OIDC_CLIENT_REGISTRATION_ENDPOINT;
    /**
     * OAuth2 OIDC /connect/logout uri 地址，可修改为自定义地址
     */
    private String oidcLogoutUri;
    /**
     * OAuth2 OIDC /connect/logout 端点地址，可修改为自定义地址
     */
    private String oidcLogoutEndpoint = DefaultConstants.OIDC_LOGOUT_ENDPOINT;
    /**
     * OAuth2 OIDC /userinfo uri 地址，可修改为自定义地址
     */
    private String oidcUserInfoUri;
    /**
     * OAuth2 OIDC /userinfo 端点地址，可修改为自定义地址
     */
    private String oidcUserInfoEndpoint = DefaultConstants.OIDC_USER_INFO_ENDPOINT;
    /**
     * Spring Authorization Server Issuer Url
     */
    private String issuerUri;

    private ServiceContextHolder() {

    }

    public static ServiceContextHolder getInstance() {
        if (ObjectUtils.isEmpty(instance)) {
            synchronized (ServiceContextHolder.class) {
                if (ObjectUtils.isEmpty(instance)) {
                    instance = new ServiceContextHolder();
                }
            }
        }

        return instance;
    }

    public String getAddress() {
        if (isDistributedArchitecture()) {
            this.address = this.getGatewayServiceUri() + SymbolConstants.FORWARD_SLASH + this.getApplicationName();
        } else {
            if (StringUtils.isNotBlank(this.ip) && StringUtils.isNotBlank(this.port)) {
                this.address = this.ip + SymbolConstants.COLON + this.port;
            }
        }
        return address;
    }

    public String getUrl() {
        if (StringUtils.isBlank(this.url)) {
            String address1 = this.getAddress();
            if (StringUtils.isNotBlank(address1)) {
                return WellFormedUtils.addressToUri(address1, getProtocol(), false);
            }
        }
        return url;
    }

    public boolean isDistributedArchitecture() {
        return this.getArchitecture() == Architecture.DISTRIBUTED;
    }

    public String getOriginService() {
        return getApplicationName() + SymbolConstants.COLON + getPort();
    }

    public void publishEvent(ApplicationEvent applicationEvent) {
        getApplicationContext().publishEvent(applicationEvent);
    }
}
