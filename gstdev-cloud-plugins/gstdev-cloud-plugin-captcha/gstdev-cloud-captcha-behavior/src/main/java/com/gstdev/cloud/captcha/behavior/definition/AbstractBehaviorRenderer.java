package com.gstdev.cloud.captcha.behavior.definition;

import com.alicp.jetcache.anno.CacheType;
import com.gstdev.cloud.captcha.core.definition.AbstractRenderer;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * <p>Description: 验证码通用基础类 </p>
 *
 * @param <K> 验证码缓存对应Key值的类型。
 * @param <V> 验证码缓存存储数据的值的类型
 * @author : cc
 * @date : 2024/12/11 13:53
 */
public abstract class AbstractBehaviorRenderer<K, V> extends AbstractRenderer<K, V> {

    protected AbstractBehaviorRenderer(String cacheName) {
        super(cacheName);
    }

    protected AbstractBehaviorRenderer(String cacheName, CacheType cacheType) {
        super(cacheName, cacheType);
    }

    protected AbstractBehaviorRenderer(String cacheName, CacheType cacheType, Duration expire) {
        super(cacheName, cacheType, expire);
    }

    protected int getEnOrZhLength(String s) {
        int enCount = 0;
        int zhCount = 0;
        for (int i = 0; i < s.length(); i++) {
            int length = String.valueOf(s.charAt(i)).getBytes(StandardCharsets.UTF_8).length;
            if (length > 1) {
                zhCount++;
            } else {
                enCount++;
            }
        }
        int zhOffset = getHalfWatermarkFontSize() * zhCount + 5;
        int enOffset = enCount * 8;
        return zhOffset + enOffset;
    }

    private int getWatermarkFontSize() {
        return getCaptchaProperties().getWatermark().getFontSize();
    }

    private int getHalfWatermarkFontSize() {
        return getWatermarkFontSize() / 2;
    }

    protected void addWatermark(Graphics graphics, int width, int height) {
        int fontSize = getHalfWatermarkFontSize();
        Font watermakFont = this.getResourceProvider().getWaterMarkFont(fontSize);
        graphics.setFont(watermakFont);
        graphics.setColor(Color.white);
        String content = this.getCaptchaProperties().getWatermark().getContent();
        graphics.drawString(content, width - getEnOrZhLength(content), height - getHalfWatermarkFontSize() + 7);
    }

    protected boolean isUnderOffset(int actualValue, int standardValue, int threshold) {
        return actualValue < standardValue - threshold;
    }

    protected boolean isOverOffset(int actualValue, int standardValue, int threshold) {
        return actualValue > standardValue + threshold;
    }

    protected boolean isDeflected(int actualValue, int standardValue, int threshold) {
        return isUnderOffset(actualValue, standardValue, threshold) || isOverOffset(actualValue, standardValue, threshold);
    }
}
