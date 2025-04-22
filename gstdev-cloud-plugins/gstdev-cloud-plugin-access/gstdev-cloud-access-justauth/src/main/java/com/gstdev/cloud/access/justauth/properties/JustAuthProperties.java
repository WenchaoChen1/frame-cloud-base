

package com.gstdev.cloud.access.justauth.properties;

import com.gstdev.cloud.access.core.constants.AccessConstants;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.zhyd.oauth.config.AuthConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.Map;

/**
 * <p>Description: 用于支持JustAuth第三方登录的配置 </p>
 *
 * @author : cc
 * @date : 2021/5/16 10:24
 */
@Data
@ConfigurationProperties(prefix = AccessConstants.PROPERTY_ACCESS_JUSTAUTH)
public class JustAuthProperties {

    /**
     * 是否开启
     */
    private Boolean enabled;
    /**
     * State 缓存时长，默认5分钟
     */
    private Duration timeout = Duration.ofMinutes(5);
    /**
     * 第三方系统登录配置信息
     */
    private Map<String, AuthConfig> configs;

    private HttpConfig httpConfig;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public void setTimeout(Duration timeout) {
        this.timeout = timeout;
    }

    public Map<String, AuthConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(Map<String, AuthConfig> configs) {
        this.configs = configs;
    }
    @Getter
    @Setter
    public static class HttpConfig{
        /**
         * 是否开启
         */
        private Boolean enabled;
        private int timeout = 3000;
        private String hostName="127.0.0.1";
        private int port=7890;
        private Type type=Type.HTTP;

        public enum Type {
            /**
             * Represents a direct connection, or the absence of a proxy.
             */
            DIRECT,
            /**
             * Represents proxy for high level protocols such as HTTP or FTP.
             */
            HTTP,
            /**
             * Represents a SOCKS (V4 or V5) proxy.
             */
            SOCKS
        };
    }

}
