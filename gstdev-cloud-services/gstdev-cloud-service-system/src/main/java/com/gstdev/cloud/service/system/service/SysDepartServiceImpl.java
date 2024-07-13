package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.data.core.service.BaseTreeServiceImpl;
import com.gstdev.cloud.service.system.domain.base.depart.DepartDto;
import com.gstdev.cloud.service.system.domain.entity.SysDepart;
import com.gstdev.cloud.service.system.mapper.DepartMapper;
import com.gstdev.cloud.service.system.repository.SysDepartRepository;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class SysDepartServiceImpl extends BaseServiceImpl<SysDepart, String, SysDepartRepository> implements SysDepartService {

    @Resource
    private SysDepartRepository departRepository;
    @Resource
    private DepartMapper departMapper;
    public SysDepartServiceImpl(SysDepartRepository departRepository, DepartMapper departMapper) {
        super(departRepository);
        this.departRepository = departRepository;
        this.departMapper = departMapper;
    }

}
