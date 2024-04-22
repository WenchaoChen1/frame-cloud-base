package com.gstdev.cloud.base.core.json.jackson2.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Set;

/**
 * <p>Description: Set集合反序列化为逗号分隔字符串 </p>
 *
 * @author : cc
 * @date : 2023/5/22 13:55
 */
public class SetToCommaDelimitedStringDeserializer extends StdDeserializer<String> {

  protected SetToCommaDelimitedStringDeserializer() {
    super(String.class);
  }

  public JavaType getValueType() {
    return TypeFactory.defaultInstance().constructType(Set.class);
  }

  @Override
  public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
    Set<String> collection = jsonParser.readValueAs(new TypeReference<Set<String>>() {
    });

    if (CollectionUtils.isNotEmpty(collection)) {
      return StringUtils.collectionToCommaDelimitedString(collection);
    }

    return null;
  }
}
