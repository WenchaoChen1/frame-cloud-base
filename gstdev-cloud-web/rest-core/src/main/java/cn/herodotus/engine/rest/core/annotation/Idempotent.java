package cn.herodotus.engine.rest.core.annotation;

import java.lang.annotation.*;

/**
 * <p>Description: 幂等标识注解 </p>
 *
 * @author : cc
 * @date : 2021/8/22 14:25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Idempotent {

    /**
     * 过期时间，即幂等签章有效时间。使用Duration格式配置。。
     * <p>
     * 默认为：空，即不设置该属性。那么就使用StampProperies中的配置进行设置。
     * 如果设置了该值，就以该值进行设置。
     *
     * @return {@link Long}
     */
    String expire() default "PT5S";
}
