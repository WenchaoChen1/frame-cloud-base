package cn.herodotus.engine.rest.core.definition;

import com.gstdev.cloud.commons.ass.core.utils.http.SessionUtils;
import com.gstdev.cloud.commons.ass.definition.constants.SymbolConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.crypto.SecureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * <p>Description: 基础拦截器 </p>
 * <p>
 * 定义拦截器通用方法
 *
 * @author : cc
 * @date : 2022/10/18 21:40
 */
public abstract class AbstractBaseHandlerInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AbstractBaseHandlerInterceptor.class);

    protected String generateRequestKey(HttpServletRequest request) {

        String sessionId = SessionUtils.analyseSessionId(request);

        String url = request.getRequestURI();
        String method = request.getMethod();

        if (StringUtils.isNotBlank(sessionId)) {
            String key = SecureUtil.md5(sessionId + SymbolConstants.COLON + url + SymbolConstants.COLON + method);
            log.debug("[GstDev Cloud] |- IdempotentInterceptor key is [{}].", key);
            return key;
        } else {
            log.warn("[GstDev Cloud] |- IdempotentInterceptor cannot create key, because sessionId is null.");
            return null;
        }
    }
}
