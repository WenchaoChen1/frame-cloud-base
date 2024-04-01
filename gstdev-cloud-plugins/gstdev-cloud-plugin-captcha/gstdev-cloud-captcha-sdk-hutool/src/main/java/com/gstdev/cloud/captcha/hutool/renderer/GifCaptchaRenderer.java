package com.gstdev.cloud.captcha.hutool.renderer;

import com.gstdev.cloud.captcha.core.definition.AbstractGraphicRenderer;
import com.gstdev.cloud.captcha.core.definition.domain.Metadata;
import com.gstdev.cloud.captcha.core.definition.enums.CaptchaCategory;
import org.dromara.hutool.swing.captcha.CaptchaUtil;
import org.dromara.hutool.swing.captcha.GifCaptcha;

/**
 * <p>Description: Hutool GIF验证码 </p>
 *
 * @author : cc
 * @date : 2024/12/23 23:08
 */
public class GifCaptchaRenderer extends AbstractGraphicRenderer {

  @Override
  public Metadata draw() {
    GifCaptcha gifCaptcha = CaptchaUtil.ofGifCaptcha(this.getWidth(), this.getHeight(), this.getLength());
    gifCaptcha.setFont(this.getFont());

    Metadata metadata = new Metadata();
    metadata.setGraphicImageBase64(gifCaptcha.getImageBase64Data());
    metadata.setCharacters(gifCaptcha.getCode());
    return metadata;
  }

  @Override
  public String getCategory() {
    return CaptchaCategory.HUTOOL_GIF.getConstant();
  }
}
