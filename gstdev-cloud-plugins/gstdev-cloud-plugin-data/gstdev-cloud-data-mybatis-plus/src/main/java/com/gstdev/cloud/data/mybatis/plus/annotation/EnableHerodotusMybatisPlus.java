package com.gstdev.cloud.data.mybatis.plus.annotation;

import com.gstdev.cloud.data.mybatis.plus.configuration.MybatisPlusConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启 Mybatis Plus 注入 </p>
 *
 * @author : cc
 * @date : 2022/1/19 19:01
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MybatisPlusConfiguration.class)
public @interface EnableHerodotusMybatisPlus {
}
