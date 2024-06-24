package com.gstdev.cloud.captcha.graphic.renderer;

import com.gstdev.cloud.captcha.core.definition.enums.CaptchaCategory;
import com.gstdev.cloud.captcha.graphic.definition.AbstractPngGraphicRenderer;

/**
 * <p>Description: 类型验证码绘制器 </p>
 *
 * @author : cc
 * @date : 2024/12/20 20:39
 */
public class SpecCaptchaRenderer extends AbstractPngGraphicRenderer {

    @Override
    public String getCategory() {
        return CaptchaCategory.SPEC.getConstant();
    }

    @Override
    protected String[] getDrawCharacters() {
        return this.getCharCharacters();
    }
}
