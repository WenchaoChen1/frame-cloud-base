package com.gstdev.cloud.data.core.constants;


import com.gstdev.cloud.commons.ass.definition.constants.BaseConstants;

/**
 * <p>Description: 数据常量 </p>
 *
 * @author : cc
 * @date : 2022/1/19 18:10
 */
public interface DataConstants extends BaseConstants {

  String ITEM_SPRING_SQL_INIT_PLATFORM = "spring.sql.init.platform";
  String PROPERTY_PREFIX_MULTI_TENANT = PROPERTY_PREFIX_DATA + ".multi-tenant";
  String ITEM_DATA_DATA_SOURCE = PROPERTY_PREFIX_DATA + ".data-source";
  String ITEM_MULTI_TENANT_APPROACH = PROPERTY_PREFIX_MULTI_TENANT + ".approach";

  String ANNOTATION_SQL_INIT_PLATFORM = ANNOTATION_PREFIX + ITEM_SPRING_SQL_INIT_PLATFORM + ANNOTATION_SUFFIX;

  String CORE_AREA_PREFIX = AREA_PREFIX + "core:";
  String REGION_SYS_TENANT_DATASOURCE = CORE_AREA_PREFIX + "sys:tenant:datasource";
}
