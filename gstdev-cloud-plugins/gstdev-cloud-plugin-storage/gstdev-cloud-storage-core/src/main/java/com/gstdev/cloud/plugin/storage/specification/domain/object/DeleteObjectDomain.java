// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.domain.object;


import com.gstdev.cloud.plugin.storage.specification.arguments.object.DeletedObjectArguments;

/**
 * <p>Description: 批量删除对象中的结果对象 </p>
 *
 * @author : cc
 * @date : 2023/8/12 13:49
 */
public class DeleteObjectDomain extends DeletedObjectArguments {

    public DeleteObjectDomain() {
        super();
    }

    public DeleteObjectDomain(String objectName) {
        super(objectName);
    }
}
