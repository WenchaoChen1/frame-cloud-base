// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.domain.object;

import com.gstdev.cloud.plugin.storage.specification.domain.bucket.BucketDomain;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

/**
 * <p>Description: 对象 </p>
 *
 * @author : cc
 * @date : 2023/7/28 22:46
 */
@Schema(name = "对象")
public class ObjectDomain extends BucketDomain {

    /**
     * 存储此对象的密钥
     */
    @Schema(name = "存储此对象的密钥")
    private String objectName;
    /**
     * ETag。此对象内容的十六进制编码MD5哈希
     */
    @Schema(name = "ETag", description = "此对象内容的十六进制编码MD5哈希")
    private String eTag;
    /**
     * 此对象的大小，以字节为单位
     */
    @Schema(name = "对象大小", description = "以字节为单位")
    private Long size;
    /**
     * 对象最后一次被修改的日期
     */
    @Schema(name = "对象最后一次被修改的日期")
    private Date lastModified;
    /**
     * 存储此对象的存储类
     */
    @Schema(name = "存储此对象的存储类")
    private String storageClass;

    @Schema(name = "是否为文件夹")
    private Boolean isDir;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getETag() {
        return eTag;
    }

    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getStorageClass() {
        return storageClass;
    }

    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    public Boolean getDir() {
        return isDir;
    }

    public void setDir(Boolean dir) {
        isDir = dir;
    }
}
