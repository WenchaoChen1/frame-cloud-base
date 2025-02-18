

package com.gstdev.cloud.access.all.processor;

import com.gstdev.cloud.access.core.definition.AccessHandler;
import com.gstdev.cloud.access.core.definition.AccessResponse;
import com.gstdev.cloud.access.core.definition.AccessUserDetails;
import com.gstdev.cloud.access.core.exception.AccessHandlerNotFoundException;
import com.gstdev.cloud.access.core.exception.IllegalAccessArgumentException;
import com.gstdev.cloud.base.core.enums.AccountType;
import com.gstdev.cloud.base.definition.domain.oauth2.AccessPrincipal;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Description: Access Handler 工厂 </p>
 * <p>
 * 通过该工厂模式，对接入的常规操作进行封装。避免导入引用各个组件，导致耦合性增大
 * <p>
 * 本处使用基于Spring Boot 的工厂模式
 * {@see :https://www.pianshen.com/article/466978086/}
 *
 * @author : cc
 * @date : 2021/4/4 17:40
 */
@Component
public class AccessHandlerStrategyFactory {

    private static final Logger log = LoggerFactory.getLogger(AccessHandlerStrategyFactory.class);

    @Autowired(required = false)
    private final Map<String, AccessHandler> handlers = new ConcurrentHashMap<>();

    public AccessResponse preProcess(String source, String core, String... params) {
        AccessHandler socialAuthenticationHandler = this.getAccessHandler(source);
        return socialAuthenticationHandler.preProcess(core, params);
    }

    public AccessResponse preProcess(AccountType accountType, String core, String... params) {
        AccessHandler socialAuthenticationHandler = this.getAccessHandler(accountType);
        return socialAuthenticationHandler.preProcess(core, params);
    }

    public AccessUserDetails findAccessUserDetails(String source, AccessPrincipal accessPrincipal) {
        AccessHandler socialAuthenticationHandler = this.getAccessHandler(source);
        AccessUserDetails accessUserDetails = socialAuthenticationHandler.loadUserDetails(source, accessPrincipal);

        log.debug("[GstDev Cloud] |- AccessHandlerFactory findAccessUserDetails.");
        return accessUserDetails;
    }

    public AccessHandler getAccessHandler(String source) {
        if (ObjectUtils.isEmpty(source)) {
            throw new IllegalAccessArgumentException("Cannot found SocialProvider");
        }

        AccountType accountType = AccountType.getAccountType(source);
        if (ObjectUtils.isEmpty(accountType)) {
            throw new IllegalAccessArgumentException("Cannot parse the source parameter.");
        }

        return getAccessHandler(accountType);
    }

    public AccessHandler getAccessHandler(AccountType accountType) {
        String handlerName = accountType.getHandler();
        AccessHandler socialAuthenticationHandler = handlers.get(handlerName);
        if (ObjectUtils.isNotEmpty(socialAuthenticationHandler)) {
            return socialAuthenticationHandler;
        } else {
            throw new AccessHandlerNotFoundException("Can not found Social Handler for " + handlerName);
        }
    }
}
