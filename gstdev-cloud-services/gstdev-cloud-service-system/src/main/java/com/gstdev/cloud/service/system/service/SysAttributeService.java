package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.pojo.sysAttribute.AttributeManageAssignedPermissionIO;

import java.util.List;
import java.util.Set;

public interface SysAttributeService extends BaseService<SysAttribute, String> {
//    void attributeInit();

    void updateAttributeInterFace();
    List<SysAttribute> findAllByServiceId(String serviceId);

    void updateAttributeManageAssignedPermission(AttributeManageAssignedPermissionIO attributeManageAssignedPermissionIO);

    Set<String> getAttributePermissionIdByAttributeId(String id);
}
