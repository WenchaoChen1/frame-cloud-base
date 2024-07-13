// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.entity;


import com.gstdev.cloud.data.core.entity.BaseTreeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "sys_depart", schema = "public")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
public class SysDepart extends BaseTreeEntity {

    @Column(name = "tenant_id", length = 50, nullable = false)
    private String tenantId;

//  @Column(name = "parent_id", length = 36)
//  private String parentId;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "code", length = 50, nullable = false)
    private String code;

    @Column(name = "short_name", length = 100)
    private String shortName;

    @Column(name = "status")
    private Integer status = 1;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "description", length = 1024)
    private String description;

    @Column(name = "deleted")
    private Integer deleted = 0;


    @ManyToMany
    @JoinTable(name = "sys_r_account_depart", joinColumns = {
            @JoinColumn(name = "depart_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "account_id", referencedColumnName = "account_id")})
    private List<SysAccount> accounts;
}



