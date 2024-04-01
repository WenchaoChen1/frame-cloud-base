package com.gstdev.cloud.oauth2.management.generator;

import com.gstdev.cloud.data.core.identifier.AbstractUuidGenerator;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Permission;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.factory.spi.CustomIdGeneratorCreationContext;

import java.lang.reflect.Member;

/**
 * <p>Description: 使得保存实体类时可以在保留主键生成策略的情况下自定义表的主键 </p>
 *
 * @author : cc
 * @date : 2022/3/31 21:11
 */
public class OAuth2PermissionUuidGeneratorType extends AbstractUuidGenerator {

  public OAuth2PermissionUuidGeneratorType(OAuth2PermissionUuidGenerator config, Member idMember, CustomIdGeneratorCreationContext creationContext) {
    super(idMember);
  }

  @Override
  public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
    if (ObjectUtils.isEmpty(object)) {
      throw new HibernateException(new NullPointerException());
    }

    OAuth2Permission permission = (OAuth2Permission) object;

    if (StringUtils.isEmpty(permission.getPermissionId())) {
      return super.generate(session, object);
    } else {
      return permission.getPermissionId();
    }
  }
}

