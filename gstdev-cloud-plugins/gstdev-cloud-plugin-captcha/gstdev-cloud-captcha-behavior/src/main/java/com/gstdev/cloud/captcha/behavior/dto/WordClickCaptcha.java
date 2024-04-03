package com.gstdev.cloud.captcha.behavior.dto;

import com.gstdev.cloud.captcha.core.dto.Captcha;
import com.google.common.base.MoreObjects;

/**
 * <p>Description: 文字点选验证码返回前台信息 </p>
 *
 * @author : cc
 * @date : 2024/12/14 11:35
 */
public class WordClickCaptcha extends Captcha {

  /**
   * 文字点选验证码生成的带文字背景图。
   */
  private String wordClickImageBase64;

  /**
   * 文字点选验证码文字
   */
  private String words;

  /**
   * 需要点击的文字数量
   */
  private Integer wordsCount;

  public String getWordClickImageBase64() {
    return wordClickImageBase64;
  }

  public void setWordClickImageBase64(String wordClickImageBase64) {
    this.wordClickImageBase64 = wordClickImageBase64;
  }

  public String getWords() {
    return words;
  }

  public void setWords(String words) {
    this.words = words;
  }

  public Integer getWordsCount() {
    return wordsCount;
  }

  public void setWordsCount(Integer wordsCount) {
    this.wordsCount = wordsCount;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("wordClickImageBase64", wordClickImageBase64)
      .add("words", words)
      .add("wordsCount", wordsCount)
      .toString();
  }
}
