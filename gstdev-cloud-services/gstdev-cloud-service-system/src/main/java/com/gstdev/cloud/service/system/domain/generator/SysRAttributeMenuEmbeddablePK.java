package com.gstdev.cloud.service.system.domain.generator;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
//@Embeddable
public class SysRAttributeMenuEmbeddablePK implements Serializable {
    private String menuId;
    private String attributeId;

    public SysRAttributeMenuEmbeddablePK(String menuId, String attributeId) {
        this.menuId = menuId;
        this.attributeId = attributeId;
    }

    public SysRAttributeMenuEmbeddablePK() {

    }
}
