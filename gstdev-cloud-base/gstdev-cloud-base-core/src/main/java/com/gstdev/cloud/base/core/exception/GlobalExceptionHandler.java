// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.base.core.exception;

import com.gstdev.cloud.base.definition.exception.FrameException;
import com.gstdev.cloud.base.definition.constants.ErrorCodes;
import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.domain.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理器
 *
 * @author cc
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final Map<String, Feedback> EXCEPTION_DICTIONARY = new HashMap<>();

    static {
        EXCEPTION_DICTIONARY.put("AccessDeniedException", ErrorCodes.ACCESS_DENIED);
        EXCEPTION_DICTIONARY.put("InsufficientAuthenticationException", ErrorCodes.ACCESS_DENIED);
        EXCEPTION_DICTIONARY.put("HttpRequestMethodNotSupportedException", ErrorCodes.HTTP_REQUEST_METHOD_NOT_SUPPORTED);
        EXCEPTION_DICTIONARY.put("HttpMediaTypeNotAcceptableException", ErrorCodes.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE);
        EXCEPTION_DICTIONARY.put("IllegalArgumentException", ErrorCodes.ILLEGAL_ARGUMENT_EXCEPTION);
        EXCEPTION_DICTIONARY.put("NullPointerException", ErrorCodes.NULL_POINTER_EXCEPTION);
        EXCEPTION_DICTIONARY.put("IOException", ErrorCodes.IO_EXCEPTION);
        EXCEPTION_DICTIONARY.put("HttpMessageNotReadableException", ErrorCodes.HTTP_MESSAGE_NOT_READABLE_EXCEPTION);
        EXCEPTION_DICTIONARY.put("TypeMismatchException", ErrorCodes.TYPE_MISMATCH_EXCEPTION);
        EXCEPTION_DICTIONARY.put("MissingServletRequestParameterException", ErrorCodes.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION);
        EXCEPTION_DICTIONARY.put("ProviderNotFoundException", ErrorCodes.PROVIDER_NOT_FOUND);
        EXCEPTION_DICTIONARY.put("CookieTheftException", ErrorCodes.COOKIE_THEFT);
        EXCEPTION_DICTIONARY.put("InvalidCookieException", ErrorCodes.INVALID_COOKIE);
        EXCEPTION_DICTIONARY.put("BadSqlGrammarException", ErrorCodes.BAD_SQL_GRAMMAR);
        EXCEPTION_DICTIONARY.put("DataIntegrityViolationException", ErrorCodes.DATA_INTEGRITY_VIOLATION);
        EXCEPTION_DICTIONARY.put("TransactionRollbackException", ErrorCodes.TRANSACTION_ROLLBACK);
        EXCEPTION_DICTIONARY.put("BindException", ErrorCodes.METHOD_ARGUMENT_NOT_VALID);
        EXCEPTION_DICTIONARY.put("MethodArgumentNotValidException", ErrorCodes.METHOD_ARGUMENT_NOT_VALID);
        EXCEPTION_DICTIONARY.put("RedisPipelineException", ErrorCodes.PIPELINE_INVALID_COMMANDS);
    }

    public static Result<String> resolveException(Exception ex, String path) {

        log.trace("[GstDev Cloud] |- Global Exception Handler, Path : [{}], Exception：", path, ex);

        if (ex instanceof FrameException exception) {
            Result<String> result = exception.getResult();
            result.path(path);
            log.error("[GstDev Cloud] |- Global Exception Handler, Error is : {}", result);
            return result;
        } else {
            Result<String> result = Result.failure();
            String exceptionName = ex.getClass().getSimpleName();
            if (StringUtils.isNotEmpty(exceptionName) && EXCEPTION_DICTIONARY.containsKey(exceptionName)) {
                Feedback feedback = EXCEPTION_DICTIONARY.get(exceptionName);
                result = Result.failure(feedback, exceptionName);
            } else {
                log.warn("[GstDev Cloud] |- Global Exception Handler,  Can not find the exception name [{}] in dictionary, please do optimize ", exceptionName);
            }

            result.path(path);
            result.stackTrace(ex.getStackTrace());
            result.detail(ex.getMessage());

            log.error("[GstDev Cloud] |- Global Exception Handler, Error is : {}", result);
            return result;
        }
    }

//  @ExceptionHandler(NoHandlerFoundException.class)
//  public ResponseEntity<Object> NoHandlerFoundExceptionHandler(NoHandlerFoundException ex) {
//    ErrorResponse errorResponse = new ErrorResponse();
//    errorResponse.setMessage(ex.getMessage());
//
//    return buildResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
//  }
//
//  @ExceptionHandler({MethodArgumentNotValidException.class})
//  public ResponseEntity<Object> validationMethodArgumentException(MethodArgumentNotValidException ex, HttpServletRequest request, HttpServletResponse response) {
//    ErrorResponse errorResponse = new ErrorResponse();
//    errorResponse.setMessage("验证不通过");
//
//    BindingResult bindingResult = ex.getBindingResult();
//    List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
//    if (!fieldErrorList.isEmpty()) {
//      List<Error> errors = new ArrayList<Error>();
//      fieldErrorList.forEach(fieldError -> {
//        Error err = new Error();
//        err.setResource(fieldError.getObjectName());
//        err.setField(fieldError.getField());
//        err.setCode(fieldError.getCode());
//        err.setMessage(fieldError.getDefaultMessage());
//
//        errors.add(err);
//      });
//
//      errorResponse.setErrors(errors);
//    }
//
//    return buildResponseEntity(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
//  }
//
//  @ExceptionHandler(Throwable.class)
//  public ResponseEntity<Object> handleException(Throwable ex, HttpServletRequest request, HttpServletResponse response) {
//
//    ErrorResponse errorResponse = new ErrorResponse();
//    errorResponse.setMessage(ex.getMessage());
//
//    return buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
//  }
//
//  private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse, HttpStatus status) {
//    return new ResponseEntity<>(errorResponse, status);
//  }
}
