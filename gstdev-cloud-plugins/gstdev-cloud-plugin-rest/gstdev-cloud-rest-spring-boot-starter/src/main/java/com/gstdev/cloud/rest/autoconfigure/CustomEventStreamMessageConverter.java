package com.gstdev.cloud.rest.autoconfigure;

import com.gstdev.cloud.base.definition.domain.Result;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class CustomEventStreamMessageConverter extends AbstractHttpMessageConverter<Object> {

    public CustomEventStreamMessageConverter() {
        super(new MediaType("text", "event-stream", StandardCharsets.UTF_8));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return Result.class.isAssignableFrom(clazz);
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(outputMessage.getBody(), StandardCharsets.UTF_8)) {
            writer.write("data: ");
            writer.write(object.toString()); // 自定义序列化逻辑
            writer.write("\n\n");
        }
    }
}