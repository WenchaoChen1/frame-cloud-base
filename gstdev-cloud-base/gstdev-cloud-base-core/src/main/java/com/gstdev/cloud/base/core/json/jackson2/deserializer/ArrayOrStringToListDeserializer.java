package com.gstdev.cloud.base.core.json.jackson2.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Description: 数组转字符串序列化 </p>
 *
 * @author : cc
 * @date : 2022/3/18 12:16
 */
public class ArrayOrStringToListDeserializer extends StdDeserializer<List<String>> {

    public ArrayOrStringToListDeserializer() {
        super(List.class);
    }
    @Override
    public JavaType getValueType() {
        return TypeFactory.defaultInstance().constructType(String.class);
    }

    @Override
    public List<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonToken token = jsonParser.getCurrentToken();
        if (token.isScalarValue()) {
            String value = jsonParser.getText();
            value = value.replaceAll("\\s+", ",");
            return new ArrayList<>(Arrays.asList(StringUtils.commaDelimitedListToStringArray(value)));
        } else {
            return jsonParser.readValueAs(new TypeReference<List<String>>() {
            });
        }
    }


}
