//package com.gstdev.cloud.data.core.enums;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//
//import java.io.IOException;
//
//public class DataItemStatusDeserializer extends JsonDeserializer<DataItemStatus> {
//    @Override
//    public DataItemStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
//        String value = p.getText();
//        try {
//            return DataItemStatus.valueOf(value);
//        } catch (IllegalArgumentException e) {
//            throw new RuntimeException("Invalid value for DataItemStatus: " + value);
//        }
//    }
//}
