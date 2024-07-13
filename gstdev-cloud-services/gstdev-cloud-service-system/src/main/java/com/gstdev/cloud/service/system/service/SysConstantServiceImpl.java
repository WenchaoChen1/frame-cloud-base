package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SysConstantServiceImpl implements SysConstantService {

    private static final List<Map<String, Object>> SYS_DATA_ITEM_STATUS = DataItemStatus.getPreprocessedJsonStructure();
//    private static final List<Map<String, Object>> SYS_ACCOUNT_PERMISSION_TYPE = SysAccountPermissionType.getPreprocessedJsonStructure();
    private static final List<Map<String, Object>> SYS_ACCOUNT_TYPE = SysAccountType.getPreprocessedJsonStructure();
    private static final List<Map<String, Object>> SYS_MENU_LOCATION = SysMenuLocation.getPreprocessedJsonStructure();
    private static final List<Map<String, Object>> SYS_MENU_TYPE = SysMenuType.getPreprocessedJsonStructure();
    private static final List<Map<String, Object>> SYS_TENANT_PERMISSION_TYPE = SysTenantPermissionType.getPreprocessedJsonStructure();
    private static final List<Map<String, Object>> SYS_USER_TYPE = SysUserType.getPreprocessedJsonStructure();

    @Override
    public Map<String, Object> getAllEnums() {
        Map<String, Object> map = new HashMap<>(8);
        map.put("sysDataItemStatus", SYS_DATA_ITEM_STATUS);
//        map.put("sysAccountPermissionType", SYS_ACCOUNT_PERMISSION_TYPE);
        map.put("sysAccountType", SYS_ACCOUNT_TYPE);
        map.put("sysMenuLocation", SYS_MENU_LOCATION);
        map.put("sysMenuType", SYS_MENU_TYPE);
        map.put("sysTenantPermissionType", SYS_TENANT_PERMISSION_TYPE);
        map.put("sysUserType", SYS_USER_TYPE);
        return map;
    }
}
