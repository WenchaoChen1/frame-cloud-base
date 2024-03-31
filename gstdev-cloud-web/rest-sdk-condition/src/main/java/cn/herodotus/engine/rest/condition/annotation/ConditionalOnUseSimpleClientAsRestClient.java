package cn.herodotus.engine.rest.condition.annotation;

import cn.herodotus.engine.rest.condition.definition.UseSimpleClientAsRestClientCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 使用 默认 客户端 条件注解 </p>
 *
 * @author : cc
 * @date : 2023/6/15 21:29
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(UseSimpleClientAsRestClientCondition.class)
public @interface ConditionalOnUseSimpleClientAsRestClient {
}
