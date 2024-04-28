// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.core.model;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class FileBucket implements Serializable {

    private static final long serialVersionUID = -2444518301828409607L;

    private String name;

    private String location;
}
