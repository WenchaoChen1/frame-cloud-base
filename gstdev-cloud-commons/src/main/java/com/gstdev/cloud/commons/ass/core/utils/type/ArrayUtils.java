package com.gstdev.cloud.commons.ass.core.utils.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>Description: 数组工具类 </p>
 *
 * @author : cc
 * @date : 2023/5/3 23:12
 */
public class ArrayUtils {

    /**
     * 将字符串数组转换成字符串List
     *
     * @param array 字符串数组
     * @return 字符串List
     */
    public static List<String> toStringList(String[] array) {
        if (org.apache.commons.lang3.ArrayUtils.isNotEmpty(array)) {
            List<String> list = new ArrayList<>(array.length);
            Collections.addAll(list, array);
            return list;
        } else {
            return new ArrayList<>();
        }
    }
}
