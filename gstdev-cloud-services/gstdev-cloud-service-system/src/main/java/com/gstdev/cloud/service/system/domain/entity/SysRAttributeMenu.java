package com.gstdev.cloud.service.system.domain.entity;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import com.gstdev.cloud.service.system.domain.generator.SysRAttributeMenuEmbeddablePK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "sys_r_attribute_menu", schema = "public")
@IdClass(SysRAttributeMenuEmbeddablePK.class)
public class SysRAttributeMenu extends BaseEntity {

    @Id
    @Column(name = "menu_id", length = 64)
    private String menuId;
    @Id
    @Column(name = "attribute_id", length = 64)
    private String attributeId;


}
