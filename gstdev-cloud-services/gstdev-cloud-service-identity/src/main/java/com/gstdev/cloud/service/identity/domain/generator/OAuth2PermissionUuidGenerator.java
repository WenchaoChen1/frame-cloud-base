package com.gstdev.cloud.service.identity.domain.generator;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * <p>Description: OAuth2AuthorityUuid </p>
 *
 * @author : cc
 * @date : 2022/11/7 17:11
 */
@IdGeneratorType(OAuth2PermissionUuidGeneratorType.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD})
public @interface OAuth2PermissionUuidGenerator {
}
