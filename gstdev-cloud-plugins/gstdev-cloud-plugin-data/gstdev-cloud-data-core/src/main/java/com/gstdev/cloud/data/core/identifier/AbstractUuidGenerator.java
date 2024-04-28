package com.gstdev.cloud.data.core.identifier;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.factory.spi.StandardGenerator;
import org.hibernate.id.uuid.StandardRandomStrategy;
import org.hibernate.type.descriptor.java.UUIDJavaType;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * <p>Description: 基于 Hibernate 6 的自定义 Uuid 生成器 </p>
 * <p>
 * 主要解决手动设置实体 ID 不生效问题。
 *
 * @author : cc
 * @date : 2022/11/7 17:15
 */
public abstract class AbstractUuidGenerator implements IdentifierGenerator, StandardGenerator {

    private final StandardRandomStrategy generator;
    private final UUIDJavaType.ValueTransformer valueTransformer;

    public AbstractUuidGenerator(Member idMember) {
        generator = StandardRandomStrategy.INSTANCE;

        final Class<?> propertyType;
        if (idMember instanceof Method) {
            propertyType = ((Method) idMember).getReturnType();
        } else {
            propertyType = ((Field) idMember).getType();
        }

        if (UUID.class.isAssignableFrom(propertyType)) {
            valueTransformer = UUIDJavaType.PassThroughTransformer.INSTANCE;
        } else if (String.class.isAssignableFrom(propertyType)) {
            valueTransformer = UUIDJavaType.ToStringTransformer.INSTANCE;
        } else if (byte[].class.isAssignableFrom(propertyType)) {
            valueTransformer = UUIDJavaType.ToBytesTransformer.INSTANCE;
        } else {
            throw new HibernateException("Unanticipated return type [" + propertyType.getName() + "] for UUID conversion");
        }
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return valueTransformer.transform(generator.generateUuid(session));
    }
}
