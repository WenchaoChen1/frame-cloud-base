package com.gstdev.cloud.commons.ass.definition.feedback;


import com.gstdev.cloud.commons.ass.definition.domain.Feedback;
import org.springframework.http.HttpStatus;

/**
 * <p>Description: 403 类型错误反馈 </p>
 * <p>
 * 403	Forbidden	服务器理解请求客户端的请求，但是拒绝执行此请求
 *
 * @author : cc
 * @date : 2023/9/26 8:51
 */
public class ForbiddenFeedback extends Feedback {
    public ForbiddenFeedback(String value) {
        super(value, HttpStatus.FORBIDDEN);
    }
}
