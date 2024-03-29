package com.gstdev.cloud.captcha.core.definition.enums;

/**
 * <p>Description: 验证码资源 </p>
 *
 * @author : cc
 * @date : 2024/12/11 15:27
 */
public enum CaptchaResource {

    /**
     * 验证码资源类型
     */
    JIGSAW_ORIGINAL("Jigsaw original image", "滑动拼图底图"),
    JIGSAW_TEMPLATE("Jigsaw template image", "滑动拼图滑块底图"),
    WORD_CLICK("Word click image", "文字点选底图");

    private final String content;
    private final String description;

    CaptchaResource(String type, String description) {
        this.content = type;
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }
}
