package com.gstdev.cloud.captcha.graphic.renderer;

import com.gstdev.cloud.captcha.core.definition.enums.CaptchaCategory;
import com.gstdev.cloud.captcha.graphic.definition.AbstractPngGraphicRenderer;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * <p>Description: 中文类型验证码绘制器 </p>
 *
 * @author : cc
 * @date : 2024/12/20 22:55
 */
//@Component
public class ChineseCaptchaRenderer extends AbstractPngGraphicRenderer {

  @Override
  public String getCategory() {
    return CaptchaCategory.CHINESE.getConstant();
  }

  @Override
  protected String[] getDrawCharacters() {
    return this.getWordCharacters();
  }

  @Override
  protected Font getFont() {
    return this.getResourceProvider().getChineseFont();
  }
}
