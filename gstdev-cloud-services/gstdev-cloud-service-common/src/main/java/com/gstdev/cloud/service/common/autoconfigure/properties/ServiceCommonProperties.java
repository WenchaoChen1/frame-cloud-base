package com.gstdev.cloud.service.common.autoconfigure.properties;

import com.google.common.base.MoreObjects;
import com.gstdev.cloud.oauth2.core.constants.OAuth2Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: frame-cloud-base
 * @description: OAuth2 合规性配置参数
 * @author: wenchao.chen
 * @create: 2024/03/25 13:12
 **/
@Getter
@Setter
@ConfigurationProperties(prefix = OAuth2Constants.PROPERTY_PREFIX_SERVICE_COMMON)
public class ServiceCommonProperties {

    /**
     * 账户踢出限制
     */
    private CommonRedisCache commonRedisCache = new CommonRedisCache();



    @Getter
    @Setter
    public static class CommonRedisCache {
        /**
         * 是否开启 Session 踢出功能，默认开启
         */
        private Boolean enabled = true;
        private String CurrentLoginInformation =  "token::login::account";


        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("enabled", enabled)
                    .toString();
        }
    }



}
