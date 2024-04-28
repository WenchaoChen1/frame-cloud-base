package com.gstdev.cloud.oauth2.resource.server.definition;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * <p>Description: 自定义 AntPathRequestMatcher </p>
 * <p>
 * 基于 AntPathRequestMatcher 代码，扩展了一些方法，解决原有 AntPathRequestMatcher 使用不方便问题。
 */
public final class FrameRequest implements Serializable {

    private String pattern;

    private String httpMethod;

    private boolean hasWildcard;

    public FrameRequest() {
    }

    /**
     * Creates a matcher with the specific pattern which will match all HTTP methods in a
     * case sensitive manner.
     *
     * @param pattern the ant pattern to use for matching
     */
    public FrameRequest(String pattern) {
        this(pattern, null);
    }

    /**
     * Creates a matcher with the supplied pattern which will match the specified Http
     * method
     *
     * @param pattern    the ant pattern to use for matching
     * @param httpMethod the HTTP method. The {@code matches} method will return false if
     *                   the incoming request doesn't have the same method.
     */
    public FrameRequest(String pattern, String httpMethod) {
        Assert.hasText(pattern, "Pattern cannot be null or empty");
        this.pattern = pattern;
        this.hasWildcard = containSpecialCharacters(pattern);
        this.httpMethod = checkHttpMethod(httpMethod);
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public boolean isHasWildcard() {
        return hasWildcard;
    }

    private String checkHttpMethod(String method) {
        if (StringUtils.isNotBlank(method)) {
            HttpMethod httpMethod = HttpMethod.valueOf(method);
            if (ObjectUtils.isNotEmpty(httpMethod)) {
                return httpMethod.name();
            }
        }
        return null;
    }

    private boolean containSpecialCharacters(String source) {
        if (StringUtils.isNotBlank(source)) {
            return StringUtils.containsAny(source, new String[]{"*", "?", "{"});
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FrameRequest that = (FrameRequest) o;
        return Objects.equal(pattern, that.pattern) && Objects.equal(httpMethod, that.httpMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pattern, httpMethod);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("pattern", pattern)
            .add("httpMethod", httpMethod)
            .toString();
    }
}
