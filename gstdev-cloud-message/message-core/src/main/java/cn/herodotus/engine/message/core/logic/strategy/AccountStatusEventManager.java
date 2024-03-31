package cn.herodotus.engine.message.core.logic.strategy;

import cn.herodotus.engine.message.core.definition.strategy.ApplicationStrategyEventManager;
import cn.herodotus.engine.message.core.logic.domain.UserStatus;

/**
 * <p>Description: 用户状态变更服务 </p>
 *
 * @author : cc
 * @date : 2022/7/10 16:23
 */
public interface AccountStatusEventManager extends ApplicationStrategyEventManager<UserStatus> {

}
