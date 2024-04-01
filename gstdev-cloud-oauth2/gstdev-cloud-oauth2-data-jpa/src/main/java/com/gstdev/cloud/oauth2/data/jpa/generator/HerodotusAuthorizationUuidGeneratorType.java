package com.gstdev.cloud.oauth2.data.jpa.generator;

import com.gstdev.cloud.data.core.identifier.AbstractUuidGenerator;
import com.gstdev.cloud.oauth2.data.jpa.entity.HerodotusAuthorization;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.factory.spi.CustomIdGeneratorCreationContext;

import java.lang.reflect.Member;

/**
 * <p>Description: OAuth2Authorization Id 生成器 </p>
 * <p>
 * 指定ID生成器，解决实体ID无法手动设置问题。
 *
 * @author : cc
 * @date : 2022/11/7 15:39
 */
public class HerodotusAuthorizationUuidGeneratorType extends AbstractUuidGenerator {

  public HerodotusAuthorizationUuidGeneratorType(HerodotusAuthorizationUuidGenerator config, Member idMember, CustomIdGeneratorCreationContext creationContext) {
    super(idMember);
  }

  @Override
  public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

    if (ObjectUtils.isEmpty(object)) {
      throw new HibernateException(new NullPointerException());
    }

    HerodotusAuthorization HerodotusAuthorization = (HerodotusAuthorization) object;

    if (StringUtils.isEmpty(HerodotusAuthorization.getId())) {
      return super.generate(session, object);
    } else {
      return HerodotusAuthorization.getId();
    }
  }
}
