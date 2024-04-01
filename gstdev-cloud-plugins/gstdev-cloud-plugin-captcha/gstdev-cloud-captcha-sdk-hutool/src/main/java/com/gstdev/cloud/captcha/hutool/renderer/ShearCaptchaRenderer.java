package com.gstdev.cloud.captcha.hutool.renderer;

import com.gstdev.cloud.captcha.core.definition.AbstractGraphicRenderer;
import com.gstdev.cloud.captcha.core.definition.domain.Metadata;
import com.gstdev.cloud.captcha.core.definition.enums.CaptchaCategory;
import org.dromara.hutool.swing.captcha.CaptchaUtil;
import org.dromara.hutool.swing.captcha.ShearCaptcha;

/**
 * <p>Description: Hutool扭曲干扰验证码绘制器 </p>
 *
 * @author : cc
 * @date : 2024/12/23 23:08
 */
public class ShearCaptchaRenderer extends AbstractGraphicRenderer {

  @Override
  public Metadata draw() {
    ShearCaptcha shearCaptcha = CaptchaUtil.ofShearCaptcha(this.getWidth(), this.getHeight(), this.getLength(), 4);
    shearCaptcha.setFont(this.getFont());

    Metadata metadata = new Metadata();
    metadata.setGraphicImageBase64(shearCaptcha.getImageBase64Data());
    metadata.setCharacters(shearCaptcha.getCode());
    return metadata;
  }

  @Override
  public String getCategory() {
    return CaptchaCategory.HUTOOL_SHEAR.getConstant();
  }
}
