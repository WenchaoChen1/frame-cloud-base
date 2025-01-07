package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysSocialUser;
import com.gstdev.cloud.service.system.mapper.SysSocialUserMapper;
import com.gstdev.cloud.service.system.repository.SysSocialUserRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
public class SysSocialUserServiceImpl extends BaseServiceImpl<SysSocialUser, String, SysSocialUserRepository> implements SysSocialUserService {


    @Resource
    private SysSocialUserRepository sysSocialUserRepository;
    @Resource
    private SysSocialUserMapper socialUserMapper;
    public SysSocialUserServiceImpl(SysSocialUserRepository socialUserRepository, SysSocialUserMapper socialUserMapper) {
        super(socialUserRepository);
        this.socialUserMapper = socialUserMapper;
    }
    @Override
    public SysSocialUser findByUuidAndSource(String uuid, String source) {
        return sysSocialUserRepository.findSysSocialUserByUuidAndSource(uuid, source);
    }
}
