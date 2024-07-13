package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.converter.SysInterfacesToSysAttributesConverter;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.entity.SysInterface;
import com.gstdev.cloud.service.system.domain.entity.SysPermission;
import com.gstdev.cloud.service.system.domain.pojo.sysAttribute.AttributeManageAssignedPermissionIO;
import com.gstdev.cloud.service.system.repository.SysAttributeRepository;
import com.gstdev.cloud.service.system.repository.SysPermissionRepository;
import jakarta.annotation.Resource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

public class SysAttributeServiceImpl extends BaseServiceImpl<SysAttribute, String, SysAttributeRepository> implements SysAttributeService {
    private final Converter<List<SysInterface>, List<SysAttribute>> toSysAttributes;
    @Resource
    private SysAttributeRepository sysAttributeRepository;
    @Resource
    private SysInterfaceService sysInterfaceService;
    @Resource
    private SysPermissionRepository sysPermissionRepository;

    public SysAttributeServiceImpl(SysAttributeRepository sysAttributeRepository) {
        super(sysAttributeRepository);
        this.toSysAttributes = new SysInterfacesToSysAttributesConverter();
    }

    @Override
    public SysAttributeRepository getRepository() {
        return sysAttributeRepository;
    }


    /**
     * 更新属性接口
     */
    @Override
    @Transactional
    public void updateAttributeInterFace() {
        List<String> interfacesIdStausExpired = sysInterfaceService.getInterfacesIdStausExpired();
        List<SysAttribute> sysAttributes = findAll();
        sysAttributes.forEach(sysAttribute -> {
            if (interfacesIdStausExpired.contains(sysAttribute.getAttributeId())) {
                sysAttribute.setStatus(DataItemStatus.EXPIRED);
            }
        });
        saveAllAndFlush(sysAttributes);
    }

//    @Override
//    @Transactional
//    public void attributeInit() {
//        List<SysInterface> sysInterfaces = sysInterfaceService.findAll();
//        List<SysAttribute> sysAttributes = findAll();
//        List<SysAttribute> sysAttributeList = new ArrayList<>();
//
//        // 使用迭代器避免并发修改异常
//        Iterator<SysAttribute> attributeIterator = sysAttributes.iterator();
//        while (attributeIterator.hasNext()) {
//            SysAttribute sysAttribute = attributeIterator.next();
//            for (SysInterface sysInterface : sysInterfaces) {
//                if (sysInterface.getServiceId().equals(sysAttribute.getServiceId())) {
//                    if (!sysInterface.getStatus().equals(DataItemStatus.ENABLE)) {
//                        sysAttribute.setStatus(DataItemStatus.EXPIRED);
//                    }
//                    sysAttributeList.add(sysAttribute);
//                    attributeIterator.remove(); // 从原集合中删除已处理的元素
//                    break; // 找到匹配的服务ID后退出循环
//                }
//            }
//        }
//
//        // 将剩余的 sysInterfaces 转换为 sysAttributes 并添加到列表中
//        sysAttributeList.addAll(toSysAttributes.convert(sysInterfaces));
//
//        // 保存并刷新所有属性
//        getService().saveAllAndFlush(sysAttributeList);
//    }

    @Override
    public List<SysAttribute> findAllByServiceId(String serviceId) {
        return getRepository().findAllByServiceId(serviceId);
    }


    @Override
    public void updateAttributeManageAssignedPermission(AttributeManageAssignedPermissionIO attributeManageAssignedPermissionIO) {
        SysAttribute sysAttribute = findById(attributeManageAssignedPermissionIO.getAttributeId());
        Set<SysPermission> sysPermissions = new HashSet<>();
        for (String permissionId : attributeManageAssignedPermissionIO.getPermissionIds()) {
            Optional<SysPermission> sysPermission = sysPermissionRepository.findById(permissionId);
            sysPermission.ifPresent(sysPermissions::add);
        }
        sysAttribute.setPermissions(sysPermissions);
        saveAndFlush(sysAttribute);
    }

    @Override
    public Set<String> getAttributePermissionIdByAttributeId(String id) {
        return findById(id).getPermissions().stream().map(SysPermission::getPermissionId).collect(Collectors.toSet());
    }
}
