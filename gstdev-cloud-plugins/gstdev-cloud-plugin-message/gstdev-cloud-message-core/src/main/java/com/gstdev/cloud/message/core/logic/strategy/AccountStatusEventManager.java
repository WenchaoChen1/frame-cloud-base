package com.gstdev.cloud.message.core.logic.strategy;

import com.gstdev.cloud.message.core.definition.strategy.ApplicationStrategyEventManager;
import com.gstdev.cloud.message.core.logic.domain.UserStatus;

/**
 * <p>Description: 用户状态变更服务 </p>
 *
 * @author : cc
 * @date : 2022/7/10 16:23
 */
public interface AccountStatusEventManager extends ApplicationStrategyEventManager<UserStatus> {

}
