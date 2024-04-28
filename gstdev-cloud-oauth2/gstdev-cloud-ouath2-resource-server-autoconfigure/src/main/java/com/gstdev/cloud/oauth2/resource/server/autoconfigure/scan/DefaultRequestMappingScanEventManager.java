package com.gstdev.cloud.oauth2.resource.server.autoconfigure.scan;

import com.gstdev.cloud.base.core.context.ServiceContextHolder;
import com.gstdev.cloud.message.core.logic.domain.RequestMapping;
import com.gstdev.cloud.message.core.logic.event.RequestMappingGatherEvent;
import com.gstdev.cloud.message.core.logic.strategy.RequestMappingScanEventManager;
import com.gstdev.cloud.oauth2.resource.server.autoconfigure.bus.RemoteRequestMappingGatherEvent;
import com.gstdev.cloud.oauth2.resource.server.processor.SecurityMetadataSourceAnalyzer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * <p>Description: 自定义 RequestMappingScanManager</p>
 *
 * @author : cc
 * @date : 2022/1/17 0:08
 */
//@Component
public class DefaultRequestMappingScanEventManager implements RequestMappingScanEventManager {

    private final SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer;

    public DefaultRequestMappingScanEventManager(SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer) {
        this.securityMetadataSourceAnalyzer = securityMetadataSourceAnalyzer;
    }

    @Override
    public Class<? extends Annotation> getScanAnnotationClass() {
        return EnableWebSecurity.class;
    }

    @Override
    public String getDestinationServiceName() {
        return ServiceContextHolder.getInstance().getSystemServiceName();
    }

    @Override
    public void postLocalStorage(List<RequestMapping> requestMappings) {
        securityMetadataSourceAnalyzer.processRequestMatchers();
    }

    @Override
    public void postLocalProcess(List<RequestMapping> data) {
        publishEvent(new RequestMappingGatherEvent(data));
    }

    @Override
    public void postRemoteProcess(String data, String originService, String destinationService) {
        publishEvent(new RemoteRequestMappingGatherEvent(data, originService, destinationService));
    }
}
