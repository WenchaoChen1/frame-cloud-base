package com.gstdev.cloud.base.core.utils.http;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.server.ServerHttpRequest;

/**
 * <p>Description: Session 操作工具类 </p>
 *
 * @author : cc
 * @date : 2023/9/2 15:30
 */
public class SessionUtils {

    private static final String[] SESSION_IDS = new String[]{"JSESSIONID, SESSION"};

    /**
     * 将 getSession 统一封装为一个方法，方便统一修改
     *
     * @param httpServletRequest httpServletRequest {@link HttpServletRequest}
     * @param create             是否创建新的 Session
     * @return {@link HttpSession}
     */
    public static HttpSession getSession(HttpServletRequest httpServletRequest, boolean create) {
        return httpServletRequest.getSession(create);
    }

    /**
     * 将 getSession 统一封装为一个方法，方便统一修改
     * <p>
     * 该方法默认不创建新的 getSession
     *
     * @param httpServletRequest {@link HttpServletRequest}
     * @return {@link HttpSession} or null
     */
    public static HttpSession getSession(HttpServletRequest httpServletRequest) {
        return getSession(httpServletRequest, false);
    }

    /**
     * 获取 Session Id。
     *
     * @param httpServletRequest {@link HttpServletRequest}
     * @param create             create 是否创建新的 Session
     * @return id 或者 null
     */
    public static String getSessionId(HttpServletRequest httpServletRequest, boolean create) {
        HttpSession httpSession = getSession(httpServletRequest, create);
        return ObjectUtils.isNotEmpty(httpSession) ? httpSession.getId() : null;
    }

    /**
     * 获取 Session ID。
     *
     * @param httpServletRequest {@link HttpServletRequest}
     * @return session ID 或者 null
     */
    public static String getSessionId(HttpServletRequest httpServletRequest) {
        return getSessionId(httpServletRequest, false);
    }

    /**
     * 获取 Session ID
     *
     * @param httpInputMessage {@link HttpInputMessage}
     * @return session ID 或者 null
     */
    public static String getSessionIdFromHeader(HttpInputMessage httpInputMessage) {
        return CookieUtils.getAnyFromHeader(httpInputMessage, SESSION_IDS);
    }

    /**
     * 获取 Session ID
     *
     * @param serverHttpRequest {@link ServerHttpRequest}
     * @return session ID 或者 null
     */
    public static String getSessionIdFromHeader(ServerHttpRequest serverHttpRequest) {
        return CookieUtils.getAnyFromHeader(serverHttpRequest, SESSION_IDS);
    }

    /**
     * 解析 Session ID
     * <p>
     * 如果请求中有 X_FRAME_SESSION_ID 头，那么则返回 SessionId，意味着前后端加密有效。
     * 这种处理方式，主要解决在没有使用系统 Session 的环境下，单独调用接口特别是测试接口时，提示 Session 过期的问题。
     *
     * @param httpServletRequest {@link HttpServletRequest}
     * @return session ID 或者 null
     */
    public static String analyseSessionId(HttpServletRequest httpServletRequest) {
        String sessionId = getSessionId(httpServletRequest);
        if (StringUtils.isBlank(sessionId)) {
            sessionId = HeaderUtils.getFrameSessionId(httpServletRequest);
        }
        return sessionId;
    }

    /**
     * 解析 Session ID
     * <p>
     * 如果请求中有 X_FRAME_SESSION_ID 头，那么则返回 SessionId，意味着前后端加密有效。
     * 这种处理方式，主要解决在没有使用系统 Session 的环境下，单独调用接口特别是测试接口时，提示 Session 过期的问题。
     *
     * @param serverHttpRequest {@link ServerHttpRequest}
     * @return session ID 或者 null
     */
    public static String analyseSessionId(ServerHttpRequest serverHttpRequest) {
        String sessionId = getSessionIdFromHeader(serverHttpRequest);
        if (StringUtils.isBlank(sessionId)) {
            sessionId = HeaderUtils.getFrameSessionId(serverHttpRequest);
        }
        return sessionId;
    }

    /**
     * 解析 Session ID
     * <p>
     * 如果请求中有 X_FRAME_SESSION_ID 头，那么则返回 SessionId，意味着前后端加密有效。
     * 这种处理方式，主要解决在没有使用系统 Session 的环境下，单独调用接口特别是测试接口时，提示 Session 过期的问题。
     *
     * @param httpInputMessage {@link HttpInputMessage}
     * @return session ID 或者 null
     */
    public static String analyseSessionId(HttpInputMessage httpInputMessage) {
        String sessionId = getSessionIdFromHeader(httpInputMessage);
        if (StringUtils.isBlank(sessionId)) {
            sessionId = HeaderUtils.getFrameSessionId(httpInputMessage);
        }
        return sessionId;
    }

    /**
     * 判断基于 Session 的前后端数据加密是否开启
     *
     * @param httpServletRequest {@link HttpServletRequest}
     * @param sessionId          SessionId
     * @return true 已开启，false 未开启。
     */
    public static boolean isCryptoEnabled(HttpServletRequest httpServletRequest, String sessionId) {
        return HeaderUtils.hasFrameSessionIdHeader(httpServletRequest) && StringUtils.isNotBlank(sessionId);
    }

    /**
     * 判断基于 Session 的前后端数据加密是否开启
     *
     * @param httpInputMessage {@link HttpInputMessage}
     * @param sessionId        SessionId
     * @return true 已开启，false 未开启。
     */
    public static boolean isCryptoEnabled(HttpInputMessage httpInputMessage, String sessionId) {
        return HeaderUtils.hasFrameSessionIdHeader(httpInputMessage) && StringUtils.isNotBlank(sessionId);
    }
}
