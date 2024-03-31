package cn.herodotus.engine.rest.core.exception;

import cn.herodotus.engine.assistant.definition.domain.Feedback;
import cn.herodotus.engine.rest.core.constants.RestErrorCodes;

/**
 * <p>Description: 操作频繁Exception </p>
 *
 * @author : cc
 * @date : 2021/8/25 17:29
 */
public class FrequentRequestsException extends IllegalOperationException {

    public FrequentRequestsException() {
    }

    public FrequentRequestsException(String message) {
        super(message);
    }

    public FrequentRequestsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrequentRequestsException(Throwable cause) {
        super(cause);
    }

    public FrequentRequestsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return RestErrorCodes.FREQUENT_REQUESTS;
    }
}
