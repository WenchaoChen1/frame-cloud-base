package com.gstdev.cloud.data.core.annotation;

import com.gstdev.cloud.data.core.condition.MongoDBDataSourceCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 以 MongoDB 作为基础核心应用数据存储条件注解 </p>
 *
 * @author : cc
 * @date : 2022/5/3 23:03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(MongoDBDataSourceCondition.class)
public @interface ConditionalOnMongoDBDataSource {
}
