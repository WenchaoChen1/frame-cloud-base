package com.gstdev.cloud.service.system.domain.pojo.sysTenant;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.SysTenantPermissionType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.EnumSet;
import java.util.Set;

@Getter
@Setter
public class UpdateTenantManageIO {
    private String id;

    private String parentId;
    private String tenantCode;
    private String tenantName;
    private String description;
    private DataItemStatus status;
    private Integer type;
    @NotEmpty
    private Set<SysTenantPermissionType> tenantPermissionTypes= EnumSet.of(SysTenantPermissionType.ACCOUNT_TYPE);

    //-----------------自定义-----------

//    private String companyName = "";
//    private String website = "";
//    private String addressLine1 = "";
//    private String addressLine2 = "";
//    private String city = "";
//    private String state = "";
//    private String country = "";
//    private String zipCode = "";
//    private String firstName = "";
//    private String lastName = "";
//    private String emailAddress = "";
//    private String phoneNumber = "";
//    private String logo = "";
}
