// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.domain.multipart;

import com.gstdev.cloud.plugin.storage.specification.core.domain.OssDomain;
import com.gstdev.cloud.plugin.storage.specification.domain.base.OwnerDomain;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

/**
 * <p>Description: 分片上传列表返回条目域对象 </p>
 *
 * @author : cc
 * @date : 2023/8/13 20:53
 */
@Schema(name = "分片上传列表返回条目域对象", title = "分片上传列表返回条目域对象")
public class UploadDomain implements OssDomain {

    /**
     * 存储此upload的密钥
     */
    @Schema(name = "对象标记")
    private String key;

    /**
     * 此分片上传的唯一ID
     */
    @Schema(name = "上传ID")
    private String uploadId;

    /**
     * 此分片上传的拥有者
     */
    @Schema(name = "分片上传的拥有者")
    private OwnerDomain owner;

    /**
     * 此分片上传的发起者
     */
    @Schema(name = "分片上传的发起者")
    private OwnerDomain initiator;

    /**
     * 存储类，指示如何存储此分片上传中的数据.
     */
    @Schema(name = "存储类", description = "指示如何存储此分片上传中的数据")
    private String storageClass;

    /**
     * 启动此分片上传的时间
     */
    @Schema(name = "启动此分片上传的时间")
    private Date initiated;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public OwnerDomain getOwner() {
        return owner;
    }

    public void setOwner(OwnerDomain owner) {
        this.owner = owner;
    }

    public OwnerDomain getInitiator() {
        return initiator;
    }

    public void setInitiator(OwnerDomain initiator) {
        this.initiator = initiator;
    }

    public String getStorageClass() {
        return storageClass;
    }

    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    public Date getInitiated() {
        return initiated;
    }

    public void setInitiated(Date initiated) {
        this.initiated = initiated;
    }
}
