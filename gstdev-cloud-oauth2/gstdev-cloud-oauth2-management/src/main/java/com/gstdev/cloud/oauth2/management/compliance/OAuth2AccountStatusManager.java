package com.gstdev.cloud.oauth2.management.compliance;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.message.core.logic.domain.UserStatus;
import com.gstdev.cloud.message.core.logic.strategy.AccountStatusEventManager;
import com.gstdev.cloud.oauth2.authorization.server.stamp.LockedUserDetailsStampManager;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.oauth2.core.definition.service.EnhanceUserDetailsService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>Description: 账户锁定处理服务 </p>
 *
 * @author : cc
 * @date : 2022/7/8 19:25
 */
//@Service
public class OAuth2AccountStatusManager {

    private static final Logger log = LoggerFactory.getLogger(OAuth2AccountStatusManager.class);

    private final UserDetailsService userDetailsService;
    private final AccountStatusEventManager accountStatusEventManager;
    private final LockedUserDetailsStampManager lockedUserDetailsStampManager;

    public OAuth2AccountStatusManager(UserDetailsService userDetailsService, AccountStatusEventManager accountStatusEventManager, LockedUserDetailsStampManager lockedUserDetailsStampManager) {
        this.userDetailsService = userDetailsService;
        this.lockedUserDetailsStampManager = lockedUserDetailsStampManager;
        this.accountStatusEventManager = accountStatusEventManager;
    }

    private EnhanceUserDetailsService getUserDetailsService() {
        return (EnhanceUserDetailsService) userDetailsService;
    }

    private String getUserId(String username) {
        EnhanceUserDetailsService enhanceUserDetailsService = getUserDetailsService();
        DefaultSecurityUser user = enhanceUserDetailsService.loadDefaultSecurityUserByUsername(username);
        if (ObjectUtils.isNotEmpty(user)) {
            return user.getUserId();
        }

        log.warn("[GstDev Cloud] |- Can not found the userid for [{}]", username);
        return null;
    }

    public void lock(String username) {
        String userId = getUserId(username);
        if (ObjectUtils.isNotEmpty(userId)) {
            accountStatusEventManager.postProcess(new UserStatus(userId, DataItemStatus.LOCKING.name()));
            lockedUserDetailsStampManager.put(userId, username);
            log.info("[GstDev Cloud] |- User count [{}] has been locked, and record into cache!", username);
        }
    }

    public void enable(String userId) {
        if (ObjectUtils.isNotEmpty(userId)) {
            accountStatusEventManager.postProcess(new UserStatus(userId, DataItemStatus.ENABLE.name()));
        }
    }

    public void releaseFromCache(String username) {
        String userId = getUserId(username);
        if (ObjectUtils.isNotEmpty(userId)) {
            String value = lockedUserDetailsStampManager.get(userId);
            if (StringUtils.isNotEmpty(value)) {
                this.lockedUserDetailsStampManager.delete(userId);
                log.info("[GstDev Cloud] |- User count [{}] locked info has been release!", username);
            }
        }
    }
}
