package com.gstdev.cloud.oauth2.data.jpa.jackson2;

import com.gstdev.cloud.base.core.json.jackson2.utils.JsonNodeUtils;
import com.gstdev.cloud.oauth2.core.definition.domain.FrameGrantedAuthority;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * <p>Description: FrameGrantedAuthority 反序列化 </p>
 *
 * @author : cc
 * @date : 2022/3/17 20:28
 */
public class FrameGrantedAuthorityDeserializer extends JsonDeserializer<FrameGrantedAuthority> {
    @Override
    public FrameGrantedAuthority deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        String authority = JsonNodeUtils.findStringValue(jsonNode, "authority");
        return new FrameGrantedAuthority(authority);
    }
}
