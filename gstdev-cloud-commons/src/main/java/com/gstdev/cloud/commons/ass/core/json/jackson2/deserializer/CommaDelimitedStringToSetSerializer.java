package com.gstdev.cloud.commons.ass.core.json.jackson2.deserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.gstdev.cloud.commons.ass.definition.constants.SymbolConstants;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: 逗号分隔字符串序列化为集合 </p>
 *
 * @author : cc
 * @date : 2023/5/22 14:37
 */
public class CommaDelimitedStringToSetSerializer extends StdSerializer<String> {
  public CommaDelimitedStringToSetSerializer() {
    super(String.class);
  }

  @Override
  public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    Set<String> collection = new HashSet<>();
    if (StringUtils.hasText(value)) {
      if (org.apache.commons.lang3.StringUtils.contains(value, SymbolConstants.COMMA)) {
        collection = StringUtils.commaDelimitedListToSet(value);
      } else {
        collection.add(value);
      }
    }

    int len = collection.size();

    gen.writeStartArray(collection, len);
    serializeContents(collection, gen, provider);
    gen.writeEndArray();
  }

  private void serializeContents(Set<String> value, JsonGenerator g, SerializerProvider provider) throws IOException {
    int i = 0;

    try {
      for (String str : value) {
        if (str == null) {
          provider.defaultSerializeNull(g);
        } else {
          g.writeString(str);
        }
        ++i;
      }
    } catch (Exception e) {
      wrapAndThrow(provider, e, value, i);
    }
  }
}
