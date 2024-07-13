package com.gstdev.cloud.service.system.constants;


import com.gstdev.cloud.base.definition.constants.BaseConstants;

/**
 * <p>Description: System 模块常量 </p>
 */
public interface SystemConstants extends BaseConstants {

    String SYSTEM_AREA_PREFIX = AREA_PREFIX + "system:";

    String REGION_SYS_USER = SYSTEM_AREA_PREFIX + "sys:user";
    String REGION_SYS_ROLE = SYSTEM_AREA_PREFIX + "sys:role";
    String REGION_SYS_DEFAULT_ROLE = SYSTEM_AREA_PREFIX + "sys:defaults:role";
    String REGION_SYS_PERMISSION = SYSTEM_AREA_PREFIX + "sys:permission";
    String REGION_SYS_OWNERSHIP = SYSTEM_AREA_PREFIX + "sys:ownership";
    String REGION_SYS_ELEMENT = SYSTEM_AREA_PREFIX + "sys:element";
    String REGION_SYS_SOCIAL_USER = SYSTEM_AREA_PREFIX + "sys:social:user";
    String REGION_SYS_DEPARTMENT = SYSTEM_AREA_PREFIX + "sys:department";
    String REGION_SYS_EMPLOYEE = SYSTEM_AREA_PREFIX + "sys:employee";
    String REGION_SYS_ORGANIZATION = SYSTEM_AREA_PREFIX + "sys:organization";
}
