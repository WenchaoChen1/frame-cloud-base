// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.core.exception;


import com.gstdev.cloud.base.definition.domain.Feedback;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.plugin.storage.core.constants.OssErrorCodes;

/**
 * <p>Description: OssXmlParserException </p>
 *
 * @author : cc
 * @date : 2021/11/8 14:36
 */
public class OssXmlParserException extends PlatformRuntimeException {

    public OssXmlParserException() {
        super();
    }

    public OssXmlParserException(String message) {
        super(message);
    }

    public OssXmlParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssXmlParserException(Throwable cause) {
        super(cause);
    }

    protected OssXmlParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return OssErrorCodes.OSS_XML_PARSER;
    }
}
