package com.gstdev.cloud.springframework.openfeign.feign;

import com.fasterxml.jackson.databind.JavaType;
import com.gstdev.cloud.base.core.json.jackson2.utils.Jackson2Utils;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.springframework.openfeign.exception.FeignDecodeIOException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * <p>Description: Feign 错误信息解码器 </p>
 */
public class FeignErrorDecoder implements ErrorDecoder {

    private static final Logger log = LoggerFactory.getLogger(FeignErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {

        try {
            String content = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
            Result<String> result = Result.failure("Feign remote call" + methodKey + " error");
            JavaType javaType = Jackson2Utils.getTypeFactory().constructParametricType(Result.class, String.class);
            Result<String> object = Jackson2Utils.toObject(content, javaType);
            if (ObjectUtils.isNotEmpty(object)) {
                result = object;
            }
            return new FeignRemoteCallExceptionWrapper(result);
        } catch (IOException e) {
            log.error("[GstDev Cloud] |- Feign invoke [{}] error decoder convert result catch io exception.", methodKey, e);
            return new FeignDecodeIOException();
        }
    }
}
