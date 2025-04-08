// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.minio.domain;

import com.gstdev.cloud.base.definition.domain.base.Entity;
import io.minio.admin.Status;

import java.util.List;

/**
 * <p>Description: Minio Group Domain </p>
 *
 * @author : cc
 * @date : 2023/6/25 15:24
 */
public class GroupDomain implements Entity {

    private String name;

    private Status status;

    private List<String> members;

    private String policy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }
}
