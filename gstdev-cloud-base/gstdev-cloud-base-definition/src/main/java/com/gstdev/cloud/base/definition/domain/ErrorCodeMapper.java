package com.gstdev.cloud.base.definition.domain;

import com.gstdev.cloud.base.definition.constants.ErrorCodes;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>Description: 错误代码映射器 </p>
 * <p>
 * 采用双重检查方式的单例模式。
 * 1. 保证线程安全
 * 2. 方便在 Exception 中直接调用。
 *
 * @author : cc
 * @date : 2023/9/25 9:11
 */
public class ErrorCodeMapper {

    private static volatile ErrorCodeMapper instance;

    private final Map<Feedback, Integer> dictionary;

    // 初始化默认的错误代码映射关系
    private ErrorCodeMapper() {
        dictionary = new LinkedHashMap<>();
//    {{
//      put(ErrorCodes.OK, ErrorCodes.OK.getSequence());
//      put(ErrorCodes.NO_CONTENT, ErrorCodes.NO_CONTENT.getSequence());
//    }};
        // 获取 ErrorCodes 接口中定义的所有常量并初始化字典

        Field[] fields = ErrorCodes.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                if (Feedback.class.isAssignableFrom(field.getType())) {
                    Feedback feedback = (Feedback) field.get(null);
                    dictionary.put(feedback, feedback.getSequence());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static ErrorCodeMapper getInstance() {
        if (ObjectUtils.isEmpty(instance)) {
            synchronized (ErrorCodeMapper.class) {
                if (ObjectUtils.isEmpty(instance)) {
                    instance = new ErrorCodeMapper();
                }
            }
        }
        return instance;
    }

    public static Integer get(Feedback feedback) {
        return getInstance().getErrorCode(feedback);
    }

    private Integer getErrorCode(Feedback feedback) {
        return dictionary.get(feedback);
    }

    public void append(Map<Feedback, Integer> indexes) {
        if (MapUtils.isNotEmpty(indexes)) {
            dictionary.putAll(indexes);
        }
    }
}
