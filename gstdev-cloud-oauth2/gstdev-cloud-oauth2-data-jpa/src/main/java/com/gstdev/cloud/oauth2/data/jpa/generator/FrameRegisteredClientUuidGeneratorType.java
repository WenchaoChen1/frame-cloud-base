package com.gstdev.cloud.oauth2.data.jpa.generator;

import com.gstdev.cloud.data.core.identifier.AbstractUuidGenerator;
import com.gstdev.cloud.oauth2.data.jpa.entity.FrameRegisteredClient;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.factory.spi.CustomIdGeneratorCreationContext;

import java.lang.reflect.Member;

/**
 * <p>Description: OAuth2RegisteredClient Id 生成器 </p>
 * <p>
 * 指定ID生成器，解决实体ID无法手动设置问题。
 *
 * @author : cc
 * @date : 2022/1/22 17:50
 */
public class FrameRegisteredClientUuidGeneratorType extends AbstractUuidGenerator {

    public FrameRegisteredClientUuidGeneratorType(FrameRegisteredClientUuidGenerator config, Member idMember, CustomIdGeneratorCreationContext creationContext) {
        super(idMember);
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (ObjectUtils.isEmpty(object)) {
            throw new HibernateException(new NullPointerException());
        }

        FrameRegisteredClient frameRegisteredClient = (FrameRegisteredClient) object;

        if (StringUtils.isEmpty(frameRegisteredClient.getId())) {
            return super.generate(session, object);
        } else {
            return frameRegisteredClient.getId();
        }
    }
}
