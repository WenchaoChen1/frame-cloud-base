package cn.herodotus.engine.rest.core.annotation;

import cn.herodotus.engine.rest.core.validation.EnumeratedValueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


/**
 * <p>Description: 枚举值校验注解 </p>
 *
 * @author : cc
 * @date : 2022/6/13 15:58
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(EnumeratedValue.List.class)
@Documented
@Constraint(validatedBy = {EnumeratedValueValidator.class})
public @interface EnumeratedValue {

    // 默认错误消息
    String message() default "必须为指定值";

    String[] names() default {};

    int[] ordinals() default {};

    // 分组
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};

    // 指定多个时使用
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        EnumeratedValue[] value();
    }
}
