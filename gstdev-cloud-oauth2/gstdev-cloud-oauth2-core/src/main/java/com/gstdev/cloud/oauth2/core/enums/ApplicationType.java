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
 * <p>Description: 应用类别 </p>
 *
 * @author : cc
 * @date : 2020/5/4 12:01
 */
@Schema(title = "应用类型")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ApplicationType implements BaseUiEnum<Integer> {

    /**
     * 应用类型
     */
    WEB(0, "PC网页应用"),
    SERVICE(1, "服务应用"),
    APP(2, "手机APP应用"),
    WAP(3, "手机网页应用"),
    MINI(4, "小程序应用"),
    IOT(5, "物联网应用");

    private static final Map<Integer, ApplicationType> INDEX_MAP = new HashMap<>();
    private static final List<Map<String, Object>> JSON_STRUCT = new ArrayList<>();

    static {
        for (ApplicationType applicationType : ApplicationType.values()) {
            INDEX_MAP.put(applicationType.getValue(), applicationType);
            JSON_STRUCT.add(applicationType.getValue(),
                ImmutableMap.<String, Object>builder()
                    .put("value", applicationType.getValue())
                    .put("key", applicationType.name())
                    .put("text", applicationType.getDescription())
                    .put("index", applicationType.getValue())
                    .build());
        }
    }

    @Schema(title = "枚举值")
    private final Integer value;
    @Schema(title = "文字")
    private final String description;

    ApplicationType(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public static ApplicationType get(Integer index) {
        return INDEX_MAP.get(index);
    }

    public static List<Map<String, Object>> getPreprocessedJsonStructure() {
        return JSON_STRUCT;
    }

    @Override
    public String getDescription() {
        return description;
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
}
