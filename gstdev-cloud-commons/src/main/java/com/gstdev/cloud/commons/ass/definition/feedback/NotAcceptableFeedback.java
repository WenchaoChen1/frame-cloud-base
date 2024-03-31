package com.gstdev.cloud.commons.ass.definition.feedback;


import com.gstdev.cloud.commons.ass.definition.domain.Feedback;
import org.springframework.http.HttpStatus;

/**
 * <p>Description: 406 类型错误反馈 </p>
 * <p>
 * 406	Not Acceptable	服务器无法根据客户端请求的内容特性完成请求
 *
 * @author : cc
 * @date : 2023/9/26 8:52
 */
public class NotAcceptableFeedback extends Feedback {
    public NotAcceptableFeedback(String value) {
        super(value, HttpStatus.NOT_ACCEPTABLE);
    }
}
