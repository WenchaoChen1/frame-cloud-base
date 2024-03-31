package com.gstdev.cloud.commons.ass.core.exception.transaction;


import com.gstdev.cloud.commons.ass.definition.constants.ErrorCodes;
import com.gstdev.cloud.commons.ass.definition.domain.Feedback;
import com.gstdev.cloud.commons.ass.definition.exception.PlatformRuntimeException;

/**
 * <p>Description: 事务回滚Exception </p>
 *
 * @author : cc
 * @date : 2021/9/21 11:56
 */
public class TransactionalRollbackException extends PlatformRuntimeException {

    public TransactionalRollbackException() {
        super();
    }

    public TransactionalRollbackException(String message) {
        super(message);
    }

    public TransactionalRollbackException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionalRollbackException(Throwable cause) {
        super(cause);
    }

    protected TransactionalRollbackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return ErrorCodes.TRANSACTION_ROLLBACK;
    }
}
