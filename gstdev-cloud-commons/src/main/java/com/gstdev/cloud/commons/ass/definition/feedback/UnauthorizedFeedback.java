package com.gstdev.cloud.commons.ass.definition.feedback;


import com.gstdev.cloud.commons.ass.definition.domain.Feedback;
import org.springframework.http.HttpStatus;

/**
 * <p>Description: 401 类型错误反馈 </p>
 * <p>
 * 401	Unauthorized	请求要求用户的身份认证
 *
 * @author : cc
 * @date : 2023/9/26 8:48
 */
public class UnauthorizedFeedback extends Feedback {
    public UnauthorizedFeedback(String value) {
        super(value, HttpStatus.UNAUTHORIZED);
    }
}
