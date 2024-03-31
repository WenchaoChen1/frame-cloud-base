package com.gstdev.cloud.commons.ass.core.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.gstdev.cloud.commons.ass.definition.constants.DefaultConstants;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Description: 存储/获取当前线程的租户信息 </p>
 *
 * @author : cc
 * @date : 2022/9/4 22:34
 */
public class TenantContextHolder {

    private static final ThreadLocal<String> CURRENT_CONTEXT = new TransmittableThreadLocal<>();

    public static String getTenantId() {
        String tenantId = CURRENT_CONTEXT.get();
        if (StringUtils.isBlank(tenantId)) {
            tenantId = DefaultConstants.TENANT_ID;
        }
        return tenantId;
    }

    public static void setTenantId(final String tenantId) {
        if (StringUtils.isBlank(tenantId)) {
            CURRENT_CONTEXT.set(DefaultConstants.TENANT_ID);
        } else {
            CURRENT_CONTEXT.set(tenantId);
        }
    }

    public static void clear() {
        CURRENT_CONTEXT.remove();
    }
}
