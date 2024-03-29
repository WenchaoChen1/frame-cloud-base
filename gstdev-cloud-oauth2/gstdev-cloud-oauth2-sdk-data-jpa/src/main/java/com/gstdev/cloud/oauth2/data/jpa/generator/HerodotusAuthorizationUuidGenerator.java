package com.gstdev.cloud.oauth2.data.jpa.generator;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * <p>Description: HerodotusAuthorizationUuid 注解 </p>
 *
 * @author : cc
 * @date : 2022/11/7 15:41
 */
@IdGeneratorType(HerodotusAuthorizationUuidGeneratorType.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD})
public @interface HerodotusAuthorizationUuidGenerator {
}
