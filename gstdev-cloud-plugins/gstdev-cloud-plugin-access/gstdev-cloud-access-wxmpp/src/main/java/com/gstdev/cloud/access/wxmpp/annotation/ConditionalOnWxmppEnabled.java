

package com.gstdev.cloud.access.wxmpp.annotation;

import com.gstdev.cloud.access.wxmpp.condition.WxmppEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 微信公众号开启条件注解 </p>
 *
 * @author : cc
 * @date : 2022/1/24 14:40
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(WxmppEnabledCondition.class)
public @interface ConditionalOnWxmppEnabled {

}
