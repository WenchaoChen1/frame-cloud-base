package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.entity.SysSocialUser;
/**
 * <p>Description: 社交用户Service </p>
 *
 * @author : cc
 * @date : 2021/5/16 16:25
 */
public interface SysSocialUserService extends BaseService<SysSocialUser, String> {

    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////

    SysSocialUser findByUuidAndSource(String uuid, String source);
}


