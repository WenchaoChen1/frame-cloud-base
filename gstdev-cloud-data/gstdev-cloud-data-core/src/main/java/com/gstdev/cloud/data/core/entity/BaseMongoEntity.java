package com.gstdev.cloud.data.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.commons.constant.DefaultConstants;
import com.gstdev.cloud.commons.domain.base.AbstractEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

/**
 * <p>Description: MongoDB 基础实体 </p>
 *
 * @author : cc
 * @date : 2023/2/26 20:30
 */
public abstract class BaseMongoEntity extends AbstractEntity {
    @Schema(title = "数据创建时间")
    @Column(name = "create_time", updatable = false)
    @CreatedDate
    @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT)
    private Date createTime = new Date();
    @Schema(title = "数据更新时间")
    @Column(name = "update_time")
    @LastModifiedDate
    @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT)
    private Date updateTime = new Date();

    public abstract String getId();

    public abstract void setId(String id);

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
}
