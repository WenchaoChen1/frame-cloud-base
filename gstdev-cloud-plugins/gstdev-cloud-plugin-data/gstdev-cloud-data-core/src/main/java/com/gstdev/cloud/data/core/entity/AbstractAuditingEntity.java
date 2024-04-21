// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.data.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fieldHandler"})
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity extends AbstractCustomEntity {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 36)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
