package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.oauth2.core.definition.domain.FrameGrantedAuthority;
import com.gstdev.cloud.oauth2.core.definition.domain.SocialUserDetails;
import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
import com.gstdev.cloud.service.system.domain.base.user.UserDto;
import com.gstdev.cloud.service.system.domain.converter.SysUserToSecurityUserConverter;
import com.gstdev.cloud.service.system.domain.entity.SysAccount;
import com.gstdev.cloud.service.system.domain.entity.SysTenant;
import com.gstdev.cloud.service.system.domain.entity.SysUser;
import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.InsertAccountManageInitializationIO;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.UpdateAccountSettingsDetailIO;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.InsertUserManageInitializationIO;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.UpdateUserSettingsDetailIO;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.UserSettingsDetailVO;
import com.gstdev.cloud.service.system.domain.vo.user.AccountListDto;
import com.gstdev.cloud.service.system.feign.service.IdentityFeignService;
import com.gstdev.cloud.service.system.mapper.SysUserMapper;
import com.gstdev.cloud.service.system.repository.SysUserRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.data.id.IdUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Random;
import java.util.Set;

@Slf4j
@Transactional(readOnly = true)
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, String, SysUserRepository> implements SysUserService {

    private static final String SPECIAL_CHARS = "! @#$%^&＊_=+-/";
    @Resource
    @Lazy
    private SysAccountService accountService;@Resource
    @Lazy
    private SysSecurityService sysSecurityService;

    @Resource
    @Lazy
    private SysTenantService sysTenantService;
    @Resource
    @Lazy
    private IdentityFeignService identityFeignService;
    @Resource
    private SysUserRepository userRepository;
    private final Converter<SysUser, DefaultSecurityUser> toUser;


    //    @Resource
    private SysUserMapper userMapper;

    public SysUserServiceImpl(SysUserRepository userRepository, SysUserMapper userMapper) {
        super(userRepository);
        this.userMapper = userMapper;
        this.toUser = new SysUserToSecurityUserConverter();
    }

    private static char nextChar(Random rnd) {
        switch (rnd.nextInt(4)) {
            case 0:
                return (char) ('a' + rnd.nextInt(26));
            case 1:
                return (char) ('A' + rnd.nextInt(26));
            case 2:
                return (char) ('0' + rnd.nextInt(10));
            default:
                return SPECIAL_CHARS.charAt(rnd.nextInt(SPECIAL_CHARS.length()));
        }
    }

    public static String randomPassword() {
        char[] chars = new char[8];
        Random rnd = new Random();
        for (int i = 0; i < 8; i++) {
            chars[i] = nextChar(rnd);
        }
        return new String(chars);
    }

    @Override
    public SysUserRepository getRepository() {
        return userRepository;
    }

    @Override
    @Transactional
    public SysUser save(SysUser user) {
        if (ObjectUtils.isEmpty(user.getPassword())) {
            user.setPassword(SecurityUtils.encrypt(randomPassword()));
        }
        return super.save(user);
    }

    @Override
    @Transactional
    public SysUser insertUserManageInitialization(InsertUserManageInitializationIO userInsertInput) {
        if (ObjectUtils.isEmpty(userInsertInput.getUsername())) {
            String enhance = this.enhance(userInsertInput.getUsername());
            userInsertInput.setUsername(enhance);
        }
        if (ObjectUtils.isEmpty(userInsertInput.getEmail())) {
            userInsertInput.setEmail(userInsertInput.getUsername() + "@frame.com");
        }
        SysUser byEmail = findByEmail(userInsertInput.getEmail());
        if (!ObjectUtils.isEmpty(byEmail)) {
            throw new PlatformRuntimeException("邮箱已经存在");
        }

        if (ObjectUtils.isEmpty(userInsertInput.getTenantId())) {
            userInsertInput.setTenantId("d578b961-3e25-429e-805e-0b8a2e34122d");
        }

        if (ObjectUtils.isEmpty(userInsertInput.getStatus())) {
            userInsertInput.setStatus(DataItemStatus.ENABLE);
        }
        SysUser user = userMapper.toEntity(userInsertInput);


        SysUser insert = insert(user);
        InsertAccountManageInitializationIO accountInsertInput = new InsertAccountManageInitializationIO();
        accountInsertInput.setTenantId(userInsertInput.getTenantId());
        accountInsertInput.setUserId(insert.getUserId());
        accountInsertInput.setName(insert.getUsername());
        accountInsertInput.setType(SysAccountType.USER);
//        accountInsertInput.setType(userInsertInput.getType());
        SysAccount sysAccount = accountService.insertAccountManageInitialization(accountInsertInput);
        insert.setAccount(List.of(sysAccount));
        // 同步到identity模块
//        IdentitySaveDto identitySaveDto = new IdentitySaveDto();
//        identitySaveDto.setUserId(insert.getUserId());
//        identitySaveDto.setEmail(insert.getEmail());
//        identitySaveDto.setUsername(insert.getUsername());
//        identitySaveDto.setPassword(insert.getPassword());
//        identityFeignService.save(identitySaveDto);
        return insert;
    }



    /**
     * 新增用户并且创建账户角色，关联部门
     *
     * @param userInsertInput
     * @return
     */
    @Transactional
    public UserDto insertUserManageInitializationToDto(InsertUserManageInitializationIO userInsertInput) {
        SysUser sysUser = insertUserManageInitialization(userInsertInput);
        return userMapper.toDto(sysUser);
    }

    @Override
    @Transactional
    public DefaultSecurityUser signInFindByUsername(String username) {

        // 根据用户名查找用户
        SysUser user = getRepository().findByUsername(username);
        if (user == null) {
            return null;
        }

        // 获取用户的权限
        Set<FrameGrantedAuthority> authorities = sysSecurityService.getUserAuthoritiesPermissions(user);

        // 将SysUser对象转换为DefaultSecurityUser对象
        SysUserToSecurityUserConverter sysUserToSecurityUserConverter = new SysUserToSecurityUserConverter();
        return sysUserToSecurityUserConverter.convert(user,authorities);
    }



    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////

    public List<AccountListDto> getByIdToAccount(String id) {
        SysUser user = getRepository().findById(id).orElseGet(SysUser::new);
        return userMapper.accountListToDto(user.getAccount());
    }

    public SysUser findByUsername(String username) {
        return getRepository().findByUsername(username);
    }

    public SysUser findByEmail(String email) {
        return getRepository().findByEmail(email);
    }

    @Override
    @Transactional()
    public void deleteById(String id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new PlatformRuntimeException("The primary key cannot be empty");
        }
        if (id.equals(SecurityUtils.getUserId())) {
            throw new PlatformRuntimeException("You cannot delete your own data");
        }
        getRepository().deleteById(id);
    }
    @Override
    @Transactional
    public void changeStatus(String userId, DataItemStatus status) {
        SysUser sysUser = getService().findById(userId);
        if (org.apache.commons.lang3.ObjectUtils.isNotEmpty(sysUser)) {
            sysUser.setStatus(status);
            log.debug("[GstDev Cloud] |- Change user [{}] status to [{}]", sysUser.getUsername(), status.name());
            getService().save(sysUser);
        }

    }
    @Override
    @Transactional
    public void resetPassword(String originalPassword, String newPassword) {
        SysUser user = getRepository().findById(SecurityUtils.getUserId()).orElseGet(SysUser::new);
        if (!SecurityUtils.matches(originalPassword, user.getPassword())) {
            throw new PlatformRuntimeException("The original password is incorrect");
        }
        user.setPassword(SecurityUtils.encrypt(newPassword));
        save(user);
    }
    @Override
    @Transactional
    public void userManageResetPaaword(String newPassword, String userId) {
        SysUser sysUser = getService().findById(userId);
        if (ObjectUtils.isEmpty(sysUser)) {
            throw new PlatformRuntimeException("Reset failure");
        }
        sysUser.setPassword(SecurityUtils.encrypt(newPassword));
        log.debug("[GstDev Cloud] |- Reset user [{}] password to [{}]", sysUser.getUsername(), newPassword);
        getService().save(sysUser);
    }

    /**
     * 用户只有在登录状态下才能获取自己的用户信息
     * 如果账户有多个不返回账户信息需要去账户配置页面寻找
     *
     * @return
     */
    @Override
    public UserSettingsDetailVO getUserSettingsDetail() {
        SysUser sysUser = getService().findById(SecurityUtils.getUserId());
        UserSettingsDetailVO userSettingsDetailVo = userMapper.toUserSettingsDetailVo(sysUser);
        List<SysAccount> account = sysUser.getAccount();
        if (account.size() == 1) {
            userSettingsDetailVo.setAccountId(account.get(0).getAccountId());
            userSettingsDetailVo.setAccountName(account.get(0).getName());
            userSettingsDetailVo.setAccountType(account.get(0).getType());
            SysTenant sysTenant = sysTenantService.findById(account.get(0).getTenantId());
            userSettingsDetailVo.setTenantName(sysTenant.getTenantName());
        }
        return userSettingsDetailVo;
    }

    @Override
    @Transactional
    public void updateUserSettingsDetail(UpdateUserSettingsDetailIO updateUserSettingsDetailIO) {
        SysUser sysUser = getService().findById(SecurityUtils.getUserId());
        userMapper.copy(updateUserSettingsDetailIO, sysUser);
        if (!ObjectUtils.isEmpty(updateUserSettingsDetailIO.getAccountId())) {
            UpdateAccountSettingsDetailIO updateAccountSettingsDetailIO = new UpdateAccountSettingsDetailIO();
            updateAccountSettingsDetailIO.setAccountId(updateUserSettingsDetailIO.getAccountId());
            updateAccountSettingsDetailIO.setName(updateUserSettingsDetailIO.getAccountName());
            accountService.updateAccountSettingsDetail(updateAccountSettingsDetailIO);
        }
        getService().save(sysUser);
    }

    @Override
    public DefaultSecurityUser registerUserDetails(SocialUserDetails socialUserDetails) {
        SysUser newSysUser = this.register(socialUserDetails);
        // 获取用户的权限
        Set<FrameGrantedAuthority> authorities = sysSecurityService.getUserAuthoritiesPermissions(newSysUser);

        // 将SysUser对象转换为DefaultSecurityUser对象
        SysUserToSecurityUserConverter sysUserToSecurityUserConverter = new SysUserToSecurityUserConverter();
        return sysUserToSecurityUserConverter.convert(newSysUser,authorities);
    }

    public SysUser register(SocialUserDetails socialUserDetails) {
        InsertUserManageInitializationIO insertUserManageInitialization = new InsertUserManageInitializationIO();

        insertUserManageInitialization.setUsername(socialUserDetails.getUsername());

        String nickname = socialUserDetails.getNickname();
        if (StringUtils.isNotBlank(nickname)) {
            insertUserManageInitialization.setNickname(nickname);
        }

        String phoneNumber = socialUserDetails.getPhoneNumber();
        if (StringUtils.isNotBlank(phoneNumber)) {
            insertUserManageInitialization.setPhoneNumber(SecurityUtils.encrypt(phoneNumber));
        }

        String email = socialUserDetails.getEmail();
        if (StringUtils.isNotBlank(email)) {
            insertUserManageInitialization.setEmail(email);
        }

        String avatar = socialUserDetails.getAvatar();
        if (StringUtils.isNotBlank(avatar)) {
            insertUserManageInitialization.setAvatar(avatar);
        }

        return insertUserManageInitialization(insertUserManageInitialization);
    }

    private String enhance(String username) {
        if (StringUtils.isNotBlank(username)) {
            SysUser checkedSysUser = this.findByUsername(username);
            if (org.apache.commons.lang3.ObjectUtils.isNotEmpty(checkedSysUser)) {
                String var10000 = checkedSysUser.getUsername();
                return var10000 + IdUtil.nanoId(12);
            } else {
                return username;
            }
        } else {
            return "User" + IdUtil.nanoId(12);
        }
    }


}
