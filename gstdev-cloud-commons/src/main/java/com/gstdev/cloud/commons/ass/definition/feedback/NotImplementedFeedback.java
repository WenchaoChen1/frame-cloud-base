package com.gstdev.cloud.commons.ass.definition.feedback;


import com.gstdev.cloud.commons.ass.definition.domain.Feedback;
import org.springframework.http.HttpStatus;

/**
 * <p>Description: 501 类型错误反馈 </p>
 * <p>
 * 501	Not Implemented	服务器不支持请求的功能，无法完成请求
 *
 * @author : cc
 * @date : 2023/9/26 8:54
 */
public class NotImplementedFeedback extends Feedback {
    public NotImplementedFeedback(String value) {
        super(value, HttpStatus.NOT_IMPLEMENTED);
    }
}
