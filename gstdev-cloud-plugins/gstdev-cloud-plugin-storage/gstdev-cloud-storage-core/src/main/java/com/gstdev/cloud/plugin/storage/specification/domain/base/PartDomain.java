// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.domain.base;

import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

/**
 * <p>Description: 基础分片域对象 </p>
 *
 * @author : cc
 * @date : 2023/8/13 16:38
 */
public class PartDomain extends BasePartDomain {

    /**
     * 此分片的大小，以字节为单位
     */
    @Schema(name = "分片数据大小", description = "单位为字节")
    private long partSize;

    @Schema(name = "新对象的上次修改日期")
    private Date lastModifiedDate;

    public long getPartSize() {
        return partSize;
    }

    public void setPartSize(long partSize) {
        this.partSize = partSize;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("lastModifiedDate", lastModifiedDate)
                .toString();
    }
}
