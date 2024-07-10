package com.gstdev.cloud.service.common.autoconfigure;

import com.gstdev.cloud.service.common.autoconfigure.currentLoginInformation.CurrentLoginInformation;

public interface BaseRedisCurrentLoginInformation<T extends CurrentLoginInformation> {

    String getCurrentLoginAccountId();

    String getCurrentLoginTenantId();

    T getCurrentLoginInformation();
}
