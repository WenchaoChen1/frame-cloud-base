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
 * <p>Description: 保留模式 </p>
 *
 * @author : cc
 * @date : 2023/6/5 21:45
 */
@Schema(name = "保留模式枚举")
public enum RetentionModeEnums implements BaseUiEnum<Integer> {

    /**
     * 治理模式。用户不能覆盖或删除对象版本或更改其锁定设置。
     * 要覆盖或删除治理模式保留设置，用户必须拥有 `s3:BypassGovernanceRetention` 权限，并且必须明确包括 `x-amz-bypass-governance-retention:true` 作为任何要求覆盖治理模式的请求的请求头。
     */
    GOVERNANCE(0, "治理模式"),
    /**
     * 合规模式。任何用户都不能覆盖或删除受保护对象版本
     */
    COMPLIANCE(1, "合规模式");

    private static final Map<Integer, RetentionModeEnums> INDEX_MAP = new HashMap<>();
    private static final List<Map<String, Object>> JSON_STRUCTURE = new ArrayList<>();

    static {
        for (RetentionModeEnums retentionModeEnums : RetentionModeEnums.values()) {
            INDEX_MAP.put(retentionModeEnums.getValue(), retentionModeEnums);
            JSON_STRUCTURE.add(retentionModeEnums.getValue(),
                    ImmutableMap.<String, Object>builder()
                            .put("value", retentionModeEnums.getValue())
                            .put("key", retentionModeEnums.name())
                            .put("text", retentionModeEnums.getDescription())
                            .put("index", retentionModeEnums.getValue())
                            .build());
        }
    }

    @Schema(title = "枚举值")
    private final Integer value;
    @Schema(name = "文字描述")
    private final String description;

    RetentionModeEnums(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public static RetentionModeEnums get(Integer index) {
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
