package com.gstdev.cloud.service.common.autoconfigure.constants;


import com.gstdev.cloud.base.definition.constants.BaseConstants;

/**
 * <p>Description: OAuth2 模块通用常量 </p>
 *
 * @author : wenchao.chen
 * @date : 2024/3/25 9:44
 */
public interface ServiceCommonConstants extends BaseConstants {

    String PROPERTY_OAUTH2_AUTHENTICATION = PROPERTY_PREFIX_OAUTH2 + ".authentication";
    String PROPERTY_OAUTH2_AUTHORIZATION = PROPERTY_PREFIX_OAUTH2 + ".authorization";
    String ITEM_COMPLIANCE_AUTO_UNLOCK = PROPERTY_OAUTH2_AUTHENTICATION + ".auto-unlock";

    String REGION_OAUTH2_AUTHORIZATION = AREA_PREFIX + "oauth2:authorization";
    String REGION_OAUTH2_AUTHORIZATION_CONSENT = AREA_PREFIX + "oauth2:authorization:consent";
    String REGION_OAUTH2_REGISTERED_CLIENT = AREA_PREFIX + "oauth2:registered:client";
    String REGION_OAUTH2_APPLICATION = AREA_PREFIX + "oauth2:application";
    String REGION_OAUTH2_COMPLIANCE = AREA_PREFIX + "oauth2:compliance";
    String REGION_OAUTH2_PERMISSION = AREA_PREFIX + "oauth2:permission";
    String REGION_OAUTH2_SCOPE = AREA_PREFIX + "oauth2:scope";
    String REGION_OAUTH2_APPLICATION_SCOPE = AREA_PREFIX + "oauth2:application:scope";
    String REGION_OAUTH2_PRODUCT = AREA_PREFIX + "oauth2:product";
    String REGION_OAUTH2_DEVICE = AREA_PREFIX + "oauth2:device";

    String CACHE_NAME_TOKEN_SIGN_IN_FAILURE_LIMITED = CACHE_TOKEN_BASE_PREFIX + "sign_in:failure_limited:";
    String CACHE_NAME_TOKEN_LOCKED_USER_DETAIL = CACHE_TOKEN_BASE_PREFIX + "locked:user_details:";

    String CACHE_SECURITY_PREFIX = CACHE_PREFIX + "security:";
    String CACHE_SECURITY_METADATA_PREFIX = CACHE_SECURITY_PREFIX + "metadata:";

    String CACHE_NAME_SECURITY_METADATA_ATTRIBUTES = CACHE_SECURITY_METADATA_PREFIX + "attributes:";
    String CACHE_NAME_SECURITY_METADATA_INDEXES = CACHE_SECURITY_METADATA_PREFIX + "indexes:";
    String CACHE_NAME_SECURITY_METADATA_INDEXABLE = CACHE_SECURITY_METADATA_PREFIX + "indexable:";
    String CACHE_NAME_SECURITY_METADATA_COMPATIBLE = CACHE_SECURITY_METADATA_PREFIX + "compatible:";
}
