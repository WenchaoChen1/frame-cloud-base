package com.gstdev.cloud.data.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.MoreObjects;
import com.gstdev.cloud.commons.ass.definition.constants.DefaultConstants;
import com.gstdev.cloud.commons.ass.definition.domain.base.AbstractEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * <p> Description : 实体通用属性 </p>
 *
 * @author : cc
 * @date : 2020/4/29 16:09
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity extends AbstractEntity {

    @Schema(name = "数据创建时间")
    @Column(name = "create_time", updatable = false)
    @CreatedDate
    @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT)
    private Date createTime = new Date();

    @Schema(name = "数据更新时间")
    @Column(name = "update_time")
    @LastModifiedDate
    @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT)
    private Date updateTime = new Date();

    @Schema(name = "创建人")
    @Column(name = "create_by")
    @CreatedBy
    private String createBy;

    @Schema(name = "最后修改")
    @Column(name = "update_by")
    @LastModifiedBy
    private String updateBy;

    @Schema(name = "排序值")
    @Column(name = "ranking")
    private Integer ranking = 0;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .add("createBy", createBy)
                .add("updateBy", updateBy)
                .add("ranking", ranking)
                .toString();
    }
}
