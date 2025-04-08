// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.converter.domain;

import com.gstdev.cloud.base.core.utils.type.DateTimeUtils;
import com.gstdev.cloud.plugin.storage.core.exception.*;
import com.gstdev.cloud.plugin.storage.specification.domain.base.OwnerDomain;
import com.gstdev.cloud.plugin.storage.specification.domain.object.ObjectDomain;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import io.minio.messages.Owner;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.io.IOException;
import java.net.ConnectException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Description: Minio Item 转 ObjectDomain 转换器 </p>
 *
 * @author : cc
 * @date : 2023/8/9 23:43
 */
public class ResultItemToDomainConverter implements Converter<Result<Item>, ObjectDomain> {

    private static final Logger log = LoggerFactory.getLogger(ResultItemToDomainConverter.class);

    private final String bucketName;

    public ResultItemToDomainConverter(String bucketName) {
        this.bucketName = bucketName;
    }

    @Override
    public ObjectDomain convert(Result<Item> result) {
        String function = "convert";

        try {
            Item item = result.get();
            ObjectDomain objectDomain = new ObjectDomain();
            objectDomain.setBucketName(bucketName);
            objectDomain.setObjectName(item.objectName());
            objectDomain.setDir(item.isDir());
            if (!item.isDir()) {
                objectDomain.setETag(item.etag());
                objectDomain.setLastModified(DateTimeUtils.zonedDateTimeToDate(item.lastModified()));
                if (ObjectUtils.isNotEmpty(item.owner())) {
                    Converter<Owner, OwnerDomain> toAttr = new OwnerToDomainConverter();
                    objectDomain.setOwner(toAttr.convert(item.owner()));
                }
                objectDomain.setSize(item.size());
                objectDomain.setStorageClass(item.storageClass());

            }
            return objectDomain;
        } catch (ErrorResponseException e) {
            log.error("[Herodotus] |- Minio catch ErrorResponseException in [{}].", function, e);
            throw new OssErrorResponseException(e.getMessage());
        } catch (InsufficientDataException e) {
            log.error("[Herodotus] |- Minio catch InsufficientDataException in [{}].", function, e);
            throw new OssInsufficientDataException(e.getMessage());
        } catch (InternalException e) {
            log.error("[Herodotus] |- Minio catch InternalException in [{}].", function, e);
            throw new OssInternalException(e.getMessage());
        } catch (InvalidKeyException e) {
            log.error("[Herodotus] |- Minio catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException(e.getMessage());
        } catch (InvalidResponseException e) {
            log.error("[Herodotus] |- Minio catch InvalidResponseException in [{}].", function, e);
            throw new OssInvalidResponseException(e.getMessage());
        } catch (IOException e) {
            log.error("[Herodotus] |- Minio catch IOException in [{}].", function, e);
            if (e instanceof ConnectException) {
                throw new OssConnectException(e.getMessage());
            } else {
                throw new OssIOException(e.getMessage());
            }
        } catch (NoSuchAlgorithmException e) {
            log.error("[Herodotus] |- Minio catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException(e.getMessage());
        } catch (ServerException e) {
            log.error("[Herodotus] |- Minio catch ServerException in [{}].", function, e);
            throw new OssServerException(e.getMessage());
        } catch (XmlParserException e) {
            log.error("[Herodotus] |- Minio catch XmlParserException in [{}].", function, e);
            throw new OssXmlParserException(e.getMessage());
        }
    }
}
