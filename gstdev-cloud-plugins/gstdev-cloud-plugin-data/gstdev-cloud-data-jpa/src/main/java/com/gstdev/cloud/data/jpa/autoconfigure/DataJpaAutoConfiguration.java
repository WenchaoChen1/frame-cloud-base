package com.gstdev.cloud.data.jpa.autoconfigure;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * <p>Description: Data JPA 模块可配置 </p>
 *
 * @author : cc
 * @date : 2022/9/8 18:12
 */
@AutoConfiguration
@EnableJpaAuditing
public class DataJpaAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DataJpaAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- Module [Data JPA] Auto Configure.");
    }
}
