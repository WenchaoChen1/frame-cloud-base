package com.gstdev.cloud.starter.sentinel.alibaba.autoconfigure;

import com.gstdev.cloud.sentinel.alibaba.configuration.AlibabaSentinelConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({AlibabaSentinelConfiguration.class})
public class AlibabaSentinelAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(AlibabaSentinelAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- Module [Starter Alibaba Sentinel] Auto Configuration.");
    }

}
