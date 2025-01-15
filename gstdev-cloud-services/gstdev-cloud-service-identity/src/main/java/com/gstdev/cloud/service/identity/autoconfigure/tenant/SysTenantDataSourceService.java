//package com.gstdev.cloud.oauth2.authorization.server.autoconfigure.tenant;
//
//import com.gstdev.cloud.data.core.repository.BaseRepository;
//import com.gstdev.cloud.data.core.service.BaseService;
//import com.gstdev.cloud.data.tenant.entity.SysTenantDataSource;
//import com.gstdev.cloud.data.tenant.repository.SysTenantDataSourceRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
///**
// * <p>Description: 多租户数据源 </p>
// *
// * @author : cc
// * @date : 2023/3/29 21:20
// */
//@Service
//public class SysTenantDataSourceService extends BaseService<SysTenantDataSource, String> {
//
//    private static final Logger log = LoggerFactory.getLogger(SysTenantDataSourceService.class);
//
//    private final SysTenantDataSourceRepository sysTenantDataSourceRepository;
//
//    public SysTenantDataSourceService(SysTenantDataSourceRepository sysTenantDataSourceRepository) {
//        this.sysTenantDataSourceRepository = sysTenantDataSourceRepository;
//    }
//
//    @Override
//    public BaseRepository<SysTenantDataSource, String> getRepository() {
//        return sysTenantDataSourceRepository;
//    }
//
//    public SysTenantDataSource findByTenantId(String tenantId) {
//        SysTenantDataSource sysRole = sysTenantDataSourceRepository.findByTenantId(tenantId);
//        log.debug("[GstDev Cloud] |- SysTenantDataSource Service findByTenantId.");
//        return sysRole;
//    }
//}
