package com.gstdev.cloud.service.system.domain.pojo.sysAttribute;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAttributeManageIO {

    private String attributeId;
    private String webExpression;
    private String description;
    private DataItemStatus status;
}
