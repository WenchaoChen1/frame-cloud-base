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

import java.util.List;

/**
 * <p>Description: Minio 策略 StatementDomain </p>
 *
 * @author : cc
 * @date : 2023/6/7 17:37
 */
public class StatementDomain implements Entity {

    @JsonProperty("Effect")
    private String effect = "Allow";

    @JsonProperty("Action")
    private List<String> actions;

    @JsonProperty("Resource")
    private List<String> resources;

    @JsonProperty("Principal")
    private PrincipalDomain principal = new PrincipalDomain();

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public PrincipalDomain getPrincipal() {
        return principal;
    }

    public void setPrincipal(PrincipalDomain principalDomain) {
        this.principal = principalDomain;
    }
}
