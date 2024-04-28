package com.gstdev.cloud.base.definition.exception;

import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.domain.Result;

/**
 * <p>Description: 核心 FrameException 定义 </p>
 *
 * @author : cc
 * @date : 2022/3/8 9:06
 */
public interface FrameException {

    /**
     * 获取反馈信息
     *
     * @return 反馈信息对象 {@link Feedback}
     */
    Feedback getFeedback();

    /**
     * 错误信息转换为 Result 对象。
     *
     * @return 结果对象 {@link Result}
     */
    Result<String> getResult();
}
