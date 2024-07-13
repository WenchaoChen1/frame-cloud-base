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
//@JsonDeserialize(using = DataItemStatusDeserializer.class)
public enum SysAccountType implements BaseUiEnum<Integer> {

    /**
     * 数据条目已启用
     */
//    SUPER(0, "super"),
    /**
     * 数据条目被启用
     */
    ADMIN(1, "Admin",0,"admin"),
    /**
     * 数据条目被锁定
     */
    USER(2, "Account",1,"user");


    private static final Map<Integer, SysAccountType> INDEX_MAP = new HashMap<>();
    private static final List<Map<String, Object>> JSON_STRUCTURE = new ArrayList<>();

    static {
        for (SysAccountType dataItemStatus : SysAccountType.values()) {
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

    SysAccountType(Integer value, String name, Integer sort,String description) {
        this.value = value;
        this.name = name;
        this.sort = sort;
        this.description = description;
    }

    public static SysAccountType get(Integer index) {
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
