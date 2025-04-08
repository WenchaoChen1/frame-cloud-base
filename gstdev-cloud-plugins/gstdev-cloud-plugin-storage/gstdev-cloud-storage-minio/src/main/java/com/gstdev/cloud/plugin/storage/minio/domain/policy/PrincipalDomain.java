// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.domain.policy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.gstdev.cloud.base.definition.domain.base.Entity;

import java.util.List;

/**
 * <p>Description: Minio 策略 PrincipalDomain  </p>
 *
 * @author : cc
 * @date : 2023/6/7 17:33
 */
public class PrincipalDomain implements Entity {

    @JsonProperty("AWS")
    private List<String> aws = Lists.newArrayList("*");

    public List<String> getAws() {
        return aws;
    }

    public void setAws(List<String> aws) {
        this.aws = aws;
    }
}
