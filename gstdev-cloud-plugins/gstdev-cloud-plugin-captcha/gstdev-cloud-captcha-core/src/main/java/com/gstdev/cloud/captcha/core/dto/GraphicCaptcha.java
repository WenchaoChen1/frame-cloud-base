package com.gstdev.cloud.captcha.core.dto;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * <p>Description: 图形验证码 </p>
 *
 * @author : cc
 * @date : 2024/12/21 22:19
 */
public class GraphicCaptcha extends Captcha {

    /**
     * 图形验证码成的图。
     */
    private String graphicImageBase64;

    public GraphicCaptcha() {
    }

    public String getGraphicImageBase64() {
        return graphicImageBase64;
    }

    public void setGraphicImageBase64(String graphicImageBase64) {
        this.graphicImageBase64 = graphicImageBase64;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GraphicCaptcha that = (GraphicCaptcha) o;
        return Objects.equal(graphicImageBase64, that.graphicImageBase64);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(graphicImageBase64);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("graphicImageBase64", graphicImageBase64)
            .toString();
    }
}
