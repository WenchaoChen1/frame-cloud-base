package com.gstdev.cloud.rest.condition.annotation;

import com.gstdev.cloud.rest.condition.definition.SMCryptoCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 国密算法条件注解 </p>
 *
 * @author : cc
 * @date : 2022/5/3 23:03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(SMCryptoCondition.class)
public @interface ConditionalOnSMCrypto {
}
