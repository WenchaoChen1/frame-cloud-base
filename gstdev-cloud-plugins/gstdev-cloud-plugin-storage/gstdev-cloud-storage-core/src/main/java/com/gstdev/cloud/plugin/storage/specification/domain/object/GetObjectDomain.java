// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.domain.object;


import com.gstdev.cloud.plugin.storage.specification.domain.base.BaseDomain;

import java.io.InputStream;

/**
 * <p>Description: 获取对象返回结果域对象 </p>
 *
 * @author : cc
 * @date : 2023/8/15 14:14
 */
public class GetObjectDomain extends BaseDomain {

    private InputStream objectContent;

    public InputStream getObjectContent() {
        return objectContent;
    }

    public void setObjectContent(InputStream objectContent) {
        this.objectContent = objectContent;
    }
}
