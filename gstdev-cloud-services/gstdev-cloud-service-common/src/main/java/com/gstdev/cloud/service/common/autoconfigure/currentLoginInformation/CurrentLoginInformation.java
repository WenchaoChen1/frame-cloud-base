package com.gstdev.cloud.service.common.autoconfigure.currentLoginInformation;

import cn.hutool.json.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CurrentLoginInformation implements Serializable {
    private String userId;
    private String userName;
    private String accountId;
    private String accountName;
    private String tenantId;
    private List<Routes> leftAndTopRoutes;
    private List<Routes> rightRoutes;
    private List<String> functionPermissionCode;
    private List<String> pagePathAccessPermission;
    private Integer type;
    private JSON tenant;
    private JSON currentLoginAccount;
    private JSON currentLoginAccountUserPermissions;
    private JSON routes;

    @Getter
    @Setter
    public static class Routes implements Serializable {

//        private String id;
//        private String code;
//        private String icon;
        private String name;
//        private String parentId;
        private String path;
//        private Integer sort;
//        private DataItemStatus status;
        private List<Routes> children = null;

//        private String component;
//        private String redirect;
//        private String meta;
//        private String hidden;
//        private String alwaysShow;
//        private String children;
    }

}
