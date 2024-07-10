package com.gstdev.cloud.service.common.autoconfigure.currentLoginInformation;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.service.common.autoconfigure.BaseRedisCurrentLoginInformation;

public interface RedisCurrentLoginInformation extends BaseRedisCurrentLoginInformation<CurrentLoginInformation> {

    Result<Object> addByTokenCurrentLoginInformation( Object currentLoginInformation);

    Result<Object> updateByTokenCurrentLoginInformation( Object currentLogIninformation);

    Result<Object> deleteByTokenCurrentLoginInformation();

    @Override
    CurrentLoginInformation getCurrentLoginInformation();

    @Override
    String getCurrentLoginAccountId();

    @Override
    String getCurrentLoginTenantId();
}
