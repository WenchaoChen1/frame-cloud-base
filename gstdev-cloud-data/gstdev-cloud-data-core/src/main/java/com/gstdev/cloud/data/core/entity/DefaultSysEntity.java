package com.gstdev.cloud.data.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.UuidGenerator;

/**
 * <p> Description : BaseSystemEntity </p>
 *
 * @author : cc
 * @date : 2020/4/29 17:43
 */
@MappedSuperclass
public abstract class DefaultSysEntity extends BaseSysEntity {

    @Id
    @UuidGenerator
    @Column(name = "id", length = 64)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
