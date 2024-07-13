package com.gstdev.cloud.service.system.domain.generator;

import com.gstdev.cloud.data.core.identifier.AbstractUuidGenerator;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.factory.spi.CustomIdGeneratorCreationContext;

import java.lang.reflect.Member;

/**
 * <p>Description: 自定义UUID生成器 </p>
 * <p>
 * 使得保存实体类时可以在保留主键生成策略的情况下自定义表的主键
 */
public class SysAttributeUuidGeneratorType extends AbstractUuidGenerator {

    public SysAttributeUuidGeneratorType(SysAttributeUuidGenerator config, Member idMember, CustomIdGeneratorCreationContext creationContext) {
        super(idMember);
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (ObjectUtils.isEmpty(object)) {
            throw new HibernateException(new NullPointerException());
        }

        SysAttribute sysAttribute = (SysAttribute) object;

        if (StringUtils.isEmpty(sysAttribute.getAttributeId())) {
            return super.generate(session, object);
        } else {
            return sysAttribute.getAttributeId();
        }
    }
}
