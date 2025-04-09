// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.domain;

import com.gstdev.cloud.plugin.storage.core.exception.*;
import com.gstdev.cloud.plugin.storage.minio.domain.DeleteErrorDomain;
import com.gstdev.cloud.plugin.storage.specification.domain.object.DeleteObjectDomain;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.DeleteError;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.io.IOException;
import java.net.ConnectException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Description: Result<DeleteError> 转 DeleteErrorEntity 转换器 </p>
 *
 * @author : cc
 * @date : 2023/5/30 22:34
 */
public class ResultDeleteErrorToDomainConverter implements Converter<Result<DeleteError>, DeleteObjectDomain> {

    private static final Logger log = LoggerFactory.getLogger(ResultDeleteErrorToDomainConverter.class);

    @Override
    public DeleteObjectDomain convert(Result<DeleteError> result) {
        String function = "converter";

        try {
            DeleteError deleteError = result.get();

            DeleteErrorDomain domain = new DeleteErrorDomain();
            if (ObjectUtils.isNotEmpty(deleteError)) {
                domain.setCode(deleteError.code());
                domain.setMessage(deleteError.message());
                domain.setBucketName(deleteError.bucketName());
                domain.setObjectName(deleteError.objectName());
                domain.setResource(deleteError.resource());
                domain.setRequestId(deleteError.requestId());
                domain.setHostId(deleteError.hostId());
            }
            return domain;
        } catch (ErrorResponseException e) {
            log.error("[GstDev Cloud] |- Minio catch ErrorResponseException in [{}].", function, e);
            throw new OssErrorResponseException(e.getMessage());
        } catch (InsufficientDataException e) {
            log.error("[GstDev Cloud] |- Minio catch InsufficientDataException in [{}].", function, e);
            throw new OssInsufficientDataException(e.getMessage());
        } catch (InternalException e) {
            log.error("[GstDev Cloud] |- Minio catch InternalException in [{}].", function, e);
            throw new OssInternalException(e.getMessage());
        } catch (InvalidKeyException e) {
            log.error("[GstDev Cloud] |- Minio catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException(e.getMessage());
        } catch (InvalidResponseException e) {
            log.error("[GstDev Cloud] |- Minio catch InvalidResponseException in [{}].", function, e);
            throw new OssInvalidResponseException(e.getMessage());
        } catch (IOException e) {
            log.error("[GstDev Cloud] |- Minio catch IOException in [{}].", function, e);
            if (e instanceof ConnectException) {
                throw new OssConnectException(e.getMessage());
            } else {
                throw new OssIOException(e.getMessage());
            }
        } catch (NoSuchAlgorithmException e) {
            log.error("[GstDev Cloud] |- Minio catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException(e.getMessage());
        } catch (ServerException e) {
            log.error("[GstDev Cloud] |- Minio catch ServerException in [{}].", function, e);
            throw new OssServerException(e.getMessage());
        } catch (XmlParserException e) {
            log.error("[GstDev Cloud] |- Minio catch XmlParserException in [{}].", function, e);
            throw new OssXmlParserException(e.getMessage());
        }
    }
}
