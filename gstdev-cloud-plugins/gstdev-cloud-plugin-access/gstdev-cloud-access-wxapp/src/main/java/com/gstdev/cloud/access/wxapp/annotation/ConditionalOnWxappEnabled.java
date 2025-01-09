

package com.gstdev.cloud.access.wxapp.annotation;

import com.gstdev.cloud.access.wxapp.condition.WxappEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 微信小程序开启条件注解 </p>
 *
 * @author : cc
 * @date : 2022/1/24 14:40
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(WxappEnabledCondition.class)
public @interface ConditionalOnWxappEnabled {
}
