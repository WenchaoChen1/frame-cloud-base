package com.gstdev.cloud.service.system.domain.pojo.rTenantMenu;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InsertTenantMenuIO {
    @NotBlank
    private String tenantId;
    private List<String> menuIds;
}
