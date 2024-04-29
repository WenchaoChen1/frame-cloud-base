package com.gstdev.cloud.data.core.utils;

import com.gstdev.cloud.base.definition.domain.base.AbstractDto;
import com.gstdev.cloud.data.core.annotations.EnumeratedValue;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 排序参数 </p>
 *
 * @author : cc
 * @date : 2022/7/9 15:04
 */
@Schema(title = "排序参数BO对象")
public class BaseSort extends AbstractDto {

    @EnumeratedValue(names = {"ASC", "DESC"}, message = "排序方式的值只能是大写 ASC 或者 DESC")
    @Schema(title = "排序方向", description = "排序方向的值只能是大写 ASC 或者 DESC, 默认值：DESC", defaultValue = "DESC")
    private String direction = "DESC";

    @Schema(title = "属性值", description = "指定排序的字段名称")
    private String[] properties;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String[] getProperties() {
        return properties;
    }

    public void setProperties(String[] properties) {
        this.properties = properties;
    }
}
