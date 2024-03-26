package com.gstdev.cloud.commons.feedback;


import com.gstdev.cloud.commons.domain.Feedback;
import org.springframework.http.HttpStatus;

/**
 * <p>Description: 200 类型错误反馈 </p>
 * <p>
 * 200	OK	请求成功。一般用于GET与POST请求
 *
 * @author : cc
 * @date : 2023/9/26 10:07
 */
public class OkFeedback extends Feedback {
    public OkFeedback(String value) {
        super(value, HttpStatus.OK);
    }
}
