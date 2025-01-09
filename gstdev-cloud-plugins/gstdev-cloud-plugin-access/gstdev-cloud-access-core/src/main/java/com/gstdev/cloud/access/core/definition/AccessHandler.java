

package com.gstdev.cloud.access.core.definition;


import com.gstdev.cloud.base.definition.domain.oauth2.AccessPrincipal;

/**
 * <p>Description: 外部应用接入处理器 </p>
 *
 * @author : cc
 * @date : 2022/1/25 16:20
 */
public interface AccessHandler {

    /**
     * 外部应用接入预处理
     * 比如 微信小程序需要传入Code 和 AppId
     * 比如 手机登录需要传入手机号码等
     *
     * @param core   对于只需要一个参数就可以进行预处理操作的核心值。
     * @param params 核心值以外的其它参数
     * @return {@link  AccessResponse}
     */
    AccessResponse preProcess(String core, String... params);

    /**
     * 获取接入系统中的用户信息，并转换为系统可以识别的 {@link AccessUserDetails} 类型
     *
     * @param source          类别
     * @param accessPrincipal 外部系统接入所需信息
     * @return 外部系统用户信息 {@link AccessUserDetails}
     */
    AccessUserDetails loadUserDetails(String source, AccessPrincipal accessPrincipal);
}
