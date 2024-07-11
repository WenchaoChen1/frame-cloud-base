package com.gstdev.cloud.rest.condition.properties;

import com.gstdev.cloud.base.core.enums.Architecture;
import com.gstdev.cloud.base.core.enums.Protocol;
import com.gstdev.cloud.base.core.enums.Target;
import com.gstdev.cloud.base.definition.properties.BaseProperties;
import com.gstdev.cloud.rest.condition.constants.RestConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: 平台服务相关配置 </p>
 *
 * @author : cc
 * @date : 2019/11/17 15:22
 */
@ConfigurationProperties(prefix = RestConstants.PROPERTY_PREFIX_PLATFORM)
public class PlatformProperties extends BaseProperties {

    /**
     * 平台架构类型，默认：DISTRIBUTED（分布式架构）
     */
    private Architecture architecture = Architecture.DISTRIBUTED;
    /**
     * 数据访问策略，默认：远程
     */
    private Target dataAccessStrategy = Target.REMOTE;

    /**
     * 接口地址默认采用的Http协议类型
     */
    private Protocol protocol = Protocol.HTTP;

    public Architecture getArchitecture() {
        return architecture;
    }

    public void setArchitecture(Architecture architecture) {
        this.architecture = architecture;
    }

    public Target getDataAccessStrategy() {
        return dataAccessStrategy;
    }

    public void setDataAccessStrategy(Target dataAccessStrategy) {
        this.dataAccessStrategy = dataAccessStrategy;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }
}
