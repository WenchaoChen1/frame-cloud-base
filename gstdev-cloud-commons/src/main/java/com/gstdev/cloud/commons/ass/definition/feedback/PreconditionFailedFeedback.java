package com.gstdev.cloud.commons.ass.definition.feedback;


import com.gstdev.cloud.commons.ass.definition.domain.Feedback;
import org.springframework.http.HttpStatus;

/**
 * <p>Description: 412 类型错误反馈 </p>
 * <p>
 * 412	Precondition Failed	客户端请求信息的先决条件错误
 *
 * @author : cc
 * @date : 2023/9/26 8:53
 */
public class PreconditionFailedFeedback extends Feedback {
  public PreconditionFailedFeedback(String value) {
    super(value, HttpStatus.PRECONDITION_FAILED);
  }
}
