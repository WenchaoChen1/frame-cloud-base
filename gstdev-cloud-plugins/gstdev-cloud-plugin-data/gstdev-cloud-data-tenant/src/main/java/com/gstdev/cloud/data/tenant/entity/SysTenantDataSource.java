package com.gstdev.cloud.data.tenant.entity;

import com.google.common.base.MoreObjects;
import com.gstdev.cloud.data.core.constants.DataConstants;
import com.gstdev.cloud.data.core.entity.BaseSysEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;

/**
 * <p>Description: 租户数据源管理 </p>
 *
 * @author : cc
 * @date : 2023/3/28 21:45
 */
@Schema(title = "多租户数据源管理")
@Entity
@Table(name = "sys_tenant_datasource",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"tenant_id"})},
        indexes = {@Index(name = "sys_tenant_datasource_id_idx", columnList = "datasource_id")})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = DataConstants.REGION_SYS_TENANT_DATASOURCE)
public class SysTenantDataSource extends BaseSysEntity {

    @Schema(title = "租户数据源主键")
    @Id
    @UuidGenerator
    @Column(name = "datasource_id", length = 64)
    private String datasourceId;

    @Schema(title = "租户ID", description = "租户的唯一标识")
    @Column(name = "tenant_id", length = 64, unique = true)
    private String tenantId;

    @Schema(title = "数据库用户名")
    @Column(name = "user_name", length = 100)
    private String username;

    @Schema(title = "数据库密码")
    @Column(name = "password", length = 100)
    private String password;

    @Schema(title = "数据库驱动")
    @Column(name = "driver_class_name", length = 64)
    private String driverClassName;

    @Schema(title = "数据库连接")
    @Column(name = "url", length = 1000)
    private String url;

    @Schema(title = "是否已经初始化", description = "默认值 false")
    private Boolean initialize = false;

    public String getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getInitialize() {
        return initialize;
    }

    public void setInitialize(Boolean initialize) {
        this.initialize = initialize;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("datasourceId", datasourceId)
                .add("tenantId", tenantId)
                .add("username", username)
                .add("password", password)
                .add("driverClassName", driverClassName)
                .add("url", url)
                .add("initialize", initialize)
                .toString();
    }
}
