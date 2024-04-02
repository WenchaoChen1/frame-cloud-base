//package com.gstdev.cloud.rest.protect.jackson2;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.gstdev.cloud.commons.ass.core.utils.protect.XssUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//
///**
// * <p>Description: Xss Json 处理 </p>
// *
// * @author : cc
// * @date : 2021/8/30 23:58
// */
//public class XssStringJsonDeserializer extends JsonDeserializer<String> {
//
//  private static final Logger log = LoggerFactory.getLogger(XssStringJsonDeserializer.class);
//
//  @Override
//  public Class<String> handledType() {
//    return String.class;
//  }
//
//  @Override
//  public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//    String value = jsonParser.getValueAsString();
//    if (StringUtils.isNotBlank(value)) {
//      return XssUtils.cleaning(value);
//    }
//
//    return value;
//  }
//}
