package com.gstdev.cloud.captcha.core.definition;

import com.gstdev.cloud.captcha.core.constants.CaptchaConstants;
import com.gstdev.cloud.captcha.core.definition.domain.Metadata;
import com.gstdev.cloud.captcha.core.dto.Captcha;
import com.gstdev.cloud.captcha.core.dto.GraphicCaptcha;
import com.gstdev.cloud.captcha.core.dto.Verification;
import com.gstdev.cloud.captcha.core.exception.CaptchaHasExpiredException;
import com.gstdev.cloud.captcha.core.exception.CaptchaIsEmptyException;
import com.gstdev.cloud.captcha.core.exception.CaptchaMismatchException;
import com.gstdev.cloud.captcha.core.exception.CaptchaParameterIllegalException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.data.id.IdUtil;

import java.awt.*;

/**
 * <p>Description: 抽象的图形验证码 </p>
 *
 * @author : cc
 * @date : 2024/12/20 20:42
 */
public abstract class AbstractGraphicRenderer extends AbstractRenderer<String, String> {

  private GraphicCaptcha graphicCaptcha;

  public AbstractGraphicRenderer() {
    super(CaptchaConstants.CACHE_NAME_CAPTCHA_GRAPHIC);
  }

  protected Font getFont() {
    return this.getResourceProvider().getGraphicFont();
  }

  protected int getWidth() {
    return this.getCaptchaProperties().getGraphics().getWidth();
  }

  protected int getHeight() {
    return this.getCaptchaProperties().getGraphics().getHeight();
  }

  protected int getLength() {
    return this.getCaptchaProperties().getGraphics().getLength();
  }

  @Override
  public Captcha getCapcha(String key) {
    String identity = key;
    if (StringUtils.isBlank(identity)) {
      identity = IdUtil.fastUUID();
    }

    this.create(identity);
    return getGraphicCaptcha();
  }

  @Override
  public boolean verify(Verification verification) {

    if (ObjectUtils.isEmpty(verification) || StringUtils.isEmpty(verification.getIdentity())) {
      throw new CaptchaParameterIllegalException("Parameter value is illegal");
    }

    if (StringUtils.isEmpty(verification.getCharacters())) {
      throw new CaptchaIsEmptyException("Captcha is empty");
    }

    String store = this.get(verification.getIdentity());
    if (StringUtils.isEmpty(store)) {
      throw new CaptchaHasExpiredException("Stamp is invalid!");
    }

    this.delete(verification.getIdentity());

    String real = verification.getCharacters();

    if (!StringUtils.equalsIgnoreCase(store, real)) {
      throw new CaptchaMismatchException();
    }

    return true;
  }

  private GraphicCaptcha getGraphicCaptcha() {
    return graphicCaptcha;
  }

  protected void setGraphicCaptcha(GraphicCaptcha graphicCaptcha) {
    this.graphicCaptcha = graphicCaptcha;
  }

  @Override
  public String nextStamp(String key) {
    Metadata metadata = draw();

    GraphicCaptcha graphicCaptcha = new GraphicCaptcha();
    graphicCaptcha.setIdentity(key);
    graphicCaptcha.setGraphicImageBase64(metadata.getGraphicImageBase64());
    graphicCaptcha.setCategory(getCategory());
    this.setGraphicCaptcha(graphicCaptcha);

    return metadata.getCharacters();
  }
}
