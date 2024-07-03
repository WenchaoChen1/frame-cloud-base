package com.gstdev.cloud.oauth2.core.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;
import com.gstdev.cloud.base.definition.enums.BaseUiEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 安全表达式 </p>
 */
@Schema(title = "框架登录页面的方式  前端还是后端")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FrameLoginType implements BaseUiEnum<String> {

    //本地java html 使用local,远程 前后端分离项目使用 remote
    LOCAL("local", "local"),
    REMOTE("remote", "remote");

    private static final Map<String, FrameLoginType> INDEX_MAP = new HashMap<>();
    private static final List<Map<String, Object>> JSON_STRUCTURE = new ArrayList<>();

    static {
        for (FrameLoginType permissionExpression : FrameLoginType.values()) {
            INDEX_MAP.put(permissionExpression.getValue(), permissionExpression);
            JSON_STRUCTURE.add(permissionExpression.ordinal(),
                    ImmutableMap.<String, Object>builder()
                            .put("value", permissionExpression.getValue())
                            .put("key", permissionExpression.name())
                            .put("text", permissionExpression.getDescription())
                            .put("index", permissionExpression.ordinal())
                            .build());
        }
    }

    @Schema(title = "索引")
    private final String value;
    @Schema(title = "说明")
    private final String description;

    FrameLoginType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static FrameLoginType get(String value) {
        return INDEX_MAP.get(value);
    }

    public static List<Map<String, Object>> getPreprocessedJsonStructure() {
        return JSON_STRUCTURE;
    }

    @JsonValue
    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
