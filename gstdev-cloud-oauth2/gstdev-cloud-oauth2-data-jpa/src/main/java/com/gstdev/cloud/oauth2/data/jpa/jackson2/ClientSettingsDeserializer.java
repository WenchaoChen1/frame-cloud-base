package com.gstdev.cloud.oauth2.data.jpa.jackson2;

import com.gstdev.cloud.base.core.json.jackson2.utils.JsonNodeUtils;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.io.IOException;
import java.util.Map;

/**
 * <p>Description: ClientSettingsDeserializer </p>
 *
 * @author : cc
 * @date : 2022/10/24 23:18
 */
public class ClientSettingsDeserializer extends JsonDeserializer<ClientSettings> {

  @Override
  public ClientSettings deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
    ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
    JsonNode jsonNode = mapper.readTree(jsonParser);

    Map<String, Object> settings = JsonNodeUtils.findValue(jsonNode, "settings", JsonNodeUtils.STRING_OBJECT_MAP, mapper);

    return ClientSettings.withSettings(settings).build();
  }
}
