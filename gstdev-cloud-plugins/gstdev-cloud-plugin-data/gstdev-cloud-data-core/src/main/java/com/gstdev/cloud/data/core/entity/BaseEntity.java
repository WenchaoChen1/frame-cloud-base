package com.gstdev.cloud.data.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import com.gstdev.cloud.base.definition.domain.base.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

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
    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private Instant createdAt;
    @CreatedBy
    @JsonIgnore
    @Column(name = "created_by", length = 36, updatable = false)
    private String createdBy;

    @JsonIgnore
    @LastModifiedDate
    @JsonSerialize(using = InstantSerializer.class)
    @Column(name = "updated_at")
    private Instant updatedAt;
    @LastModifiedBy
    @JsonIgnore
    @Column(name = "updated_by", length = 36)
    private String updatedBy;


//    @PrePersist
//    public void addAuditInfo() {
//        createdAt = Instant.now();
////    createdBy = SecurityUtils.getUserId();
//        updatedAt = Instant.now();
////    updatedBy = SecurityUtils.getUserId();
//    }
//
//    @PreUpdate
//    public void updateAuditInfo() {
//        updatedAt = Instant.now();
////    updatedBy = SecurityUtils.getUserId();
//    }

//    public ID getId() {
//        return null;
//    }
    //  @Schema(title = "数据创建时间")
//  @Column(name = "create_time", updatable = false)
//  @CreatedDate
//  @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT)
//  private Date createTime = new Date();
//
//  @Schema(title = "数据更新时间")
//  @Column(name = "update_time")
//  @LastModifiedDate
//  @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT)
//  private Date updateTime = new Date();
//
//  @Schema(title = "创建人")
//  @Column(name = "create_by")
//  @CreatedBy
//  private String createBy;
//
//  @Schema(title = "最后修改")
//  @Column(name = "update_by")
//  @LastModifiedBy
//  private String updateBy;
//
//  @Schema(title = "排序值")
//  @Column(name = "ranking")
//  private Integer ranking = 0;
//
//  public Date getCreateTime() {
//    return createTime;
//  }

}
