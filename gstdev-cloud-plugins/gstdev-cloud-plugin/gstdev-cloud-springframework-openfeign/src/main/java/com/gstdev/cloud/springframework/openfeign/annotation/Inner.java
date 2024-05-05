package com.gstdev.cloud.springframework.openfeign.annotation;

import java.lang.annotation.*;

/**
 * <p>Description: Feign 内部调用标记注解 </p>
 *
 * @author : cc
 * @date : 2022/5/31 12:10
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inner {

}
