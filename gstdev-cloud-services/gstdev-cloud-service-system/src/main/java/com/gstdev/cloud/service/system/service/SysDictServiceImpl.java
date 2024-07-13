package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.data.core.service.BaseTreeServiceImpl;
import com.gstdev.cloud.service.system.domain.base.dict.DictDto;
import com.gstdev.cloud.service.system.domain.entity.SysDict;
import com.gstdev.cloud.service.system.mapper.DictMapper;
import com.gstdev.cloud.service.system.repository.SysDictRepository;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public class SysDictServiceImpl extends BaseServiceImpl<SysDict, String, SysDictRepository> implements SysDictService {
    private static final Logger log = LoggerFactory.getLogger(SysDictServiceImpl.class);
    @Resource
    private SysDictRepository dictRepository;
    @Resource
    private DictMapper dictMapper;

    public SysDictServiceImpl(SysDictRepository dictRepository, DictMapper dictMapper) {
        super(dictRepository);
        this.dictRepository = dictRepository;
        this.dictMapper = dictMapper;
    }

    @Override
    public SysDictRepository getRepository() {
        return dictRepository;
    }
}
