package com.gstdev.cloud.commons.feedback;


import com.gstdev.cloud.commons.domain.Feedback;
import org.springframework.http.HttpStatus;


/**
 * <p>Description: 自定义类型错误反馈 </p>
 * <p>
 * 自定义错误码超过 HttpStatus 范围的自定义错误代码类型
 *
 * @author : cc
 * @date : 2023/9/26 15:41
 */
public class CustomizeFeedback extends Feedback {
    public CustomizeFeedback(String value, int custom) {
        super(value, HttpStatus.INTERNAL_SERVER_ERROR, custom);
    }
}
