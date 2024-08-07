package com.gstdev.cloud.oauth2.resource.server.properties;

import com.gstdev.cloud.base.core.enums.Target;
import com.gstdev.cloud.base.definition.properties.BaseProperties;
import com.gstdev.cloud.oauth2.core.constants.OAuth2Constants;
import com.gstdev.cloud.oauth2.core.enums.Certificate;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @program: frame-cloud-base
 * @description: OAuth2 配置属性
 * 仅认证服务会使用到的安全相关配置，这是与 OAuth2Properties 的主要区别。
 * @author: wenchao.chen
 * @create: 2024/03/25 15:04
 **/
@ConfigurationProperties(prefix = OAuth2Constants.PROPERTY_OAUTH2_AUTHORIZATION)
public class OAuth2AuthorizationProperties extends BaseProperties {

    /**
     * Token 校验是采用远程方式还是本地方式。
     */
    private Target validate = Target.REMOTE;

    /**
     * 是否使用严格模式。严格模式一定要求有权限，非严格模式没有权限管控的接口，只要认证通过也可以使用。
     */
    private Boolean strict = true;

    /**
     * JWT的密钥或者密钥对(JSON Web Key) 配置
     */
    private Jwk jwk = new Jwk();
    /**
     * 指定 Request Matcher 静态安全规则
     */
    private Matcher matcher = new Matcher();

    public Target getValidate() {
        return validate;
    }

    public void setValidate(Target validate) {
        this.validate = validate;
    }

    public Boolean getStrict() {
        return strict;
    }

    public void setStrict(Boolean strict) {
        this.strict = strict;
    }

    public Jwk getJwk() {
        return jwk;
    }

    public void setJwk(Jwk jwk) {
        this.jwk = jwk;
    }

    public Matcher getMatcher() {
        return matcher;
    }

    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }

    public static class Jwk {
//    keytool -genkeypair -alias gstdev-cloud -keyalg RSA -keysize 2048 -keystore gstdev-cloud.jks -validity 3650 -keypass gstdev-cloud -storepass gstdev-cloud


//    keytool -genkey -alias gstdev-cloud -keyalg RSA -keysize 1024 -keystore gstdev-cloud.jks -validity 365 -keypass gstdev-cloud -storepass gstdev-cloud
//    keytool -list -rfc --keystore gstdev-cloud.jks > gstdev-cloud.crt
//    ren gstdev-cloud.crt gstdev-cloud.pub

        /**
         * 证书策略：standard OAuth2 标准证书模式；custom 自定义证书模式
         */
        private Certificate certificate = Certificate.CUSTOM;
        /**
         * jks证书文件路径
         */
        private String jksKeyStore = "classpath*:certificate/gstdev-cloud.jks";
        /**
         * jks证书密码
         */
        private String jksKeyPassword = "gstdev-cloud";
        /**
         * jks证书密钥库密码
         */
        private String jksStorePassword = "gstdev-cloud";
        /**
         * jks证书别名
         */
        private String jksKeyAlias = "gstdev-cloud";

        public Certificate getCertificate() {
            return certificate;
        }

        public void setCertificate(Certificate certificate) {
            this.certificate = certificate;
        }

        public String getJksKeyStore() {
            return jksKeyStore;
        }

        public void setJksKeyStore(String jksKeyStore) {
            this.jksKeyStore = jksKeyStore;
        }

        public String getJksKeyPassword() {
            return jksKeyPassword;
        }

        public void setJksKeyPassword(String jksKeyPassword) {
            this.jksKeyPassword = jksKeyPassword;
        }

        public String getJksStorePassword() {
            return jksStorePassword;
        }

        public void setJksStorePassword(String jksStorePassword) {
            this.jksStorePassword = jksStorePassword;
        }

        public String getJksKeyAlias() {
            return jksKeyAlias;
        }

        public void setJksKeyAlias(String jksKeyAlias) {
            this.jksKeyAlias = jksKeyAlias;
        }

        @Override
        public String toString() {
            return "Jwk{" +
                    "certificate=" + certificate +
                    ", jksKeyStore='" + jksKeyStore + '\'' +
                    ", jksKeyPassword='" + jksKeyPassword + '\'' +
                    ", jksStorePassword='" + jksStorePassword + '\'' +
                    ", jksKeyAlias='" + jksKeyAlias + '\'' +
                    '}';
        }

        private enum Strategy {
            STANDARD, CUSTOM
        }
    }

    /**
     * 用于手动的指定 Request Matcher 安全规则。
     * <p>
     * permitAll 比较常用，因此先只增加该项。后续可根据需要添加
     */
    public static class Matcher {
        /**
         * 静态资源过滤
         */
        private List<String> staticResources;
        /**
         * Security "permitAll" 权限列表。
         */
        private List<String> permitAll;
        /**
         * 只校验是否请求中包含Token，不校验Token中是否包含该权限的资源
         */
        private List<String> hasAuthenticated;

        public List<String> getStaticResources() {
            return staticResources;
        }

        public void setStaticResources(List<String> staticResources) {
            this.staticResources = staticResources;
        }

        public List<String> getPermitAll() {
            return permitAll;
        }

        public void setPermitAll(List<String> permitAll) {
            this.permitAll = permitAll;
        }

        public List<String> getHasAuthenticated() {
            return hasAuthenticated;
        }

        public void setHasAuthenticated(List<String> hasAuthenticated) {
            this.hasAuthenticated = hasAuthenticated;
        }
    }
}
