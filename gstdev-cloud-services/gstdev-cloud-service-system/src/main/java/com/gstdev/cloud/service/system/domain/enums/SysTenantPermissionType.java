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
public enum SysTenantPermissionType implements BaseUiEnum<Integer> {


    /**
     * 正常情况下account permission type 不会为0  为0的话会和account type有一点的业务重跌 增加0是为了让账户没有任何权限默认为零
     */
    ACCOUNT_TYPE(0,"Account Type",0, "1"),
    ACCOUNT_TENANT_MENU(1,"Account Tenant Menu",1, "1"),
    ACCOUNT_TENANT_BUSINESS_PERMISSION(2,"Account Tenant Business Permission",2, "1"),
    ACCOUNT_ROLE_MENU(3,"Account Role Menu",3, "1"),
    ACCOUNT_ROLE_BUSINESS_PERMISSION(4,"Account Role Business Permission",4, "1");


    private static final Map<Integer, SysTenantPermissionType> INDEX_MAP = new HashMap<>();
    private static final List<Map<String, Object>> JSON_STRUCTURE = new ArrayList<>();

    static {
        for (SysTenantPermissionType dataItemStatus : SysTenantPermissionType.values()) {
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

    SysTenantPermissionType(Integer value, String name, Integer sort,String description) {
        this.value = value;
        this.name = name;
        this.sort = sort;
        this.description = description;
    }

    public static SysTenantPermissionType get(Integer index) {
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
