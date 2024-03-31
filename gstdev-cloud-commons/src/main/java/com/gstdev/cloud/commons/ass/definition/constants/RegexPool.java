package com.gstdev.cloud.commons.ass.definition.constants;

/**
 * <p>Description: 常用正则表达式 </p>
 *
 * @author : cc
 * @date : 2021/10/12 10:43
 */
public interface RegexPool extends org.dromara.hutool.core.regex.RegexPool {

    /**
     * 匹配大括号以及其中的内容，
     * <p>
     * 示例： "ab{gnfnm}ah{hell}o"，匹配结果：{gnfnm}、{hell}
     */
    String BRACES_AND_CONTENT = "\\{([^}])*\\}";

    /**
     * 匹配所有字符
     * <p>
     * 示例：String cat = "abc", cat.split((?!^)) 匹配结果： array["a", "b", "c"]
     */
    String ALL_CHARACTERS = "(?!^)";

    /**
     * 单引号字符串等式
     * <p>
     * 示例：pattern='/open/**'  匹配结果：pattern 和 /open/**
     */
    String SINGLE_QUOTE_STRING_EQUATION = "(\\w+)\\s*=\\s*'(.*?)'";

    /**
     * Bucket DNS 兼容
     */
    String DNS_COMPATIBLE = "^[a-z0-9][a-z0-9\\.\\-]+[a-z0-9]$";

}
