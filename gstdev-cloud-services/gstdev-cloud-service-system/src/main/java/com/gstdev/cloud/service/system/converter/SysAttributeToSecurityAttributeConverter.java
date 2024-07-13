package com.gstdev.cloud.service.system.converter;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.oauth2.core.definition.domain.SecurityAttribute;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.entity.SysPermission;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * <p>Description: SysAttribute 转 SecurityAttribute 转换器 </p>
 */
public class SysAttributeToSecurityAttributeConverter implements Converter<SysAttribute, SecurityAttribute> {
    @Override
    public SecurityAttribute convert(SysAttribute sysAttribute) {
        SecurityAttribute securityAttribute = new SecurityAttribute();
        securityAttribute.setAttributeId(sysAttribute.getAttributeId());
        securityAttribute.setAttributeCode(sysAttribute.getAttributeCode());
        securityAttribute.setWebExpression(sysAttribute.getWebExpression());
        securityAttribute.setPermissions(permissionToCommaDelimitedString(sysAttribute.getPermissions()));
        securityAttribute.setUrl(sysAttribute.getUrl());
        securityAttribute.setRequestMethod(sysAttribute.getRequestMethod());
        securityAttribute.setServiceId(sysAttribute.getServiceId());
        securityAttribute.setAttributeName(sysAttribute.getDescription());
        return securityAttribute;


    }

    private String permissionToCommaDelimitedString(Set<SysPermission> sysAuthorities) {
        if (CollectionUtils.isNotEmpty(sysAuthorities)) {
            List<String> codes = sysAuthorities.stream().filter(item -> DataItemStatus.ENABLE.equals(item.getStatus())).map(SysPermission::getPermissionCode).toList();
            return StringUtils.collectionToCommaDelimitedString(codes);
        } else {
            return "";
        }
    }
}
