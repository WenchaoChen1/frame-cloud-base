package com.gstdev.cloud.service.common.autoconfigure.currentLoginInformation;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.cache.redis.utils.RedisUtils;
import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
//@DependsOn("redisUtils")
public class RedisCurrentLoginInformationImpl implements RedisCurrentLoginInformation {
    public static final String KET_CURRENT_LOGIN_ACCOUNT_ID = "token::login::account";

    public static final Long TIMEOUT = 10L;

    public static String buildKey(String type, String KEY) {
        return String.format("%s::%s", type, KEY);
    }
    //    @Resource
//    private RedisUtils redisUtils;
    @Override
    public Result<Object> addByTokenCurrentLoginInformation(Object currentLoginInformation) {
        RedisUtils.set(buildKey(KET_CURRENT_LOGIN_ACCOUNT_ID, SecurityUtils.getTokenValue()), currentLoginInformation, TIMEOUT, TimeUnit.HOURS);
        return Result.success();
    }

    @Override
    //add和update一样的功能
    public Result<Object> updateByTokenCurrentLoginInformation(Object currentLoginInformation) {
        RedisUtils.set(buildKey(KET_CURRENT_LOGIN_ACCOUNT_ID, SecurityUtils.getTokenValue()), currentLoginInformation, TIMEOUT, TimeUnit.HOURS);
        return Result.success();
    }

    @Override
    public Result<Object> deleteByTokenCurrentLoginInformation() {
        RedisUtils.del(buildKey(KET_CURRENT_LOGIN_ACCOUNT_ID,SecurityUtils.getTokenValue()));
        return Result.success();
    }

    @Override
    public CurrentLoginInformation getCurrentLoginInformation() {
        CurrentLoginInformation currentLogininformation = null;
        try {
            currentLogininformation = (CurrentLoginInformation) RedisUtils.get(buildKey(KET_CURRENT_LOGIN_ACCOUNT_ID, SecurityUtils.getTokenValue()));
        } catch (Exception e) {
            return null;
        }
        return currentLogininformation;
    }

    @Override
    public String getCurrentLoginAccountId() {
        CurrentLoginInformation loginInformation = getCurrentLoginInformation();
        if (loginInformation == null) {
            return null;
        }
        return loginInformation.getUserId();
    }

    @Override
    public String getCurrentLoginTenantId() {
        CurrentLoginInformation loginInformation = getCurrentLoginInformation();
        if (loginInformation == null) {
            return null;
        }
        return loginInformation.getTenantId();
    }
}
