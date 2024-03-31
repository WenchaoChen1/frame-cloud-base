package com.gstdev.cloud.commons.ass.core.exception;

import cn.herodotus.engine.assistant.definition.constants.ErrorCodes;
import cn.herodotus.engine.assistant.definition.domain.Feedback;
import cn.herodotus.engine.assistant.definition.exception.PlatformRuntimeException;

/**
 * <p>Description: 发现尚未记录的错误 </p>
 *
 * @author : cc
 * @date : 2023/12/20 16:23
 */
public class DiscoveredUnrecordedErrorException extends PlatformRuntimeException {

    public DiscoveredUnrecordedErrorException() {
        super();
    }

    public DiscoveredUnrecordedErrorException(String message) {
        super(message);
    }

    public DiscoveredUnrecordedErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public DiscoveredUnrecordedErrorException(Throwable cause) {
        super(cause);
    }

    protected DiscoveredUnrecordedErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return ErrorCodes.DISCOVERED_UNRECORDED_ERROR_EXCEPTION;
    }
}
