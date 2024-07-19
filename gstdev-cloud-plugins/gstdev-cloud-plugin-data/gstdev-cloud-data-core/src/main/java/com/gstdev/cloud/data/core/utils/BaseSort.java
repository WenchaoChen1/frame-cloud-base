package com.gstdev.cloud.data.core.utils;

import com.gstdev.cloud.base.definition.domain.base.AbstractDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 排序参数 </p>
 *
 * @author : cc
 * @date : 2022/7/9 15:04
 */
@Getter
@Setter
@Schema(title = "排序参数BO对象")
public class BaseSort extends AbstractDto {

//    @EnumeratedValue(names = {"ASC", "DESC"}, message = "排序方式的值只能是大写 ASC 或者 DESC")
//    @Schema(title = "排序方向", description = "排序方向的值只能是大写 ASC 或者 DESC, 默认值：DESC", defaultValue = "DESC")
    private List<String> directions=new ArrayList<>();

    @Schema(title = "属性值", description = "指定排序的字段名称")
    private List<String> properties=new ArrayList<>();

    private List<Order> order;

    public Sort getSort() {
        Sort by = Sort.by(getSortOrder());
        return by;
    }

    public List<Sort.Order> getSortOrder() {
        List<Sort.Order> orders = new ArrayList<>();
        if(properties.size()!=directions.size()){
            throw new IllegalArgumentException("The number of sort attributes and sort directions does not agree");
        }

        if(!ObjectUtils.isEmpty(order)){
            for (Order order1 : order) {
                Sort.Order order2 = new Sort.Order(order1.direction, order1.getProperty());
                orders.add(order2);
            }
        }

        if(!ObjectUtils.isEmpty(properties)&&!ObjectUtils.isEmpty(directions)) {
            for (int i = 0; i < properties.size(); i++) {
                orders.add(new Sort.Order(Sort.Direction.fromString(directions.get(i)), properties.get(i)));
            }
        }

        return orders;
    }

    @Setter
    @Getter
    static class Order {
        private Sort.Direction direction;
        private String property;
    }
}
