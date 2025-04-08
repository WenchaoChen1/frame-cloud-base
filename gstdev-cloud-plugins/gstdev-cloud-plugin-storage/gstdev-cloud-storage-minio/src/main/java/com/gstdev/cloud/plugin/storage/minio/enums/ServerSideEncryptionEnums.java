// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;
import com.gstdev.cloud.base.definition.enums.BaseUiEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 服务端加密方法 </p>
 *
 * @author : cc
 * @date : 2023/6/9 12:36
 */
@Schema(name = "服务端加密方法")
public enum ServerSideEncryptionEnums implements BaseUiEnum<Integer> {

    /**
     * enum
     */
    AWS_KMS(1, "SSE_KMS"),
    AES256(2, "SSE_S3"),
    CUSTOM(3, "自定义");

    private static final Map<Integer, ServerSideEncryptionEnums> INDEX_MAP = new HashMap<>();
    private static final List<Map<String, Object>> JSON_STRUCTURE = new ArrayList<>();

    static {
        for (ServerSideEncryptionEnums sseConfiguration : ServerSideEncryptionEnums.values()) {
            INDEX_MAP.put(sseConfiguration.getValue(), sseConfiguration);
            JSON_STRUCTURE.add(sseConfiguration.getValue(),
                    ImmutableMap.<String, Object>builder()
                            .put("value", sseConfiguration.getValue())
                            .put("key", sseConfiguration.name())
                            .put("text", sseConfiguration.getDescription())
                            .put("index", sseConfiguration.getValue())
                            .build());
        }
    }

    @Schema(title = "枚举值")
    private final Integer value;
    @Schema(name = "文字")
    private final String description;

    ServerSideEncryptionEnums(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public static ServerSideEncryptionEnums get(Integer index) {
        return INDEX_MAP.get(index);
    }

    public static List<Map<String, Object>> getPreprocessedJsonStructure() {
        return JSON_STRUCTURE;
    }

    /**
     * 不加@JsonValue，转换的时候转换出完整的对象。
     * 加了@JsonValue，只会显示相应的属性的值
     * <p>
     * 不使用@JsonValue @JsonDeserializer类里面要做相应的处理
     *
     * @return Enum枚举值
     */
    @JsonValue
    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
