package com.gstdev.cloud.service.system.domain.generator;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * <p>Description: SysAttributeUuid </p>
 */
@IdGeneratorType(SysAttributeUuidGeneratorType.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD})
public @interface SysAttributeUuidGenerator {
}
