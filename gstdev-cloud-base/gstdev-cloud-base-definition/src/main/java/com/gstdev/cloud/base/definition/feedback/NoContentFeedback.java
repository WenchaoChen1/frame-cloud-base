package com.gstdev.cloud.base.definition.feedback;


import com.gstdev.cloud.base.definition.domain.Feedback;
import org.springframework.http.HttpStatus;

/**
 * <p>Description: 204 类型错误反馈 </p>
 * <p>
 * 204	No Content	无内容。服务器成功处理，但未返回内容。在未更新网页的情况下，可确保浏览器继续显示当前文档
 *
 * @author : cc
 * @date : 2023/9/26 10:11
 */
public class NoContentFeedback extends Feedback {
    public NoContentFeedback(String value) {
        super(value, HttpStatus.NO_CONTENT);
    }
}
