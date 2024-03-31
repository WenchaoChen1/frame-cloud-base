
//package com.gstdev.cloud.commons.ass.core.utils.protect;
//
//import com.gstdev.cloud.commons.ass.core.utils.ResourceUtils;
//import org.apache.commons.lang3.ObjectUtils;
//import org.apache.commons.text.StringEscapeUtils;
//import org.owasp.validator.html.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.net.URL;
//
///**
// * <p>Description: Antisamy 单例 工具类 </p>
// *
// * @author : cc
// * @date : 2021/8/29 16:15
// */
//public class XssUtils {
//
//    private static final Logger log = LoggerFactory.getLogger(XssUtils.class);
//
//    private static volatile XssUtils INSTANCE;
//    private final AntiSamy antiSamy;
//    private final String nbsp;
//    private final String quot;
//
//    private XssUtils() {
//        Policy policy = createPolicy();
//        this.antiSamy = ObjectUtils.isNotEmpty(policy) ? new AntiSamy(policy) : new AntiSamy();
//        this.nbsp = cleanHtml("&nbsp;");
//        this.quot = cleanHtml("\"");
//    }
//
//    private static XssUtils getInstance() {
//        if (ObjectUtils.isEmpty(INSTANCE)) {
//            synchronized (XssUtils.class) {
//                if (ObjectUtils.isEmpty(INSTANCE)) {
//                    INSTANCE = new XssUtils();
//                }
//            }
//        }
//
//        return INSTANCE;
//    }
//
//    public static String cleaning(String taintedHTML) {
//        // 对转义的HTML特殊字符（<、>、"等）进行反转义，因为AntiSamy调用scan方法时会将特殊字符转义
//        String cleanHtml = StringEscapeUtils.unescapeHtml4(getInstance().cleanHtml(taintedHTML));
//        //AntiSamy会把“&nbsp;”转换成乱码，把双引号转换成"&quot;" 先将&nbsp;的乱码替换为空，双引号的乱码替换为双引号
//        String temp = cleanHtml.replaceAll(getInstance().nbsp, "");
//        temp = temp.replaceAll(getInstance().quot, "\"");
//        String result = temp.replaceAll("\n", "");
//        log.trace("[GstDev Cloud] |- Antisamy process value from [{}] to [{}]", taintedHTML, result);
//        return result;
//    }
//
//    private Policy createPolicy() {
//        try {
//            URL url = ResourceUtils.getURL("classpath:antisamy/antisamy-anythinggoes.xml");
//            return Policy.getInstance(url);
//        } catch (IOException | PolicyException e) {
//            log.warn("[GstDev Cloud] |- Antisamy create policy error! {}", e.getMessage());
//            return null;
//        }
//    }
//
//    private CleanResults scan(String taintedHtml) throws ScanException, PolicyException {
//        return antiSamy.scan(taintedHtml);
//    }
//
//    private String cleanHtml(String taintedHtml) {
//        try {
//            // 使用AntiSamy清洗数据
//            final CleanResults cleanResults = scan(taintedHtml);
//            return cleanResults.getCleanHTML();
//        } catch (ScanException | PolicyException e) {
//            log.error("[GstDev Cloud] |- Antisamy scan catch error! {}", e.getMessage());
//            return taintedHtml;
//        }
//    }
//}
