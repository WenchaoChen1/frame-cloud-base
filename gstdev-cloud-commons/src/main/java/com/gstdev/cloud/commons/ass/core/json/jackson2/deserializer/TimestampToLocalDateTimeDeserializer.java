package com.gstdev.cloud.commons.ass.core.json.jackson2.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * <p>Description: Timestamp 转 LocalDateTime 反序列化器 </p>
 *
 * @author : cc
 * @date : 2023/9/22 16:46
 */
public class TimestampToLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
  @Override
  public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

    long timestamp = jsonParser.getValueAsLong();
    if (timestamp > 0) {
      Instant instant = Instant.ofEpochMilli(timestamp);
      return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    } else {
      return null;
    }
  }
}
