package com.gstdev.cloud.service.system.bus.processor;


import com.google.common.collect.ImmutableList;
import com.gstdev.cloud.base.core.exception.transaction.TransactionalRollbackException;
import com.gstdev.cloud.message.core.definition.strategy.StrategyEventManager;
import com.gstdev.cloud.oauth2.core.definition.domain.SecurityAttribute;
import com.gstdev.cloud.oauth2.resource.server.processor.SecurityMetadataSourceAnalyzer;
import com.gstdev.cloud.rest.service.scan.RequestMapping;
import com.gstdev.cloud.service.common.autoconfigure.bus.RemoteSecurityMetadataSyncEvent;
import com.gstdev.cloud.service.system.domain.converter.SysAttributeToSecurityAttributeConverter;
import com.gstdev.cloud.service.system.domain.converter.SysInterfacesToSysAttributesConverter;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.entity.SysInterface;
import com.gstdev.cloud.service.system.service.SysAttributeService;
import com.gstdev.cloud.service.system.service.SysInterfaceService;
import com.gstdev.cloud.service.system.service.SysPermissionService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Description: SecurityMetadata数据处理器 </p>
 */
@Component
public class SecurityMetadataDistributeProcessor implements StrategyEventManager<List<SecurityAttribute>>,SecurityMetadataDistributeProcessora {

    private static final Logger log = LoggerFactory.getLogger(SecurityMetadataDistributeProcessor.class);

    private final Converter<List<SysInterface>, List<SysAttribute>> toSysAttributes;
    private final Converter<SysAttribute, SecurityAttribute> toSecurityAttribute;

    private final SysAttributeService sysAttributeService;
    private final SysInterfaceService sysInterfaceService;
    private final SysPermissionService sysPermissionService;
    private final SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer;

    public SecurityMetadataDistributeProcessor(SysAttributeService sysAttributeService, SysInterfaceService sysInterfaceService, SysPermissionService sysPermissionService, SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer) {
        this.sysAttributeService = sysAttributeService;
        this.sysInterfaceService = sysInterfaceService;
        this.sysPermissionService = sysPermissionService;
        this.securityMetadataSourceAnalyzer = securityMetadataSourceAnalyzer;
        this.toSysAttributes = new SysInterfacesToSysAttributesConverter();
        this.toSecurityAttribute = new SysAttributeToSecurityAttributeConverter();
    }

    @Override
    public void postLocalProcess(List<SecurityAttribute> data) {
        securityMetadataSourceAnalyzer.processSecurityAttribute(data);
    }

    @Override
    public void postRemoteProcess(String data, String originService, String destinationService) {
        publishEvent(new RemoteSecurityMetadataSyncEvent(data, originService, destinationService));
    }

    /**
     * 将SysAuthority表中存在，但是SysSecurityAttribute中不存在的数据同步至SysSecurityAttribute，保证两侧数据一致
     */
    @Transactional(rollbackFor = TransactionalRollbackException.class)
    public void postRequestMappings(List<RequestMapping> requestMappings) {
        List<SysInterface> storedInterfaces = sysInterfaceService.storeRequestMappings(requestMappings);
        if (CollectionUtils.isNotEmpty(storedInterfaces)) {
            log.debug("[Gstdev Cloud] |- [5] Request mapping store success, start to merge security metadata!");

            List<SysInterface> sysInterfaces = sysInterfaceService.findAllocatable();
            sysAttributeService.updateAttributeInterFace();
//            if (CollectionUtils.isNotEmpty(sysInterfaces)) {
                List<SysAttribute> elements = toSysAttributes.convert(sysInterfaces);
                List<SysAttribute> result = sysAttributeService.saveAllAndFlush(elements);

                sysPermissionService.permissionInit();
                if (CollectionUtils.isNotEmpty(result)) {
                    log.debug("[Gstdev Cloud] |- Merge security attribute SUCCESS and FINISHED!");
                } else {
                    log.error("[Gstdev Cloud] |- Merge Security attribute failed!, Please Check!");
                }
//            } else {
//                log.debug("[Gstdev Cloud] |- No security attribute requires merge, SKIP!");
//            }

            distributeServiceSecurityAttributes(storedInterfaces);
        }
    }

    private void distributeServiceSecurityAttributes(List<SysInterface> storedInterfaces) {
        storedInterfaces.stream().findAny().ifPresent(item -> {
            String serviceId = item.getServiceId();
            List<SysAttribute> sysAttributes = sysAttributeService.findAllByServiceId(item.getServiceId());
            if (CollectionUtils.isNotEmpty(sysAttributes)) {
                List<SecurityAttribute> securityAttributes = sysAttributes.stream().map(toSecurityAttribute::convert).toList();
                log.debug("[Gstdev Cloud] |- [6] Synchronization permissions to service [{}]", serviceId);
                this.postProcess(serviceId, securityAttributes);
            }
        });
    }

    public void distributeChangedSecurityAttribute(SysAttribute sysAttribute) {
        SecurityAttribute securityAttribute = toSecurityAttribute.convert(sysAttribute);
        postProcess(securityAttribute.getServiceId(), ImmutableList.of(securityAttribute));
    }

    public void distributeChangedSecurityAttribute(List<SysAttribute> sysAttributeList) {
        sysAttributeList.stream().findAny().ifPresent(item -> {
            String serviceId = item.getServiceId();
            List<SysAttribute> sysAttributes = sysAttributeService.findAllByServiceId(item.getServiceId());
            if (CollectionUtils.isNotEmpty(sysAttributes)) {
                List<SecurityAttribute> securityAttributes = sysAttributes.stream().map(toSecurityAttribute::convert).toList();
//                log.debug("[Gstdev Cloud] |- [6] Synchronization permissions to service [{}]", serviceId);
                this.postProcess(serviceId, securityAttributes);
            }
        });
    }
//    public void distributeChangedSecurityAttribute(SysPermission sysPermission) {
//        SysPermission byId = sysPermissionService.findById(sysPermission.getPermissionId());
//        if (CollectionUtils.isNotEmpty(byId.getSysAttributes())) {
//            log.debug("[Gstdev Cloud] |-  Distribute changed security attribute, start to process!");
//            List<SecurityAttribute> securityAttributes = byId.getSysAttributes().stream().map(toSecurityAttribute::convert).toList();
//            Map<String, List<SecurityAttribute>> collect = securityAttributes.stream().collect(Collectors.groupingBy(SecurityAttribute::getServiceId));
//            collect.forEach(this::postProcess);
//        }
//    }

}
