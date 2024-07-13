package com.gstdev.cloud.service.system.converter;

import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.entity.SysInterface;
import com.gstdev.cloud.service.system.domain.entity.SysPermission;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>Description: List<SysInterface> 转 List<SysAttribute> 转换器 </p>
 */
public class SysInterfacesToSysAttributesConverter implements Converter<List<SysInterface>, List<SysAttribute>> {
    @Override
    public List<SysAttribute> convert(List<SysInterface> sysInterfaces) {
        if (CollectionUtils.isNotEmpty(sysInterfaces)) {
            return sysInterfaces.stream().map(this::convert).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    // TODO
    private SysAttribute convert(SysInterface sysInterface) {
        SysAttribute sysAttribute = new SysAttribute();
        sysAttribute.setAttributeId(sysInterface.getInterfaceId());
        sysAttribute.setAttributeCode(sysInterface.getInterfaceCode());
        sysAttribute.setRequestMethod(sysInterface.getRequestMethod());
        sysAttribute.setServiceId(sysInterface.getServiceId());
        sysAttribute.setClassName(sysInterface.getClassName());
        sysAttribute.setMethodName(sysInterface.getMethodName());
        sysAttribute.setUrl(sysInterface.getUrl());

//        sysAttribute.setStatus(sysInterface.getStatus());
//        sysAttribute.setReserved(sysInterface.getReserved());
        sysAttribute.setDescription(sysInterface.getDescription());
//        sysAttribute.setReversion(sysInterface.getReversion());
//        sysAttribute.setCreateTime(sysInterface.getCreateTime());
//        sysAttribute.setUpdateTime(sysInterface.getUpdateTime());
//        sysAttribute.setRanking(sysInterface.getRanking());

        Set<SysPermission> sysPermissions = new HashSet<>();
//        SysPermission attributePermission = new SysPermission();
//        attributePermission.setPermissionId(sysAttribute.getAttributeId());
//        attributePermission.setPermissionCode(sysAttribute.getServiceId()+":"+sysAttribute.getAttributeCode());
//        attributePermission.setPermissionName(sysAttribute.getMethodName());
//        attributePermission.setPermissionType("attribute");
//        SysPermission servicePermission = new SysPermission();
//        servicePermission.setPermissionId(sysAttribute.getServiceId());
//        servicePermission.setPermissionCode(sysAttribute.getServiceId());
//        servicePermission.setPermissionName(sysAttribute.getServiceId());
//        servicePermission.setPermissionType("service");

//        sysPermissions.add(attributePermission);
//        sysPermissions.add(servicePermission);
//        SysPermission allPermission = new SysPermission();
//        allPermission.setPermissionId("6b492c39-f367-4e66-821c-f4dffacfaeff");
//        allPermission.setPermissionCode("all");
//        allPermission.setPermissionName("all");
//        allPermission.setPermissionType("all");
//        sysPermissions.add(allPermission);
        sysAttribute.setPermissions(sysPermissions);
        return sysAttribute;
    }

}
