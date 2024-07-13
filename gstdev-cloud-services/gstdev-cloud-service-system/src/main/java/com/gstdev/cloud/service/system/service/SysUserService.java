package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.service.system.domain.entity.SysUser;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.InsertUserManageInitializationIO;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.UpdateUserSettingsDetailIO;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.UserSettingsDetailVO;

public interface SysUserService extends BaseService<SysUser, String> {

    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    DefaultSecurityUser signInFindByUsername(String username);


    /**
     * 用户管理初始化
     * @param userInsertInput
     * @return
     */
    SysUser insertUserManageInitialization(InsertUserManageInitializationIO userInsertInput);

    /**
     * 重置密码
     * @param originalPassword
     * @param newPassword
     */
    void resetPassword(String originalPassword, String newPassword);

    void changeStatus(String userId, DataItemStatus dataItemStatus);

    void userManageResetPaaword(String newPassword, String userId);

    UserSettingsDetailVO getUserSettingsDetail();

    void updateUserSettingsDetail(UpdateUserSettingsDetailIO updateUserSettingsDetailIO);
}


