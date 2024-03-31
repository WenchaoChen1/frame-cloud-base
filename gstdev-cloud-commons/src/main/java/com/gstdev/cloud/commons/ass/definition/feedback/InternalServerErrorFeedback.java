package com.gstdev.cloud.commons.ass.definition.feedback;


import com.gstdev.cloud.commons.ass.definition.domain.Feedback;
import org.springframework.http.HttpStatus;

/**
 * <p>Description: 500 类型错误反馈 </p>
 * <p>
 * 500	Internal Server Error	服务器内部错误，无法完成请求
 *
 * @author : cc
 * @date : 2023/9/26 8:54
 */
public class InternalServerErrorFeedback extends Feedback {
    public InternalServerErrorFeedback(String value) {
        super(value, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
