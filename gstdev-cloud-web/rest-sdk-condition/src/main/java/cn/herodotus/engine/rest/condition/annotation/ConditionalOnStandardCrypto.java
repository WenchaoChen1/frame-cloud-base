package cn.herodotus.engine.rest.condition.annotation;

import cn.herodotus.engine.rest.condition.definition.StandardCryptoCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 标准加密算法 </p>
 *
 * @author : cc
 * @date : 2022/5/3 23:04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(StandardCryptoCondition.class)
public @interface ConditionalOnStandardCrypto {
}
