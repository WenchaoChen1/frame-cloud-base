package com.gstdev.cloud.service.system.feign.service;

import com.gstdev.cloud.service.system.feign.vo.IdentitySaveDto;
import com.gstdev.cloud.service.system.feign.vo.UserDto;

public interface IdentityFeignService {

    UserDto save(IdentitySaveDto identitySaveDto);

}
