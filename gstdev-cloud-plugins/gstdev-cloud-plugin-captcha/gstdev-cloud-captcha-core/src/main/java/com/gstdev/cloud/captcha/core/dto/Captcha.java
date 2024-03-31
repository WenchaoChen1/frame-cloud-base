package com.gstdev.cloud.captcha.core.dto;

import com.gstdev.cloud.commons.ass.definition.domain.base.AbstractDto;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 验证码返回数据基础类 </p>
 *
 * @author : cc
 * @date : 2024/12/13 17:06
 */
public abstract class Captcha extends AbstractDto {

    @Schema(title = "验证码身份")
    private String identity;

    @Schema(title = "验证码类别")
    private String category;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
