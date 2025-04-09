package com.gstdev.cloud.starter.storage.minio.autoconfigure;


import com.gstdev.cloud.plugin.storage.minio.configuration.OssDialectMinioConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({OssDialectMinioConfiguration.class})
public class StorageMinioAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(StorageMinioAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[GstDev Cloud] |- Module [Storage Minio Server Starter] Auto Configure.");
    }
}
