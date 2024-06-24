package com.gstdev.cloud.rest.condition.properties;

import com.google.common.base.MoreObjects;
import com.gstdev.cloud.rest.condition.constants.RestConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.time.Duration;

/**
 * <p>Description: 跟踪标记配置属性 </p>
 *
 * @author : cc
 * @date : 2021/8/24 15:53
 */
@ConfigurationProperties(prefix = RestConstants.PROPERTY_PREFIX_SECURE)
public class SecureProperties {

    private Idempotent idempotent = new Idempotent();
    private AccessLimited accessLimited = new AccessLimited();

    public Idempotent getIdempotent() {
        return idempotent;
    }

    public void setIdempotent(Idempotent idempotent) {
        this.idempotent = idempotent;
    }

    public AccessLimited getAccessLimited() {
        return accessLimited;
    }

    public void setAccessLimited(AccessLimited accessLimited) {
        this.accessLimited = accessLimited;
    }

    public static class Idempotent implements Serializable {

        /**
         * 幂等签章缓存默认过期时间，以防Token删除失败后，缓存数据始终存在影响使用，默认：5秒
         */
        private Duration expire = Duration.ofSeconds(5);

        public Duration getExpire() {
            return expire;
        }

        public void setExpire(Duration expire) {
            this.expire = expire;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("expire", expire)
                    .toString();
        }
    }

    public static class AccessLimited implements Serializable {

        /**
         * 单位时间内同一个接口可以访问的次数，默认10次
         */
        private int maxTimes = 10;

        /**
         * 持续时间，即在多长时间内，限制访问多少次。默认为 30秒。
         */
        private Duration expire = Duration.ofSeconds(30);

        public int getMaxTimes() {
            return maxTimes;
        }

        public void setMaxTimes(int maxTimes) {
            this.maxTimes = maxTimes;
        }

        public Duration getExpire() {
            return expire;
        }

        public void setExpire(Duration expire) {
            this.expire = expire;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("maxTimes", maxTimes)
                    .add("expire", expire)
                    .toString();
        }
    }
}
