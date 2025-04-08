// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.arguments.object;


import com.gstdev.cloud.plugin.storage.specification.arguments.base.PutObjectBaseArguments;

import java.io.InputStream;

/**
 * <p>Description: 上传对象请求参数实体 </p>
 *
 * @author : cc
 * @date : 2023/8/15 15:19
 */
public class PutObjectArguments extends PutObjectBaseArguments {

    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
