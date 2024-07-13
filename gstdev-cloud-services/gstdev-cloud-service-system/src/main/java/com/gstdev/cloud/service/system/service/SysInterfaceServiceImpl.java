package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.message.core.logic.domain.RequestMapping;
import com.gstdev.cloud.service.system.domain.converter.RequestMappingToSysInterfaceConverter;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.entity.SysInterface;
import com.gstdev.cloud.service.system.repository.SysInterfaceRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

public class SysInterfaceServiceImpl extends BaseServiceImpl<SysInterface, String, SysInterfaceRepository> implements SysInterfaceService {

    private final Converter<RequestMapping, SysInterface> toSysInterface;
    @Resource
    private SysInterfaceRepository sysInterfaceRepository;

    public SysInterfaceServiceImpl(SysInterfaceRepository sysInterfaceRepository) {
        super(sysInterfaceRepository);
        this.toSysInterface = new RequestMappingToSysInterfaceConverter();
    }

    @Override
    public SysInterfaceRepository getRepository() {
        return sysInterfaceRepository;
    }

    /**
     * 查找SysSecurityAttribute中不存在的SysAuthority
     *
     * @return SysAuthority列表
     */
    @Override
    public List<SysInterface> findAllocatable() {

        // exist sql 结构示例： SELECT * FROM article WHERE EXISTS (SELECT * FROM user WHERE article.uid = user.uid)
        Specification<SysInterface> specification = (root, criteriaQuery, criteriaBuilder) -> {

            // 构造Not Exist子查询
            Subquery<SysAttribute> subQuery = criteriaQuery.subquery(SysAttribute.class);
            Root<SysAttribute> subRoot = subQuery.from(SysAttribute.class);

            // 构造Not Exist 子查询的where条件
            Predicate subPredicate = criteriaBuilder.equal(subRoot.get("attributeId"), root.get("interfaceId"));
            subQuery.where(subPredicate);

            // 构造完整的子查询语句
            //这句话不加会报错，因为他不知道你子查询要查出什么字段。就是上面示例中的子查询中的“select *”的作用
            subQuery.select(subRoot.get("attributeId"));

            // 构造完整SQL
            // 正确的结构参考：SELECT * FROM sys_authority sa WHERE NOT EXISTS ( SELECT * FROM sys_metadata sm WHERE sm.metadata_id = sa.authority_id )
            criteriaQuery.where(criteriaBuilder.not(criteriaBuilder.exists(subQuery)));
            return criteriaQuery.getRestriction();
        };

        return this.findAll(specification);
    }

    @Override
    @Transactional
    public List<SysInterface> storeRequestMappings(Collection<RequestMapping> requestMappings) {
        List<SysInterface> sysAuthorities = toSysInterfaces(requestMappings);
        Map<String, List<SysInterface>> attributeMap = sysAuthorities.stream()
                .collect(Collectors.groupingBy(SysInterface::getServiceId));
        Set<Map.Entry<String, List<SysInterface>>> entries = attributeMap.entrySet();
        for (Map.Entry<String, List<SysInterface>> entry : entries) {
            String serviceId = entry.getKey();
            getRepository().updateStatusByServiceId(DataItemStatus.EXPIRED, serviceId);
        }
        return saveAllAndFlush(sysAuthorities);
    }

    @Override
    public List<SysInterface> toSysInterfaces(Collection<RequestMapping> requestMappings) {
        if (CollectionUtils.isNotEmpty(requestMappings)) {
            return requestMappings.stream().map(toSysInterface::convert).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> getInterfacesIdStausExpired() {
        return getRepository().findAll().stream().filter(sysInterface ->
                        sysInterface.getStatus() == DataItemStatus.EXPIRED)
                .map(SysInterface::getInterfaceId).collect(Collectors.toList());
    }

}
