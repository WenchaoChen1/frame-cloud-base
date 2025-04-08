// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.domain.multipart;


import com.gstdev.cloud.plugin.storage.specification.domain.base.MultipartUploadDomain;
import com.gstdev.cloud.plugin.storage.specification.domain.base.OwnerDomain;

import java.util.List;

/**
 * <p>Description: 分片列表返回域对象 </p>
 *
 * @author : cc
 * @date : 2023/8/12 23:06
 */
public class ListPartsDomain extends MultipartUploadDomain {

    private OwnerDomain owner;

    private OwnerDomain initiator;

    private String storageClass;

    private Integer maxParts;

    private Integer partNumberMarker;

    private Integer nextPartNumberMarker;

    private Boolean isTruncated;

    private List<PartSummaryDomain> parts;

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

    public Integer getMaxParts() {
        return maxParts;
    }

    public void setMaxParts(Integer maxParts) {
        this.maxParts = maxParts;
    }

    public Integer getPartNumberMarker() {
        return partNumberMarker;
    }

    public void setPartNumberMarker(Integer partNumberMarker) {
        this.partNumberMarker = partNumberMarker;
    }

    public Integer getNextPartNumberMarker() {
        return nextPartNumberMarker;
    }

    public void setNextPartNumberMarker(Integer nextPartNumberMarker) {
        this.nextPartNumberMarker = nextPartNumberMarker;
    }

    public Boolean getTruncated() {
        return isTruncated;
    }

    public void setTruncated(Boolean truncated) {
        isTruncated = truncated;
    }

    public List<PartSummaryDomain> getParts() {
        return parts;
    }

    public void setParts(List<PartSummaryDomain> parts) {
        this.parts = parts;
    }
}
