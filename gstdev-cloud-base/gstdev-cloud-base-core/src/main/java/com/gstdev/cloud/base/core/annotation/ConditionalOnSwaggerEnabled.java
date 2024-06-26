package com.gstdev.cloud.base.core.annotation;

//import com.gstdev.cloud.commons.ass.core.conditon.SwaggerEnabledCondition;

import java.lang.annotation.*;

/**
 * <p>Description: Swagger条件开启主机 </p>
 *
 * @author : cc
 * @date : 2021/8/20 11:58
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
//@Conditional(SwaggerEnabledCondition.class)
public @interface ConditionalOnSwaggerEnabled {
}
