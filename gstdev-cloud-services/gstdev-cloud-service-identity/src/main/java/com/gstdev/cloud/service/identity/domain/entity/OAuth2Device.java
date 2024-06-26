package com.gstdev.cloud.service.identity.domain.entity;

import com.google.common.base.MoreObjects;
import com.gstdev.cloud.oauth2.core.constants.OAuth2Constants;
import com.gstdev.cloud.service.identity.domain.definition.AbstractOAuth2RegisteredClient;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: 物联网设备管理 </p>
 *
 * @author : cc
 * @date : 2023/5/15 14:26
 */
@Schema(title = "物联网设备")
@Entity
@Table(name = "oauth2_device",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"device_name"})},
        indexes = {@Index(name = "oauth2_device_id_idx", columnList = "device_id"),
                @Index(name = "oauth2_device_ipk_idx", columnList = "device_name"),
                @Index(name = "oauth2_device_pid_idx", columnList = "product_id")
        })
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = OAuth2Constants.REGION_OAUTH2_DEVICE)
public class OAuth2Device extends AbstractOAuth2RegisteredClient {

    @Schema(title = "设备ID")
    @Id
    @UuidGenerator
    @Column(name = "device_id", length = 64)
    private String deviceId;

    @Schema(title = "设备名称")
    @Column(name = "device_name", length = 64, unique = true)
    private String deviceName;

    @Schema(title = "产品ID")
    @Column(name = "product_id", length = 64)
    private String productId;

    @Schema(title = "是否已激活", description = "设备是否已经激活状态标记，默认值false，即未激活")
    @Column(name = "is_activated")
    private Boolean activated = Boolean.FALSE;

    @Schema(title = "设备对应Scope", description = "传递设备对应Scope ID数组")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = OAuth2Constants.REGION_OAUTH2_APPLICATION_SCOPE)
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "oauth2_device_scope",
            joinColumns = {@JoinColumn(name = "device_id")},
            inverseJoinColumns = {@JoinColumn(name = "scope_id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"device_id", "scope_id"})},
            indexes = {@Index(name = "oauth2_device_scope_aid_idx", columnList = "device_id"), @Index(name = "oauth2_device_scope_sid_idx", columnList = "scope_id")})
    private Set<OAuth2Scope> scopes = new HashSet<>();

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    @Override
    public Set<OAuth2Scope> getScopes() {
        return scopes;
    }

    public void setScopes(Set<OAuth2Scope> scopes) {
        this.scopes = scopes;
    }

    @Override
    public String getId() {
        return getDeviceId();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("deviceId", deviceId)
                .add("deviceName", deviceName)
                .add("productId", productId)
                .add("activated", activated)
                .toString();
    }
}
