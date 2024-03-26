package com.gstdev.cloud.commons.feedback;


import com.gstdev.cloud.commons.domain.Feedback;
import org.springframework.http.HttpStatus;

/**
 * <p>Description: 415 类型错误反馈 </p>
 * <p>
 * 415	Unsupported Media Type	服务器无法处理请求附带的媒体格式
 *
 * @author : cc
 * @date : 2023/9/26 8:53
 */
public class UnsupportedMediaTypeFeedback extends Feedback {
    public UnsupportedMediaTypeFeedback(String value) {
        super(value, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
