package com.gstdev.cloud.data.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import com.gstdev.cloud.base.core.utils.SecurityUserDetailUtils;
import com.gstdev.cloud.base.definition.domain.base.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Objects;

/**
 * <p> Description : 实体通用属性 </p>
 *
 * @author : cc
 * @date : 2020/4/29 16:09
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity extends AbstractEntity {


    @JsonSerialize(using = InstantSerializer.class)
    @Column(name = "z_created_date")
    @CreatedDate
    private Instant createdDate;
    @CreatedBy
    @JsonIgnore
//    @Column(name = "z_created_user", length = 64, updatable = false)
    @Column(name = "z_created_user", length = 64)
    private String createdUser;
    //    @CreatedBy
    @JsonIgnore
    @Column(name = "z_created_account", length = 64)
    private String createdAccount;

    @JsonIgnore
    @LastModifiedDate
    @JsonSerialize(using = InstantSerializer.class)
    @Column(name = "z_updated_date")
    private Instant updatedDate;
//    @LastModifiedBy
    @JsonIgnore
    @Column(name = "z_updated_user", length = 64)
    private String updatedUser;
    @JsonIgnore
    @Column(name = "z_updated_account", length = 64)
    private String updatedAccount;


    @PrePersist
    public void addAuditInfo() {
        createdDate = Instant.now();
        createdUser = SecurityUserDetailUtils.getUserId();
        createdAccount = SecurityUserDetailUtils.getAccountId();
        updatedDate = Instant.now();
        updatedUser = SecurityUserDetailUtils.getUserId();
        updatedAccount = SecurityUserDetailUtils.getAccountId();
    }

    @PreUpdate
    public void updateAuditInfo() {
        updatedDate = Instant.now();
        updatedUser = SecurityUserDetailUtils.getUserId();
        updatedAccount = SecurityUserDetailUtils.getAccountId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(createdDate, that.createdDate) && Objects.equals(createdUser, that.createdUser) && Objects.equals(createdAccount, that.createdAccount) && Objects.equals(updatedDate, that.updatedDate) && Objects.equals(updatedUser, that.updatedUser) && Objects.equals(updatedAccount, that.updatedAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdDate, createdUser, createdAccount, updatedDate, updatedUser, updatedAccount);
    }
}
