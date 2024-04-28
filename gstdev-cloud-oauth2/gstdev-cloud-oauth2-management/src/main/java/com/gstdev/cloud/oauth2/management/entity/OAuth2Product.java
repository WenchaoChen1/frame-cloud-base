package com.gstdev.cloud.oauth2.management.entity;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.oauth2.core.constants.OAuth2Constants;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;

/**
 * <p>Description: 物联网产品 </p>
 *
 * @author : cc
 * @date : 2023/5/15 14:26
 */
@Schema(name = "物联网产品")
@Entity
@Table(name = "oauth2_product", uniqueConstraints = {@UniqueConstraint(columnNames = {"product_key"})},
    indexes = {@Index(name = "oauth2_product_pid_idx", columnList = "product_id"), @Index(name = "oauth2_product_ipk_idx", columnList = "product_key")})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = OAuth2Constants.REGION_OAUTH2_PRODUCT)
public class OAuth2Product extends BaseEntity {

    @Id
    @UuidGenerator
    @Column(name = "product_id", length = 64)
    private String productId;

    @Column(name = "product_key", length = 32, unique = true)
    private String productKey;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("productId", productId)
            .add("productKey", productKey)
            .toString();
    }
}
