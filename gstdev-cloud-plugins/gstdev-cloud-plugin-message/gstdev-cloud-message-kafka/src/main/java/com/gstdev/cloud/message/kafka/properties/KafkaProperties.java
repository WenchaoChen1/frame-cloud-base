package com.gstdev.cloud.message.kafka.properties;

import com.gstdev.cloud.base.definition.properties.BaseProperties;
import com.gstdev.cloud.message.core.constants.MessageConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: 消息队列配置 </p>
 *
 * @author : cc
 * @date : 2021/8/7 23:55
 */
@ConfigurationProperties(prefix = MessageConstants.PROPERTY_PREFIX_KAFKA)
public class KafkaProperties extends BaseProperties {

    /**
     * Kakfa监听是否自动启动
     */
    private Boolean enabled = false;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
