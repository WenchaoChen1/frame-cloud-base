package com.gstdev.cloud.rest.protect.crypto.enhance;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.gstdev.cloud.base.core.json.jackson2.utils.Jackson2Utils;
import com.gstdev.cloud.base.core.utils.http.SessionUtils;
import com.gstdev.cloud.rest.core.annotation.Crypto;
import com.gstdev.cloud.rest.core.exception.SessionInvalidException;
import com.gstdev.cloud.rest.protect.crypto.processor.HttpCryptoProcessor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.io.IoUtil;
import org.dromara.hutool.core.util.ByteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>Description: RequestBody 解密 Advice</p>
 *
 * @author : cc
 * @date : 2021/10/4 12:15
 */
@RestControllerAdvice
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {

    private static final Logger log = LoggerFactory.getLogger(DecryptRequestBodyAdvice.class);

    private HttpCryptoProcessor httpCryptoProcessor;

    public void setInterfaceCryptoProcessor(HttpCryptoProcessor httpCryptoProcessor) {
        this.httpCryptoProcessor = httpCryptoProcessor;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {

        String methodName = methodParameter.getMethod().getName();
        Crypto crypto = methodParameter.getMethodAnnotation(Crypto.class);

        boolean isSupports = ObjectUtils.isNotEmpty(crypto) && crypto.requestDecrypt();

        log.trace("[GstDev Cloud] |- Is DecryptRequestBodyAdvice supports method [{}] ? Status is [{}].", methodName, isSupports);
        return isSupports;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {

        String sessionId = SessionUtils.analyseSessionId(httpInputMessage);

        if (SessionUtils.isCryptoEnabled(httpInputMessage, sessionId)) {

            log.info("[GstDev Cloud] |- DecryptRequestBodyAdvice begin decrypt data.");

            String methodName = methodParameter.getMethod().getName();
            String className = methodParameter.getDeclaringClass().getName();

            String content = IoUtil.read(httpInputMessage.getBody()).toString();

            if (StringUtils.isNotBlank(content)) {
                String data = httpCryptoProcessor.decrypt(sessionId, content);
                if (StringUtils.equals(data, content)) {
                    data = decrypt(sessionId, content);
                }
                log.debug("[GstDev Cloud] |- Decrypt request body for rest method [{}] in [{}] finished.", methodName, className);
                return new DecryptHttpInputMessage(httpInputMessage, ByteUtil.toUtf8Bytes(data));
            } else {
                return httpInputMessage;
            }
        } else {
            log.warn("[GstDev Cloud] |- Cannot find GstDev Cloud custom session header. Use interface crypto founction need add X_GstDev_SESSION_ID to request header.");
            return httpInputMessage;
        }
    }

    private String decrypt(String sessionKey, String content) throws SessionInvalidException {
        JsonNode jsonNode = Jackson2Utils.toNode(content);
        if (ObjectUtils.isNotEmpty(jsonNode)) {
            decrypt(sessionKey, jsonNode);
            return Jackson2Utils.toJson(jsonNode);
        }

        return content;
    }

    private void decrypt(String sessionKey, JsonNode jsonNode) throws SessionInvalidException {
        if (jsonNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                if (entry.getValue() instanceof TextNode t && entry.getValue().isValueNode()) {
                    String value = httpCryptoProcessor.decrypt(sessionKey, t.asText());
                    entry.setValue(new TextNode(value));
                }
                decrypt(sessionKey, entry.getValue());
            }
        }

        if (jsonNode.isArray()) {
            for (JsonNode node : jsonNode) {
                decrypt(sessionKey, node);
            }
        }
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    public static class DecryptHttpInputMessage implements HttpInputMessage {

        private final HttpInputMessage httpInputMessage;
        private final byte[] data;

        public DecryptHttpInputMessage(HttpInputMessage httpInputMessage, byte[] data) {
            this.httpInputMessage = httpInputMessage;
            this.data = data;
        }

        @Override
        public InputStream getBody() throws IOException {
            return new ByteArrayInputStream(this.data);
        }

        @Override
        public HttpHeaders getHeaders() {
            return this.httpInputMessage.getHeaders();
        }
    }
}
