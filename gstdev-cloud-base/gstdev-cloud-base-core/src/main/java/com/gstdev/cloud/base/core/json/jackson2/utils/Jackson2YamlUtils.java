package com.gstdev.cloud.base.core.json.jackson2.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.gstdev.cloud.base.definition.constants.SymbolConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> Description : 基于Jackson Yaml 的 yml处理工具 </p>
 *
 * @author : cc
 * @date : 2020/5/3 8:50
 */
public class Jackson2YamlUtils {

    private static final Logger log = LoggerFactory.getLogger(Jackson2YamlUtils.class);

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static <T> String writeAsString(T entity) {
        return writeAsString(entity, true);
    }

    /**
     * 将实体转化为Yaml形式的字符串
     *
     * @param domain      可序列化的试题
     * @param removeQuote 是否要去除转化后字符串的双引号
     * @param <D>         任意类型
     * @return 字符串形式的Yaml
     */
    public static <D> String writeAsString(D domain, boolean removeQuote) {
        try {
            String yaml = getObjectMapper().writeValueAsString(domain);
            if (StringUtils.isNotBlank(yaml) && removeQuote) {
                return StringUtils.remove(yaml, SymbolConstants.QUOTE);
            } else {
                return yaml;
            }
        } catch (JsonProcessingException e) {
            log.error("[GstDev Cloud] |- Yaml writeAsString processing error! {}", e.getMessage());
        }

        return null;
    }

}
