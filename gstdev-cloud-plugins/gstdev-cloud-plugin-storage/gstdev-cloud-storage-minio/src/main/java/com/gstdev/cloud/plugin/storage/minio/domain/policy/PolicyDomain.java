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
import com.gstdev.cloud.base.definition.domain.base.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: Minio 访问策略实体 </p>
 *
 * @author : cc
 * @date : 2023/6/7 17:42
 */
public class PolicyDomain implements Entity {

    @JsonProperty("Version")
    private String version = "2012-10-17";

    @JsonProperty("Statement")
    private List<StatementDomain> statements = new ArrayList<>();

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<StatementDomain> getStatements() {
        return statements;
    }

    public void setStatements(List<StatementDomain> statements) {
        this.statements = statements;
    }
}
