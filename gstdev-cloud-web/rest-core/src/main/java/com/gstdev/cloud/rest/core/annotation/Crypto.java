package com.gstdev.cloud.rest.core.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * <p>Description: 加密解密标记注解 </p>
 *
 * @author : cc
 * @date : 2021/10/4 11:48
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface Crypto {

    /**
     * 请求参数记否解密，默认值 true
     *
     * @return true 请求参数解密；false 请求参数不解密
     */
    boolean requestDecrypt() default true;

    /**
     * 响应体是否加密，默认值 true
     *
     * @return true 响应体加密；false 响应体不加密
     */
    boolean responseEncrypt() default true;
}
