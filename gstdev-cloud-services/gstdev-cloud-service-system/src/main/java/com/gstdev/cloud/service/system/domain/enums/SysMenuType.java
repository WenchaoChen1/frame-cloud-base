package com.gstdev.cloud.service.system.domain.enums;

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
 * @author gengwei.zheng
 */
@Schema(title = "数据状态")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SysMenuType implements BaseUiEnum<Integer> {

    /**
     * 目录
     */
    CATALOGUE(0,"Catalogue",0, "catalogue"),
    /**
     * 页面
     */
    PAGE(1,"Page",1, "page"),
    /**
     * 功能
     */
    FUNCTION(2, "Function",2, "function");


    private static final Map<Integer, SysMenuType> INDEX_MAP = new HashMap<>();
    private static final List<Map<String, Object>> JSON_STRUCTURE = new ArrayList<>();

    static {
        for (SysMenuType dataItemStatus : SysMenuType.values()) {
            INDEX_MAP.put(dataItemStatus.getValue(), dataItemStatus);
            JSON_STRUCTURE.add(dataItemStatus.getSort(),
                    ImmutableMap.<String, Object>builder()
                            .put("value", dataItemStatus.getValue())
                            .put("name", dataItemStatus.getName())
                            .put("sort", dataItemStatus.getSort())
                            .put("key", dataItemStatus.name())
                            .put("description", dataItemStatus.getDescription())
                            .build());
        }
    }

    @Schema(title = "枚举值")
    private final Integer value;
    @Schema(title = "name")
    private final String name;
    @Schema(title = "顺序")
    private final Integer sort;
    @Schema(title = "文字")
    private final String description;

    SysMenuType(Integer value, String name, Integer sort,String description) {
        this.value = value;
        this.name = name;
        this.sort = sort;
        this.description = description;
    }
    public static SysMenuType get(Integer index) {
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
        return this.description;
    }
    public Integer getSort() {
        return sort;
    }
    public String getName() {
        return name;
    }
}
