package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysBusinessPermission;
import com.gstdev.cloud.service.system.domain.entity.SysTenantMenu;
import com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission.TenantBusinessPermissionTreeDto;
import com.gstdev.cloud.service.system.mapper.SysBusinessPermissionMapper;
import com.gstdev.cloud.service.system.repository.SysBusinessPermissionRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
public class SysBusinessPermissionServiceImpl extends BaseServiceImpl<SysBusinessPermission, String, SysBusinessPermissionRepository> implements SysBusinessPermissionService {

    @Resource
    private SysBusinessPermissionRepository sysBusinessPermissionRepository;
    @Resource
    private SysBusinessPermissionMapper sysBusinessPermissionMapper;
    @Resource
    @Lazy
    private SysRTenantMenuBusinessPermissionService sysRTenantMenuBusinessPermissionService;
    @Resource
    @Lazy
    private SysBusinessPermissionServiceImpl sysBusinessPermissionService;

    public SysBusinessPermissionServiceImpl(SysBusinessPermissionRepository sysBusinessPermissionRepository) {
        super(sysBusinessPermissionRepository);
    }


    @Override
    public Set<String> getAllTenantMenuIdByBusinessPermissionId(String businessPermissionId) {
        SysBusinessPermission byId = sysBusinessPermissionService.findById(businessPermissionId);
        return byId.getSysTenantMenus().stream().map(SysTenantMenu::getTenantMenuId).collect(Collectors.toSet());
    }

    @Override
    public List<TenantBusinessPermissionTreeDto> getTenantBusinessPermissionTree(String tenantId) {
        List<SysBusinessPermission> allByTenantId = sysBusinessPermissionService.findAllByTenantId(tenantId);
        return  sysBusinessPermissionMapper.toTenantBusinessPermissionTreeDtoToTree(allByTenantId);
    }

    public  List<SysBusinessPermission> findAllByTenantId(String tenantId) {
        return getRepository().findByTenantId(tenantId);
    }
}
