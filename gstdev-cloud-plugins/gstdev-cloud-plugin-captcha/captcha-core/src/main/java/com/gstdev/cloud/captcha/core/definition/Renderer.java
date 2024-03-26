package com.gstdev.cloud.captcha.core.definition;

import com.gstdev.cloud.captcha.core.definition.domain.Metadata;
import com.gstdev.cloud.captcha.core.dto.Captcha;
import com.gstdev.cloud.captcha.core.dto.Verification;

/**
 * <p>Description: 基础绘制器定义 </p>
 *
 * @author : cc
 * @date : 2024/12/21 15:36
 */
public interface Renderer {

    /**
     * 验证码绘制
     *
     * @return 绘制的验证码和校验信息 {@link Metadata}
     */
    Metadata draw();

    /**
     * 创建验证码
     *
     * @param key 验证码标识，用于标记在缓存中的存储
     * @return 验证码数据 {@link Captcha}
     */
    Captcha getCapcha(String key);

    /**
     * 验证码校验
     *
     * @param verification 前端传入的验证值
     * @return true 验证成功，返回错误信息
     */
    boolean verify(Verification verification);

    /**
     * 获取验证码类别
     *
     * @return 验证码类别
     */
    String getCategory();
}
