package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.base.core.utils.SecureUtil;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.oauth2.core.definition.domain.FrameGrantedAuthority;
import com.gstdev.cloud.oauth2.core.exception.PlatformAuthenticationException;
import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
import com.gstdev.cloud.service.common.autoconfigure.currentLoginInformation.CurrentLoginInformation;
import com.gstdev.cloud.service.common.autoconfigure.currentLoginInformation.RedisCurrentLoginInformation;
import com.gstdev.cloud.service.system.domain.entity.*;
import com.gstdev.cloud.service.system.domain.enums.*;
import com.gstdev.cloud.service.system.mapper.SysMenuMapper;
import com.gstdev.cloud.service.system.mapper.SysSecurityMapper;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
public class SysSecurityServiceImpl implements SysSecurityService {


    @Resource
    @Lazy
    private SysTenantService sysTenantService;
    @Resource
    @Lazy
    private SysAccountService sysAccountService;
    @Resource
    @Lazy
    private SysMenuService sysMenuService;

    @Resource
    private SysTenantMenuService sysTenantMenuService;
    @Resource
    private SysRAccountTenantMenuService sysRAccountTenantMenuService;
    @Resource
    private SysRAccountBusinessPermissionService sysRAccountBusinessPermissionService;
    @Resource
    private SysRAccountRoleService sysRAccountRoleService;
    @Resource
    private SysRRoleTenantMenuService sysRRoleTenantMenuService;
    @Resource
    private SysRRoleBusinessPermissionService sysRRoleBusinessPermissionService;
    @Resource
    private SysBusinessPermissionService sysBusinessPermissionService;
    @Resource
    private SysRTenantMenuBusinessPermissionService sysRTenantMenuBusinessPermissionService;

    @Resource
    private SysSecurityMapper sysSecurityMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private RedisCurrentLoginInformation redisCurrentLoginInformation;

    /**
     * 获取用户的权限
     *
     * @param user
     * @return
     */
    @Override
    public Set<FrameGrantedAuthority> getUserAuthoritiesPermissions(SysUser user) {
        // 初始化权限集合
        Set<FrameGrantedAuthority> authorities = new HashSet<>();

        // 添加超级管理员的特殊权限
        if (user.getType().equals(SysUserType.SUPER)) {
            authorities.add(new FrameGrantedAuthority("a181a603769c1f98ad927e7367c7aa51"));
            return authorities;
        }
        if (user.getType().equals(SysUserType.USER)) {
            authorities.addAll(getAccountAuthoritiesPermissions(user.getAccount()));
        }
        return authorities;
    }

    /**
     * 获取账户的权限
     *
     * @param sysAccounts
     * @return
     */
    public Set<FrameGrantedAuthority> getAccountAuthoritiesPermissions(List<SysAccount> sysAccounts) {
        Set<FrameGrantedAuthority> authorities = new HashSet<>();
        // 获取用户的账号列表
        sysAccounts = sysAccounts.stream()
            .filter(account -> account.getStatus().equals(DataItemStatus.ENABLE)).toList();
        List<SysMenu> accountSysMenu = getAccountSysMenu(sysAccounts);

        List<String> permissionsByTenantMenuIds = getPermissionsByMenus(accountSysMenu);
        for (String permissionsByMenuId : permissionsByTenantMenuIds) {
            authorities.add(new FrameGrantedAuthority(permissionsByMenuId));
        }
        return authorities;
    }

    /**
     * 获取账户的菜单
     *
     * @param accounts
     * @return
     */
    public List<SysMenu> getAccountSysMenu(List<SysAccount> accounts) {
        Set<String> tenantIds = accounts.stream().map(SysAccount::getTenantId).collect(Collectors.toSet());
        Map<String, SysTenant> tenantMap = sysTenantService.findAllByIds(tenantIds).stream()
            .collect(Collectors.toMap(
                SysTenant::getId,
                employee -> employee,
                (existing, replacement) -> existing
            ));

        // 初始化租户菜单相关集合
        Set<String> userTenantMenuIds = new HashSet<>();
        Set<String> adminAccountTenantIds = new HashSet<>();
        Set<String> accountTenantMenuAccountIds = new HashSet<>();
        Set<String> accountTenantBusinessPermissionAccountIds = new HashSet<>();
        Set<String> accountRoleAccountIds = new HashSet<>();
        Set<String> accountRoleMenuAccountIds = new HashSet<>();
        Set<String> accountRoleBusinessPermissionAccountIds = new HashSet<>();

        // 处理用户的租户账号
        accounts.forEach(account -> {
            SysTenant sysTenant = tenantMap.get(account.getTenantId());
            if (sysTenant.getTenantPermissionTypes().contains(SysTenantPermissionType.ACCOUNT_TYPE)) {
                if (account.getType().equals(SysAccountType.ADMIN)) {
                    adminAccountTenantIds.add(account.getTenantId());
                }
                if (account.getType().equals(SysAccountType.USER)) {
                    if (sysTenant.getTenantPermissionTypes().contains(SysTenantPermissionType.ACCOUNT_TENANT_MENU)) {
                        accountTenantMenuAccountIds.add(account.getAccountId());
                    }
                    if (sysTenant.getTenantPermissionTypes().contains(SysTenantPermissionType.ACCOUNT_TENANT_BUSINESS_PERMISSION)) {
                        accountTenantBusinessPermissionAccountIds.add(account.getAccountId());
                    }

                    if (sysTenant.getTenantPermissionTypes().contains(SysTenantPermissionType.ACCOUNT_ROLE_MENU)) {
                        accountRoleAccountIds.add(account.getAccountId());
                        accountRoleMenuAccountIds.add(account.getAccountId());
                    }
                    if (sysTenant.getTenantPermissionTypes().contains(SysTenantPermissionType.ACCOUNT_ROLE_BUSINESS_PERMISSION)) {
                        accountRoleAccountIds.add(account.getAccountId());
                        accountRoleBusinessPermissionAccountIds.add(account.getAccountId());
                    }
                }
            }
        });
        Set<String> tenantBusinessPermissionIds = new HashSet<>();

        if (!adminAccountTenantIds.isEmpty()) {
            userTenantMenuIds.addAll(sysTenantMenuService.getAllTenantMenuIdByTenantIdIn(adminAccountTenantIds));
        }

        if (!ObjectUtils.isEmpty(accountTenantMenuAccountIds)) {
            userTenantMenuIds.addAll(sysRAccountTenantMenuService.getAllTenantMenuIdByAccountIds(accountTenantMenuAccountIds));
        }
        if (!ObjectUtils.isEmpty(accountTenantBusinessPermissionAccountIds)) {
            tenantBusinessPermissionIds.addAll(sysRAccountBusinessPermissionService.getAllBusinessPermissionIdByAccountIds(accountTenantBusinessPermissionAccountIds));
        }
        if (!ObjectUtils.isEmpty(accountTenantBusinessPermissionAccountIds)) {
            List<String> allRoleIdByAccountIds = sysRAccountRoleService.getAllRoleIdByAccountIds(accountRoleAccountIds);
            if (!ObjectUtils.isEmpty(allRoleIdByAccountIds)) {
                if (!ObjectUtils.isEmpty(accountRoleMenuAccountIds)) {
                    userTenantMenuIds.addAll(sysRRoleTenantMenuService.getAllTenantMenuIdByRoleIds(allRoleIdByAccountIds));

                }
                if (!ObjectUtils.isEmpty(accountRoleBusinessPermissionAccountIds)) {
                    tenantBusinessPermissionIds.addAll(sysRRoleBusinessPermissionService.getAllBusinessPermissionIdByRoleIds(allRoleIdByAccountIds));

                }
            }
        }
        if (!ObjectUtils.isEmpty(tenantBusinessPermissionIds)) {
            userTenantMenuIds.addAll(sysRTenantMenuBusinessPermissionService.getAllTenantMenuIdByBusinessPermissionIds(tenantBusinessPermissionIds));
        }

        List<SysTenantMenu> sysTenantMenus = sysTenantMenuService.findAllById(userTenantMenuIds);
        return sysTenantMenus.stream().map(SysTenantMenu::getMenu).toList();
    }

    /**
     * 获取当前登录信息
     *
     * @param accountId
     * @return
     */
    @Override
    public CurrentLoginInformation getAccountCurrentLoginInformation(String accountId) {
        CurrentLoginInformation currentLoginInformation = redisCurrentLoginInformation.getCurrentLoginInformation();
        return currentLoginInformation == null ? updateAccountCurrentLoginInformation(accountId) : currentLoginInformation;
    }
    /**
     * 更新当前登录信息
     *
     * @param accountId
     * @return
     */
    @Override
    public CurrentLoginInformation updateAccountCurrentLoginInformation(String accountId) {
        CurrentLoginInformation accountCurrentLoginInformation = findAccountCurrentLoginInformation(accountId);
        redisCurrentLoginInformation.updateByTokenCurrentLoginInformation(accountCurrentLoginInformation);
        return accountCurrentLoginInformation;
    }

    /**
     * 获取当前登录信息
     *
     * @param accountId
     * @return
     */
    public CurrentLoginInformation findAccountCurrentLoginInformation(String accountId) {
        SysAccount account = null;
        if (!ObjectUtils.isEmpty(accountId)) {
            account = sysAccountService.findById(accountId);
        }
        if (account == null || account.getAccountId() == null) {
            List<SysAccount> accounts = sysAccountService.findAllByUserId(SecurityUtils.getUserId());
            if (ObjectUtils.isEmpty(accounts)) {
                throw new PlatformAuthenticationException("No account was found, please log in again");
            }
            account = accounts.get(0);
        }
        return getAccountCurrentLoginInformation(account);
    }


    /**
     * 获取指定账户登录信息
     *
     * @param account
     * @return
     */
    public CurrentLoginInformation getAccountCurrentLoginInformation(SysAccount account) {

        CurrentLoginInformation currentLoginInformation = new CurrentLoginInformation();
        currentLoginInformation.setUserId(account.getUser().getUserId());
        currentLoginInformation.setUserName(account.getUser().getUsername());
        currentLoginInformation.setAccountId(account.getAccountId());
        currentLoginInformation.setAccountName(account.getName());
        currentLoginInformation.setTenantId(account.getTenantId());
        currentLoginInformation.setType(account.getType().getValue());
        List<SysMenu> accountSysMenu;
        if (account.getUser().getType().equals(SysUserType.SUPER)) {
            accountSysMenu = sysMenuService.findAll();
        } else {
            accountSysMenu = getAccountSysMenu(List.of(account));
        }

        accountSysMenu = accountSysMenu.stream()
            .filter(sysMenu -> sysMenu.getStatus().equals(DataItemStatus.ENABLE)).toList();

        List<SysMenu> leftAndTopRoutes = accountSysMenu.stream()
            .filter(sysMenu -> sysMenu.getLocation().equals(SysMenuLocation.LEFT_MENU)
                && (sysMenu.getType().equals(SysMenuType.CATALOGUE)
                || sysMenu.getType().equals(SysMenuType.PAGE))
            ).toList();

        currentLoginInformation.setLeftAndTopRoutes(sysSecurityMapper.toRoutes(sysSecurityMapper.toMenuRoutesDtoToTree(leftAndTopRoutes)));
        List<SysMenu> rightRoutes = accountSysMenu.stream()
            .filter(sysMenu -> sysMenu.getLocation().equals(SysMenuLocation.RIGHT_MENU)
                && sysMenu.getType().equals(SysMenuType.FUNCTION)
            ).toList();

        currentLoginInformation.setRightRoutes(sysSecurityMapper.toRoutes(sysSecurityMapper.toMenuRoutesDtoToTree(rightRoutes)));

        List<String> functionPermissionCode = accountSysMenu.stream()
            .filter(sysMenu -> sysMenu.getType().equals(SysMenuType.FUNCTION)
            ).map(SysMenu::getCode).toList();
        currentLoginInformation.setFunctionPermissionCode(functionPermissionCode);

        List<String> pagePathAccessPermission = accountSysMenu.stream()
            .filter(sysMenu -> sysMenu.getType().equals(SysMenuType.CATALOGUE) || sysMenu.getType().equals(SysMenuType.PAGE)
            ).map(SysMenu::getName).toList();
        currentLoginInformation.setPagePathAccessPermission(pagePathAccessPermission);
        return currentLoginInformation;
    }

    /**
     * 获取菜单的权限
     *
     * @param menus
     * @return
     */
    public List<String> getPermissionsByMenus(List<SysMenu> menus) {
        Map<String, Set<String>> attributeMaps = new HashMap<>();
        // 处理菜单根据menuId去重
//        menus.stream().collect(Collectors.toMap(SysMenu::getId, menu -> menu, (key1, key2) -> key1));
//        List<SysMenu> distinctMenus = menus.stream()
//                .filter(Objects::nonNull)
//                .collect(Collectors.toMap(
//                        SysMenu::getId,
//                        Function.identity(),
//                        (existing, replacement) -> existing
//                ))
//                .values()
//                .stream()
//                .collect(Collectors.toList());

        // 处理菜单的属性并进行分组
        menus.forEach(sysMenu ->
            sysMenu.getAttributes().stream()
                .collect(Collectors.groupingBy(SysAttribute::getServiceId))
                .forEach((serviceId, attributes) -> {
                    Set<String> sysAttributes = attributeMaps.getOrDefault(serviceId, new HashSet<>());
                    sysAttributes.addAll(attributes.stream().map(SysAttribute::getAttributeCode).collect(Collectors.toSet()));
                    sysAttributes.addAll(attributeMaps.get(serviceId));
                    attributeMaps.put(serviceId, sysAttributes);
                })
        );
        List<String> collect = new ArrayList<>();
        // 将分组后的属性ID集合生成权限并添加到权限集合中
        for (Map.Entry<String, Set<String>> stringSetEntry : attributeMaps.entrySet()) {
            Set<String> value = stringSetEntry.getValue();
            String key = stringSetEntry.getKey();
            List<String> objects = new ArrayList<>();
            for (String s : value) {
                System.out.println(key+s);
                objects.add(key+s);
            }

            collect.add(generateKey(new ArrayList<>(objects)));
        }

        return collect;
    }

    /**
     * 生成权限key
     *
     * @param input
     * @return
     */
    public static String generateKey(List<String> input) {
        // 对字符串列表进行排序
        Collections.sort(input);
        // 连接排序后的字符串
        String combinedInput = String.join("", input);
        return SecureUtil.md5(combinedInput);
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hash = digest.digest(combinedInput.getBytes());
//            StringBuilder hexString = new StringBuilder();
//            for (byte b : hash) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) {
//                    hexString.append('0');
//                }
//                hexString.append(hex);
//            }
//            return hexString.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return null;
//        }
    }


}
