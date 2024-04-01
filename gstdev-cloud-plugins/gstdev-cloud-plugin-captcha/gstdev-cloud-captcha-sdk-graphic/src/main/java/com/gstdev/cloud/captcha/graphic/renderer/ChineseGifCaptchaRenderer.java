package com.gstdev.cloud.captcha.graphic.renderer;

import com.gstdev.cloud.captcha.core.definition.enums.CaptchaCategory;
import com.gstdev.cloud.captcha.graphic.definition.AbstractGifGraphicRenderer;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * <p>Description: 中文Gif类型验证码绘制器 </p>
 *
 * @author : cc
 * @date : 2024/12/20 22:55
 */
//@Component
public class ChineseGifCaptchaRenderer extends AbstractGifGraphicRenderer {

  @Override
  public String getCategory() {
    return CaptchaCategory.CHINESE_GIF.getConstant();
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
