package com.gstdev.cloud.message.core.definition.domain;

import org.springframework.util.MimeType;

/**
 * <p>Description: Spring Cloud Stream 类型消息参数实体 </p>
 *
 * @author : cc
 * @date : 2023/10/26 14:57
 */
public class StreamMessage implements Message {

    private String bindingName;
    private String binderName;
    private Object data;
    private MimeType outputContentType;

    public String getBindingName() {
        return bindingName;
    }

    public void setBindingName(String bindingName) {
        this.bindingName = bindingName;
    }

    public String getBinderName() {
        return binderName;
    }

    public void setBinderName(String binderName) {
        this.binderName = binderName;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public MimeType getOutputContentType() {
        return outputContentType;
    }

    public void setOutputContentType(MimeType outputContentType) {
        this.outputContentType = outputContentType;
    }
}
