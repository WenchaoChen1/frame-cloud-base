package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.entity.SysAccount;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.domain.entity.SysUser;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.AccountMenuPermissionsDto;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.InsertMenuManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.UpdateMenuManageIO;

import java.util.Collection;
import java.util.List;


public interface SysMenuService extends BaseService<SysMenu, String> {

    List<AccountMenuPermissionsDto> getAccountMenuPermissions(String accountId);

    void updateMenuManage(UpdateMenuManageIO updateMenuManageIO);

    void insertMenuManage(InsertMenuManageIO insertMenuManageIO);

    Collection<? extends SysMenu> findAllMenuByAccount(SysAccount account);

    Collection<? extends SysMenu> findAllMenuByUser(SysUser user);



    /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/


}
