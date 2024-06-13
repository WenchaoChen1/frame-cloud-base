package com.gstdev.cloud.service.identity.compliance.annotation;

import com.gstdev.cloud.service.identity.compliance.condition.AutoUnlockUserAccountCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 自动解锁用户账号条件注解 </p>
 *
 * @author : cc
 * @date : 2022/7/9 22:20
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(AutoUnlockUserAccountCondition.class)
public @interface ConditionalOnAutoUnlockUserAccount {
}
