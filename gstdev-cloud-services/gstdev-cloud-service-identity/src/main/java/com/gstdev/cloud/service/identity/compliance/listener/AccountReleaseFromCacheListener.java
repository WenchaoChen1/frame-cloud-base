package com.gstdev.cloud.service.identity.compliance.listener;

import com.gstdev.cloud.message.core.logic.event.AccountReleaseFromCacheEvent;
import com.gstdev.cloud.service.identity.compliance.OAuth2AccountStatusManager;
import org.springframework.context.ApplicationListener;

/**
 * <p>Description: TODO </p>
 *
 * @author : cc
 * @date : 2023/5/14 14:33
 */
public class AccountReleaseFromCacheListener implements ApplicationListener<AccountReleaseFromCacheEvent> {

    private final OAuth2AccountStatusManager accountStatusManager;

    public AccountReleaseFromCacheListener(OAuth2AccountStatusManager accountStatusManager) {
        this.accountStatusManager = accountStatusManager;
    }

    @Override
    public void onApplicationEvent(AccountReleaseFromCacheEvent event) {
        String username = event.getData();
        accountStatusManager.releaseFromCache(username);
    }
}
