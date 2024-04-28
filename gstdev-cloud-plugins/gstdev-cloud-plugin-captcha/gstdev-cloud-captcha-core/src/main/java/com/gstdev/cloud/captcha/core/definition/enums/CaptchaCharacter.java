package com.gstdev.cloud.captcha.core.definition.enums;

import com.gstdev.cloud.captcha.core.provider.RandomProvider;

/**
 * <p>Description: 验证码字符类型 </p>
 *
 * @author : cc
 * @date : 2024/12/21 16:26
 */
public enum CaptchaCharacter {

    /**
     * 验证码字母显示类别
     */
    NUM_AND_CHAR(RandomProvider.NUM_MIN_INDEX, RandomProvider.CHAR_MAX_INDEX, "数字和字母混合"),
    ONLY_NUM(RandomProvider.NUM_MIN_INDEX, RandomProvider.NUM_MAX_INDEX, "纯数字"),
    ONLY_CHAR(RandomProvider.CHAR_MIN_INDEX, RandomProvider.CHAR_MAX_INDEX, "纯字母"),
    ONLY_UPPER_CHAR(RandomProvider.UPPER_MIN_INDEX, RandomProvider.UPPER_MAX_INDEX, "纯大写字母"),
    ONLY_LOWER_CHAR(RandomProvider.LOWER_MIN_INDEX, RandomProvider.LOWER_MAX_INDEX, "纯小写字母"),
    NUM_AND_UPPER_CHAR(RandomProvider.NUM_MIN_INDEX, RandomProvider.UPPER_MAX_INDEX, "数字和大写字母");

    /**
     * 字符枚举值开始位置
     */
    private final int start;
    /**
     * 字符枚举值结束位置
     */
    private final int end;
    /**
     * 类型说明
     */
    private final String description;

    CaptchaCharacter(int start, int end, String description) {
        this.start = start;
        this.end = end;
        this.description = description;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getDescription() {
        return description;
    }
}
