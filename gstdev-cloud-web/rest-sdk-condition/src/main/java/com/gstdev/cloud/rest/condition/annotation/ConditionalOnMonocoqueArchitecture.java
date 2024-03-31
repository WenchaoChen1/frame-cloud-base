package com.gstdev.cloud.rest.condition.annotation;

import com.gstdev.cloud.rest.condition.definition.MonocoqueArchitectureCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 单体架构模式条件注解 </p>
 *
 * @author : cc
 * @date : 2022/1/9 10:42
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(MonocoqueArchitectureCondition.class)
public @interface ConditionalOnMonocoqueArchitecture {
}
