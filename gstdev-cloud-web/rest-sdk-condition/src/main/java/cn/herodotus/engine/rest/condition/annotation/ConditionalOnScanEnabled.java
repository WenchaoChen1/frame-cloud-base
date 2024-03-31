package cn.herodotus.engine.rest.condition.annotation;

import cn.herodotus.engine.rest.condition.definition.RequestMappingScanCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 分布式架构模式条件注解 </p>
 *
 * @author : cc
 * @date : 2022/1/10 14:54
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(RequestMappingScanCondition.class)
public @interface ConditionalOnScanEnabled {
}
