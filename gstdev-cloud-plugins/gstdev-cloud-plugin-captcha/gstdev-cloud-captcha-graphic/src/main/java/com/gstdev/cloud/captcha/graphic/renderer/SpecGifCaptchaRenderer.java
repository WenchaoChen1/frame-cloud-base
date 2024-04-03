package com.gstdev.cloud.captcha.graphic.renderer;

import com.gstdev.cloud.captcha.core.definition.enums.CaptchaCategory;
import com.gstdev.cloud.captcha.graphic.definition.AbstractGifGraphicRenderer;
import org.springframework.stereotype.Component;

/**
 * <p>Description: Gif 类型验证码绘制器 </p>
 *
 * @author : cc
 * @date : 2024/12/20 22:54
 */
//@Component
public class SpecGifCaptchaRenderer extends AbstractGifGraphicRenderer {

  @Override
  public String getCategory() {
    return CaptchaCategory.SPEC_GIF.getConstant();
  }

  @Override
  protected String[] getDrawCharacters() {
    return this.getCharCharacters();
  }
}
