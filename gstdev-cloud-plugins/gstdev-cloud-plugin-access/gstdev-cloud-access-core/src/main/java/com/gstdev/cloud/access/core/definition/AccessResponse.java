

package com.gstdev.cloud.access.core.definition;


import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;

/**
 * <p>Description: 外部接入预操作统一返回实体 </p>
 *
 * @author : cc
 * @date : 2022/1/25 16:35
 */
public class AccessResponse {

    /**
     * JustAuth 认证URL
     */
    private String authorizeUrl;
    /**
     * 手机短信验证码是否发送成功
     */
    private Boolean success;
    /**
     * 微信小程序返回Session信息
     */
    private WxMaJscode2SessionResult session;

    public String getAuthorizeUrl() {
        return authorizeUrl;
    }

    public void setAuthorizeUrl(String authorizeUrl) {
        this.authorizeUrl = authorizeUrl;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public WxMaJscode2SessionResult getSession() {
        return session;
    }

    public void setSession(WxMaJscode2SessionResult session) {
        this.session = session;
    }
}
