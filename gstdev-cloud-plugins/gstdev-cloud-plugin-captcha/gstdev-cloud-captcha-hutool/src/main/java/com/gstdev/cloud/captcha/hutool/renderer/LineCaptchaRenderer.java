package com.gstdev.cloud.captcha.hutool.renderer;

import com.gstdev.cloud.captcha.core.definition.AbstractGraphicRenderer;
import com.gstdev.cloud.captcha.core.definition.domain.Metadata;
import com.gstdev.cloud.captcha.core.definition.enums.CaptchaCategory;
import org.dromara.hutool.swing.captcha.CaptchaUtil;
import org.dromara.hutool.swing.captcha.LineCaptcha;

/**
 * <p>Description: Hutool Line Captcha </p>
 *
 * @author : cc
 * @date : 2024/12/23 22:44
 */
public class LineCaptchaRenderer extends AbstractGraphicRenderer {

    @Override
    public Metadata draw() {
        LineCaptcha lineCaptcha = CaptchaUtil.ofLineCaptcha(this.getWidth(), this.getHeight(), this.getLength(), 150);
        lineCaptcha.setFont(this.getFont());

        Metadata metadata = new Metadata();
        metadata.setGraphicImageBase64(lineCaptcha.getImageBase64Data());
        metadata.setCharacters(lineCaptcha.getCode());
        return metadata;
    }

    @Override
    public String getCategory() {
        return CaptchaCategory.HUTOOL_LINE.getConstant();
    }
}
