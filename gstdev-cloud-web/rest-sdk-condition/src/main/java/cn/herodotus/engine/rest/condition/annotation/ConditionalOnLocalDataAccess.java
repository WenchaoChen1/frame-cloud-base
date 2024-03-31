package cn.herodotus.engine.rest.condition.annotation;

import cn.herodotus.engine.rest.condition.definition.LocalDataAccessCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 本地数据访问策略条件注解 </p>
 *
 * @author : cc
 * @date : 2021/8/6 21:28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(LocalDataAccessCondition.class)
public @interface ConditionalOnLocalDataAccess {
}
