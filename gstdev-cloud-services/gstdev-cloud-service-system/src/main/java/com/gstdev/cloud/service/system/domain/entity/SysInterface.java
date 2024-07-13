package com.gstdev.cloud.service.system.domain.entity;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.generator.SysInterfaceUuidGenerator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: 系统应用程序接口实体 </p>
 */
@Schema(title = "系统应用接口")
@Entity
@Getter
@Setter
@Table(name = "sys_interface", indexes = {@Index(name = "sys_interface_id_idx", columnList = "interface_id")})
public class SysInterface extends BaseEntity {

    @Schema(title = "接口ID")
    @Id
    @SysInterfaceUuidGenerator
    @Column(name = "interface_id", length = 64)
    private String interfaceId;

    @Schema(title = "接口代码")
    @Column(name = "interface_code", length = 128)
    private String interfaceCode;

    @Schema(title = "请求方法")
    @Column(name = "request_method", length = 20)
    private String requestMethod;

    @Schema(title = "服务ID")
    @Column(name = "service_id", length = 128)
    private String serviceId;

    @Schema(title = "接口所在类")
    @Column(name = "class_name", length = 512)
    private String className;

    @Schema(title = "接口对应方法")
    @Column(name = "method_name", length = 128)
    private String methodName;

    @Schema(title = "请求URL")
    @Column(name = "url", length = 2048)
    private String url;

    /**
     * 角色描述,UI界面显示使用
     */
    @Schema(title = "备注")
    @Column(name = "description", length = 512)
    private String description;
    @Schema(title = "数据状态")
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DataItemStatus status = DataItemStatus.ENABLE;
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysInterface that = (SysInterface) o;
        return Objects.equal(interfaceId, that.interfaceId) && Objects.equal(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(interfaceId, serviceId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("interfaceId", interfaceId)
                .add("interfaceCode", interfaceCode)
                .add("requestMethod", requestMethod)
                .add("serviceId", serviceId)
                .add("className", className)
                .add("methodName", methodName)
                .add("url", url)
                .add("status", status)
                .toString();
    }
}
