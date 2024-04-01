package com.gstdev.cloud.commons.ass.definition.feedback;


import com.gstdev.cloud.commons.ass.definition.domain.Feedback;
import org.springframework.http.HttpStatus;

/**
 * <p>Description: 405 类型错误反馈 </p>
 * <p>
 * 405	Method Not Allowed	客户端请求中的方法被禁止
 *
 * @author : cc
 * @date : 2023/9/26 8:52
 */
public class MethodNotAllowedFeedback extends Feedback {
  public MethodNotAllowedFeedback(String value) {
    super(value, HttpStatus.METHOD_NOT_ALLOWED);
  }
}
