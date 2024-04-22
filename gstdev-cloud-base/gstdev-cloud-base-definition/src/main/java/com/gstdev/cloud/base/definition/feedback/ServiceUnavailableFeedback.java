package com.gstdev.cloud.base.definition.feedback;


import com.gstdev.cloud.base.definition.domain.Feedback;
import org.springframework.http.HttpStatus;

/**
 * <p>Description: 503 类型错误反馈 </p>
 * <p>
 * 503	Service Unavailable	由于超载或系统维护，服务器暂时的无法处理客户端的请求。延时的长度可包含在服务器的Retry-After头信息中
 *
 * @author : cc
 * @date : 2023/9/26 8:54
 */
public class ServiceUnavailableFeedback extends Feedback {
  public ServiceUnavailableFeedback(String value) {
    super(value, HttpStatus.SERVICE_UNAVAILABLE);
  }
}
