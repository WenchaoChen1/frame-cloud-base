package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.service.system.domain.entity.SysSocialUser;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.QueryHints;
/**
 * <p>Description: 社交用户Repository </p>
 *
 * @author : cc
 * @date : 2021/5/16 16:25
 */
public interface SysSocialUserRepository extends BaseRepository<SysSocialUser, String> {
    /**
     * 通过 uuid 和 source查询用户
     *
     * @param uuid   JustAuth返回信息中uuid，具体信息查询JustAuth {@see :https://justauth.wiki/quickstart/explain.html}
     * @param source 第三方登录类型的字符串
     * @return SysSocialUser
     */
    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    SysSocialUser findSysSocialUserByUuidAndSource(String uuid, String source);;
}
