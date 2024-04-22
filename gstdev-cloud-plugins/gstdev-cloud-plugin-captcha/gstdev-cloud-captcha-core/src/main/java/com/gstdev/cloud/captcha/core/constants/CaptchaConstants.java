package com.gstdev.cloud.captcha.core.constants;


import com.gstdev.cloud.base.definition.constants.BaseConstants;

/**
 * <p>Description: 验证码常量 </p>
 *
 * @author : cc
 * @date : 2024/1/18 19:06
 */
public interface CaptchaConstants extends BaseConstants {

  String CACHE_NAME_TOKEN_CAPTCHA = CACHE_TOKEN_BASE_PREFIX + "captcha:";

  String CACHE_NAME_CAPTCHA_JIGSAW = CACHE_NAME_TOKEN_CAPTCHA + "jigsaw:";
  String CACHE_NAME_CAPTCHA_WORD_CLICK = CACHE_NAME_TOKEN_CAPTCHA + "word_click:";
  String CACHE_NAME_CAPTCHA_GRAPHIC = CACHE_NAME_TOKEN_CAPTCHA + "graphic:";
}
