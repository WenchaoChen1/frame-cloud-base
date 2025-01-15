//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev Cloud <support@gstdev.com>
//// Copyright (c) 2022-2025 gstdev.com
////
//// ====================================================
//
//package com.gstdev.cloud.data.core.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
//import com.gstdev.cloud.base.definition.domain.base.AbstractEntity;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.time.Instant;
//
//@EqualsAndHashCode(callSuper = false)
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
//public abstract class AbstractCustomEntity extends AbstractEntity {
//
//    @JsonSerialize(using = InstantSerializer.class)
//    @Column(name = "created_at", updatable = false)
//    private Instant createdAt;
//    @JsonIgnore
//    @Column(name = "created_by", length = 36, updatable = false)
//    private String createdBy;
//
//    @JsonIgnore
//    @JsonSerialize(using = InstantSerializer.class)
//    @Column(name = "updated_at")
//    private Instant updatedAt;
//    @JsonIgnore
//    @Column(name = "updated_by", length = 36)
//    private String updatedBy;
//
//}
