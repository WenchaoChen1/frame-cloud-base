package com.gstdev.cloud.data.core.entity;

import com.gstdev.cloud.commons.ass.definition.constants.DefaultConstants;
import com.gstdev.cloud.commons.ass.definition.domain.base.AbstractEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/**
 * <p>Description: DISCRIMINATOR 类型多租户实体基础类 </p>
 * <p>
 * Dante Cloud 系统本身如果要改成多租户模式，还是建议使用 DATABASE 模式。
 * 根据需要指定具体是哪些实体（数据表）采用 DISCRIMINATOR 模式多租户。
 * 不要什么实体都加以防产生不必要的干扰。
 *
 * @author : cc
 * @date : 2023/3/29 10:46
 */
@MappedSuperclass
public abstract class BaseTenantEntity extends AbstractEntity {

  @Schema(name = "租户ID", description = "Partitioned 类型租户ID")
  @Column(name = "tenant_id", length = 20)
  private String tenantId = DefaultConstants.TENANT_ID;
}
