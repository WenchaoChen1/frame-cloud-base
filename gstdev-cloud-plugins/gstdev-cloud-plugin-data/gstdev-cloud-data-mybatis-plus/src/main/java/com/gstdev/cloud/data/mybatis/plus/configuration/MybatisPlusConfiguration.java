package com.gstdev.cloud.data.mybatis.plus.configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.gstdev.cloud.data.core.constants.DataConstants;
import com.gstdev.cloud.data.mybatis.plus.enhance.FrameIdentifierGenerator;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: Mybatis Plus 配置</p>
 *
 * @author : cc
 * @date : 2021/8/28 11:48
 */
@Configuration(proxyBeanMethods = false)
public class MybatisPlusConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MybatisPlusConfiguration.class);

    @Value(DataConstants.ANNOTATION_SQL_INIT_PLATFORM)
    private String platform;

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- SDK [Data Mybatis Plus] Auto Configure.");
    }

    private DbType parseDbType() {
        if (StringUtils.isNotBlank(platform)) {
            DbType type = DbType.getDbType(platform);
            if (ObjectUtils.isNotEmpty(type)) {
                return type;
            }
        }

        return DbType.POSTGRE_SQL;
    }

    /**
     * 防止 修改与删除时对全表进行操作
     *
     * @return {@link MybatisPlusInterceptor}
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(parseDbType()));
        log.trace("[GstDev Cloud] |- Bean [Mybatis Plus Interceptor] Auto Configure.");
        return mybatisPlusInterceptor;
    }

    @Bean
    public BlockAttackInnerInterceptor blockAttackInnerInterceptor() {
        BlockAttackInnerInterceptor blockAttackInnerInterceptor = new BlockAttackInnerInterceptor();
        log.trace("[GstDev Cloud] |- Bean [Block Attack Inner Interceptor] Auto Configure.");
        return blockAttackInnerInterceptor;
    }

    @Bean
    public IdentifierGenerator identifierGenerator() {
        FrameIdentifierGenerator frameIdentifierGenerator = new FrameIdentifierGenerator();
        log.trace("[GstDev Cloud] |- Bean [GstDev Cloud Identifier Generator] Auto Configure.");
        return frameIdentifierGenerator;
    }
}
