// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gstdev.cloud.data.core.entity.BaseTreeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Getter
@Setter
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
@Table(name = "sys_dict", schema = "public")
public class SysDict extends BaseTreeEntity {
    @Column(name = "name", length = 64, nullable = false)
    private String name;

//  @Column(name = "parent_id", length = 36)
//  private String parentId;

    @Column(name = "code", length = 50, nullable = false)
    private String code;

    @Column(name = "status", nullable = false)
    private Integer status = 1;

    @Column(name = "sort", length = 6)
    private Integer sort;

    @Column(name = "description")
    private String description;

    @Column(name = "deleted", nullable = false)
    private Integer deleted = 0;

    @JsonIgnore
    @OneToMany(mappedBy = "dict", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<TenantDict> tenantDicts;
}
