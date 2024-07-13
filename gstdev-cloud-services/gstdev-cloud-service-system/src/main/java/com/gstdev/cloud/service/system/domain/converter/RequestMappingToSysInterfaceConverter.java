package com.gstdev.cloud.service.system.domain.converter;

import com.gstdev.cloud.message.core.logic.domain.RequestMapping;
import com.gstdev.cloud.service.system.domain.entity.SysInterface;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: RequestMapping 转 SysInterface 转换器 </p>
 */
public class RequestMappingToSysInterfaceConverter implements Converter<RequestMapping, SysInterface> {

    @Override
    public SysInterface convert(RequestMapping requestMapping) {
        SysInterface sysInterface = new SysInterface();
        sysInterface.setInterfaceId(requestMapping.getMappingId());
        sysInterface.setInterfaceCode(requestMapping.getMappingCode());
        sysInterface.setRequestMethod(requestMapping.getRequestMethod());
        sysInterface.setServiceId(requestMapping.getServiceId());
        sysInterface.setClassName(requestMapping.getClassName());
        sysInterface.setMethodName(requestMapping.getMethodName());
        sysInterface.setUrl(requestMapping.getUrl());
        sysInterface.setDescription(requestMapping.getDescription());
        return sysInterface;
    }
}
