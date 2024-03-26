// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.commons.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.gstdev.cloud.commons.constant.DefaultConstants;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JsonUtils {

  private static ObjectMapper instance;

  public static ObjectMapper getInstance() {
    if (instance == null) {
      instance = new ObjectMapper();
      instance.setDateFormat(new SimpleDateFormat(DefaultConstants.DATE_TIME_FORMAT));
      instance.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
      instance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    return instance;
  }

  public static <T> T toObject(String json, Class<T> clazz) {
    try {
      return getInstance().readValue(json, clazz);
    } catch (JsonParseException e) {
      log.error("[GstDev Cloud] |- JsonUtils toObject parse json error! {}", e.getMessage());
    } catch (JsonMappingException e) {
      log.error("[GstDev Cloud] |- JsonUtils toObject mapping to object error! {}", e.getMessage());
    } catch (IOException e) {
      log.error("[GstDev Cloud] |- JsonUtils toObject read content error! {}", e.getMessage());
    }

    return null;
  }

  public static <T> String toJson(T entity) {
    try {
      return getInstance().writeValueAsString(entity);
    } catch (JsonParseException e) {
      log.error("[GstDev Cloud] |- JsonUtils toJson parse json error! {}", e.getMessage());
    } catch (JsonMappingException e) {
      log.error("[GstDev Cloud] |- JsonUtils toJson mapping to object error! {}", e.getMessage());
    } catch (IOException e) {
      log.error("[GstDev Cloud] |- JsonUtils toJson read content error! {}", e.getMessage());
    }

    return null;
  }

  public static <T> List<T> toList(String json, Class<T> clazz) {
    JavaType javaType = getInstance().getTypeFactory().constructParametricType(ArrayList.class, clazz);
    try {
      return getInstance().readValue(json, javaType);
    } catch (JsonParseException e) {
      log.error("[GstDev Cloud] |- JsonUtils toList parse json error! {}", e.getMessage());
    } catch (JsonMappingException e) {
      log.error("[GstDev Cloud] |- JsonUtils toList mapping to object error! {}", e.getMessage());
    } catch (IOException e) {
      log.error("[GstDev Cloud] |- JsonUtils toList read content error! {}", e.getMessage());
    }

    return null;
  }

  public static <T> T toCollection(String json, TypeReference<T> typeReference) {
    try {
      return getInstance().readValue(json, typeReference);
    } catch (JsonParseException e) {
      log.error("[GstDev Cloud] |- JsonUtils toCollection parse json error! {}", e.getMessage());
    } catch (JsonMappingException e) {
      log.error("[GstDev Cloud] |- JsonUtils toCollection mapping to object error! {}", e.getMessage());
    } catch (IOException e) {
      log.error("[GstDev Cloud] |- JsonUtils toCollection read content error! {}", e.getMessage());
    }

    return null;
  }
}
