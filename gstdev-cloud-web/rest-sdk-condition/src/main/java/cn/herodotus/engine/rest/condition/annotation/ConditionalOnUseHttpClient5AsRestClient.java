package cn.herodotus.engine.rest.condition.annotation;

import cn.herodotus.engine.rest.condition.definition.UseHttpClient5AsRestClientCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 使用 HttpComponents 客户端作为 RestTemplate 和 OpenFeign 引擎条件注解 </p>
 *
 * @author : cc
 * @date : 2023/6/15 21:29
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(UseHttpClient5AsRestClientCondition.class)
public @interface ConditionalOnUseHttpClient5AsRestClient {
}
