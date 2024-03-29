package com.gstdev.cloud.captcha.behavior.dto;

import com.gstdev.cloud.captcha.core.dto.Captcha;

/**
 * <p>Description: 滑块拼图验证码返回前端信息 </p>
 *
 * @author : cc
 * @date : 2024/12/13 17:06
 */
public class JigsawCaptcha extends Captcha {

    private String originalImageBase64;

    private String sliderImageBase64;

    public String getOriginalImageBase64() {
        return originalImageBase64;
    }

    public void setOriginalImageBase64(String originalImageBase64) {
        this.originalImageBase64 = originalImageBase64;
    }

    public String getSliderImageBase64() {
        return sliderImageBase64;
    }

    public void setSliderImageBase64(String sliderImageBase64) {
        this.sliderImageBase64 = sliderImageBase64;
    }
}
