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
    private Instant createdAt;
    @JsonIgnore
    @Column(name = "created_by", length = 36, updatable = false)
    private String createdBy;

    @JsonIgnore
    @JsonSerialize(using = InstantSerializer.class)
    @Column(name = "updated_at")
    private Instant updatedAt;
    @JsonIgnore
    @Column(name = "updated_by", length = 36)
    private String updatedBy;


    @PrePersist
    public void addAuditInfo() {
//        createdAt = Instant.now();
//    createdBy = SecurityUtils.getUserId();
//        updatedAt = Instant.now();
//    updatedBy = SecurityUtils.getUserId();
    }

    @PreUpdate
    public void updateAuditInfo() {
        updatedAt = Instant.now();
//    updatedBy = SecurityUtils.getUserId();
    }

//    public ID getId() {
//        return null;
//    }
    //  @Schema(name = "数据创建时间")
//  @Column(name = "create_time", updatable = false)
//  @CreatedDate
//  @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT)
//  private Date createTime = new Date();
//
//  @Schema(name = "数据更新时间")
//  @Column(name = "update_time")
//  @LastModifiedDate
//  @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT)
//  private Date updateTime = new Date();
//
//  @Schema(name = "创建人")
//  @Column(name = "create_by")
//  @CreatedBy
//  private String createBy;
//
//  @Schema(name = "最后修改")
//  @Column(name = "update_by")
//  @LastModifiedBy
//  private String updateBy;
//
//  @Schema(name = "排序值")
//  @Column(name = "ranking")
//  private Integer ranking = 0;
//
//  public Date getCreateTime() {
//    return createTime;
//  }
//
//  public void setCreateTime(Date createTime) {
//    this.createTime = createTime;
//  }
//
//  public Date getUpdateTime() {
//    return updateTime;
//  }
//
//  public void setUpdateTime(Date updateTime) {
//    this.updateTime = updateTime;
//  }
//
//  public Integer getRanking() {
//    return ranking;
//  }
//
//  public void setRanking(Integer ranking) {
//    this.ranking = ranking;
//  }
//
//  public String getCreateBy() {
//    return createBy;
//  }
//
//  public void setCreateBy(String createBy) {
//    this.createBy = createBy;
//  }
//
//  public String getUpdateBy() {
//    return updateBy;
//  }
//
//  public void setUpdateBy(String updateBy) {
//    this.updateBy = updateBy;
//  }
//
//  @Override
//  public String toString() {
//    return MoreObjects.toStringHelper(this)
//      .add("createTime", createTime)
//      .add("updateTime", updateTime)
//      .add("createBy", createBy)
//      .add("updateBy", updateBy)
//      .add("ranking", ranking)
//      .toString();
//  }
}
