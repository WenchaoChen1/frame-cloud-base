package com.gstdev.cloud.commons.ass.definition.feedback;


import com.gstdev.cloud.commons.ass.definition.domain.Feedback;
import org.springframework.http.HttpStatus;

/**
 * <p>Description: 406 类型错误反馈 </p>
 * <p>
 * 404	Not Found	服务器无法根据客户端的请求找到资源（网页）。通过此代码，网站设计人员可设置"您所请求的资源无法找到"的个性页面
 *
 * @author : cc
 * @date : 2023/9/27 20:07
 */
public class NotFoundFeedback extends Feedback {

    public NotFoundFeedback(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
