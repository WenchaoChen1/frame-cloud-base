package com.gstdev.cloud.commons.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class ListUtils {
  public ListUtils() {
  }

  public static List<String> merge(List<String> appendResources, List<String> defaultResources) {
    return CollectionUtils.isEmpty(appendResources) ? defaultResources : CollectionUtils.collate(defaultResources, appendResources);
  }

  public static String[] toStringArray(List<String> resources) {
    if (CollectionUtils.isNotEmpty(resources)) {
      String[] result = new String[resources.size()];
      return (String[])resources.toArray(result);
    } else {
      return new String[0];
    }
  }
}
