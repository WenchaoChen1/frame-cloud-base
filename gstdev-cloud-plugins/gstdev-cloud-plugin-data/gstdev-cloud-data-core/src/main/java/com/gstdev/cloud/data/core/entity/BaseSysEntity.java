package com.gstdev.cloud.data.core.entity;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * <p>Description: 框架基础权限实体通用基础类</p>
 *
 * @author : cc
 * @date : 2019/11/25 15:05
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseSysEntity extends BaseEntity {

    @Schema(title = "数据状态")
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private DataItemStatus status = DataItemStatus.ENABLE;

    @Schema(title = "是否为保留数据", description = "True 为不能删，False为可以删除")
    @Column(name = "is_reserved")
    private Boolean reserved = Boolean.FALSE;

    @Schema(title = "版本号")
    @Column(name = "reversion")
    private Integer reversion = 0;

    /**
     * 角色描述,UI界面显示使用
     */
    @Schema(title = "备注")
    @Column(name = "description", length = 512)
    private String description;

    public DataItemStatus getStatus() {
        return status;
    }

    public void setStatus(DataItemStatus status) {
        this.status = status;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReversion() {
        return reversion;
    }

    public void setReversion(Integer reversion) {
        this.reversion = reversion;
    }
}
