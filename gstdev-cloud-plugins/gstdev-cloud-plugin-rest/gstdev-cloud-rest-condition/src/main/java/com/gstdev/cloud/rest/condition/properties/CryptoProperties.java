package com.gstdev.cloud.rest.condition.properties;

import com.google.common.base.MoreObjects;
import com.gstdev.cloud.rest.condition.constants.RestConstants;
import com.gstdev.cloud.rest.core.enums.CryptoStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: 加密算法配置 </p>
 *
 * @author : cc
 * @date : 2022/5/1 21:13
 */
@ConfigurationProperties(prefix = RestConstants.PROPERTY_PREFIX_CRYPTO)
public class CryptoProperties {

    /**
     * 加密算法策略，默认：国密算法
     */
    private CryptoStrategy cryptoStrategy = CryptoStrategy.SM;

    public CryptoStrategy getCryptoStrategy() {
        return cryptoStrategy;
    }

    public void setCryptoStrategy(CryptoStrategy cryptoStrategy) {
        this.cryptoStrategy = cryptoStrategy;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("strategy", cryptoStrategy)
                .toString();
    }
}
