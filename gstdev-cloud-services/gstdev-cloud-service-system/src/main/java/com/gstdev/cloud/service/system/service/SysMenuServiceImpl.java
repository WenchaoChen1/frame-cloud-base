package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.base.core.utils.SecureUtil;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.*;
import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import com.gstdev.cloud.service.system.domain.enums.SysMenuType;
import com.gstdev.cloud.service.system.domain.enums.SysUserType;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.AccountMenuPermissionsDto;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.AccountMenuPermissionsQO;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.InsertMenuManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.UpdateMenuManageIO;
import com.gstdev.cloud.service.system.mapper.SysMenuMapper;
import com.gstdev.cloud.service.system.repository.SysAccountRepository;
import com.gstdev.cloud.service.system.repository.SysMenuRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, String, SysMenuRepository> implements SysMenuService {

    @Resource
    private SysAccountRepository accountRepository;
    @Resource
    @Lazy
    private SysTenantMenuService tenantMenuService;
    @Resource
    @Lazy
    private SysMenuServiceImpl service;
    @Resource
    private SysMenuRepository menuRepository;
    @Resource
    private SysMenuMapper menuMapper;

    public SysMenuServiceImpl(SysMenuRepository menuRepository, SysMenuMapper menuMapper) {
        super(menuRepository);
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    @Override
    public SysMenuRepository getRepository() {
        return menuRepository;
    }

    public SysMenuMapper getMapper() {
        return menuMapper;
    }

    @Override
    public SysMenuServiceImpl getService() {
        return service;
    }

    public List<SysMenu> findAllMenuByTenantId(String tenantId) {
        return tenantMenuService.findAllByTenantId(tenantId).stream().map(SysTenantMenu::getMenu).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends SysMenu> findAllMenuByAccount(SysAccount account) {
        return List.of();
    }

    @Override
    public Collection<? extends SysMenu> findAllMenuByUser(SysUser user) {
        return List.of();
    }

    @Override
    public List<AccountMenuPermissionsDto> getAccountMenuPermissions(String accountId) {
        SysAccount account = accountRepository.findById(accountId).get();
        AccountMenuPermissionsQO accountMenuPermissionsQO = new AccountMenuPermissionsQO();
        List<SysMenu> sysMenuList = new ArrayList<>();
        SysUser user = account.getUser();
        if (user.getType().equals(SysUserType.SUPER)) {
            sysMenuList = service.findAll();
        }
        if (user.getType().equals(SysUserType.USER)) {
            if (account.getType().equals(SysAccountType.ADMIN)) {
                sysMenuList = findAllMenuByTenantId(account.getTenantId());
            } else if (account.getType().equals(SysAccountType.USER)) {
                Map<String, SysMenu> collect = new HashMap<>();
                for (SysRole role : account.getRoles()) {
                    Map<String, SysMenu> collect1 = role.getTenantMenus().stream().map(SysTenantMenu::getMenu).collect(Collectors.groupingBy(SysMenu::getId,
                        Collectors.collectingAndThen(Collectors.toList(), value -> value.get(0))));
                    collect.putAll(collect1);
                }
                sysMenuList = collect.values().stream().toList();
            }
        }
        sysMenuList = sysMenuList.stream().filter(sysMenu -> sysMenu.getStatus().equals(DataItemStatus.ENABLE)).toList();
        return getMapper().toAccountMenuPermissionsDtoToTree(sysMenuList);
    }

    @Override
    @Transactional
    public void insertMenuManage(InsertMenuManageIO insertMenuManageIO) {
        SysMenu entity = getMapper().toEntity(insertMenuManageIO);
        entity.setId(SecureUtil.md5(entity.getCode()));
        verify(entity);
        getService().saveAndFlush(entity);
    }


    @Override
    @Transactional
    public void updateMenuManage(UpdateMenuManageIO updateMenuManageIO) {
        SysMenu sysMenu = service.findById(updateMenuManageIO.getId());
        menuMapper.copy(updateMenuManageIO, sysMenu);
        verify(sysMenu);
        service.saveAndFlush(sysMenu);
    }

    public void verify(SysMenu entity) {


        if (entity.getType().equals(SysMenuType.CATALOGUE)) {
            if (ObjectUtils.isEmpty(entity.getName())) {
                throw new PlatformRuntimeException("目录类型菜单必须填写name");
            }
        }
        if (entity.getType().equals(SysMenuType.PAGE)) {
            if (ObjectUtils.isEmpty(entity.getName())) {
                throw new PlatformRuntimeException("页面类型菜单必须填写name");
            }
            if (ObjectUtils.isEmpty(entity.getPath())) {
                throw new PlatformRuntimeException("页面类型菜单必须填写路径");
            }
        }

        /**
         * ^(?=.*[a-zA-Z]{3,})：使用正向肯定预查（positive lookahead）确保字符串中至少包含三个字母。
         * [a-zA-Z0-9-]*$：表示字符串可以包含任意数量的字母、数字或连字符，但不能包含其他特殊符号。
         */
        if (!Pattern.compile("^(?=.*[a-zA-Z]{3,})[a-zA-Z0-9-]*$").matcher(entity.getCode()).matches()) {
            throw new PlatformRuntimeException("code 不符合格式");
        }

        /**
         * ^[a-zA-Z]{3,}: 表示字符串必须以至少三个字母开头（字母数不少于三位）。
         * -: 表示字母和数字之间必须用一个连字符 - 间隔。
         * \\d{3,}$: 表示连字符后必须跟随至少三个数字（数字数不少于三位）。
         * 确保整个字符串的长度不少于七个字符时，只需保证字母和数字各不少于三位，并且中间有一个连字符，即可满足要求。以下是一个示例代码，用于检查字符串是否符合上述正则表达式：
         */
        if (!Pattern.compile("^[a-zA-Z]{3,}-\\d{3,}$").matcher(entity.getCode()).matches()
                && !entity.getType().equals(SysMenuType.FUNCTION)
        ) {
            throw new PlatformRuntimeException("code 不符合格式");
        }
        if (entity.getType().equals(SysMenuType.FUNCTION)) {

        }
    }
    /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/

}
