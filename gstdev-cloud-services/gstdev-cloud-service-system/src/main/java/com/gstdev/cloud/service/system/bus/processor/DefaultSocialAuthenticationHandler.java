package com.gstdev.cloud.service.system.bus.processor;

import com.google.common.collect.ImmutableSet;
import com.gstdev.cloud.access.all.processor.AccessHandlerStrategyFactory;
import com.gstdev.cloud.access.core.definition.AccessUserDetails;
import com.gstdev.cloud.access.core.exception.AccessIdentityVerificationFailedException;
import com.gstdev.cloud.base.definition.domain.oauth2.AccessPrincipal;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.oauth2.core.definition.domain.FrameGrantedAuthority;
import com.gstdev.cloud.oauth2.core.definition.domain.SocialUserDetails;
import com.gstdev.cloud.oauth2.core.definition.handler.AbstractSocialAuthenticationHandler;
import com.gstdev.cloud.oauth2.core.exception.SocialCredentialsParameterBindingFailedException;
import com.gstdev.cloud.oauth2.core.exception.UsernameAlreadyExistsException;
import com.gstdev.cloud.service.system.domain.converter.SysUserToSecurityUserConverter;
import com.gstdev.cloud.service.system.domain.entity.SysSocialUser;
import com.gstdev.cloud.service.system.domain.entity.SysUser;
import com.gstdev.cloud.service.system.service.SysSecurityService;
import com.gstdev.cloud.service.system.service.SysSocialUserService;
import com.gstdev.cloud.service.system.service.SysUserService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.bean.BeanUtil;

import java.util.Set;

/**
 * <p>Description: 社交登录默认处理器。 </p>
 *
 * @author : cc
 * @date : 2022/1/26 23:44
 */
public class DefaultSocialAuthenticationHandler extends AbstractSocialAuthenticationHandler {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysSocialUserService sysSocialUserService;
    @Resource
    private SysSecurityService sysSecurityService;
    @Resource
    private AccessHandlerStrategyFactory accessHandlerStrategyFactory;

    @Override
    public SocialUserDetails identity(String source, AccessPrincipal accessPrincipal) throws AccessIdentityVerificationFailedException {
        AccessUserDetails accessUserDetails = accessHandlerStrategyFactory.findAccessUserDetails(source, accessPrincipal);

        if (BeanUtil.isNotEmpty(accessUserDetails)) {
            SysSocialUser sysSocialUser = new SysSocialUser();
            BeanUtil.copyProperties(accessUserDetails, sysSocialUser);
            return sysSocialUser;
        }

        throw new AccessIdentityVerificationFailedException("Access Identity Verification Failed!");
    }

    @Override
    public SocialUserDetails isUserExist(SocialUserDetails socialUserDetails) {
        String uuid = socialUserDetails.getUuid();
        String source = socialUserDetails.getSource();
        if (StringUtils.isNotBlank(uuid) && StringUtils.isNotBlank(source)) {
            return sysSocialUserService.findByUuidAndSource(uuid, source);
        }

        return null;
    }

    @Override
    public DefaultSecurityUser register(SocialUserDetails socialUserDetails) throws UsernameAlreadyExistsException {
        return sysUserService.registerUserDetails(socialUserDetails);
    }

    @Override
    public void binding(String userId, SocialUserDetails socialUserDetails) throws SocialCredentialsParameterBindingFailedException {
        if (socialUserDetails instanceof SysSocialUser sysSocialUser) {
            SysUser sysUser = new SysUser();
            sysUser.setUserId(userId);
            sysSocialUser.setUsers(ImmutableSet.<SysUser>builder().add(sysUser).build());
            sysSocialUserService.saveAndFlush(sysSocialUser);
        }
    }

    @Override
    public void additionalRegisterOperation(DefaultSecurityUser herodotusUserDetails, SocialUserDetails socialUserDetails) {

    }

    @Override
    public DefaultSecurityUser signIn(SocialUserDetails socialUserDetails) {
        if (socialUserDetails instanceof SysSocialUser sysSocialUser) {
            SysUser sysUser = sysSocialUser.getUsers().stream().findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(sysUser)) {
                // 获取用户的权限
                Set<FrameGrantedAuthority> authorities = sysSecurityService.getUserAuthoritiesPermissions(sysUser);
                // 将SysUser对象转换为DefaultSecurityUser对象
                SysUserToSecurityUserConverter sysUserToSecurityUserConverter = new SysUserToSecurityUserConverter();
                return sysUserToSecurityUserConverter.convert(sysUser,authorities);
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public void additionalSignInOperation(DefaultSecurityUser herodotusUserDetails, SocialUserDetails newSocialUserDetails, SocialUserDetails oldSocialUserDetails) {
        if (newSocialUserDetails instanceof SysSocialUser newSysSocialUser && oldSocialUserDetails instanceof SysSocialUser oldSysSocialUser) {
            setSocialUserInfo(oldSysSocialUser, newSysSocialUser.getAccessToken(), newSysSocialUser.getExpireIn(), newSysSocialUser.getRefreshToken(), newSysSocialUser.getRefreshTokenExpireIn(), newSysSocialUser.getScope(), newSysSocialUser.getTokenType(), newSysSocialUser.getUid(), newSysSocialUser.getOpenId(), newSysSocialUser.getAccessCode(), newSysSocialUser.getUnionId());
            sysSocialUserService.saveAndFlush(oldSysSocialUser);
        }
    }

    protected void setSocialUserInfo(SysSocialUser sysSocialUser, String accessToken, Integer expireIn, String refreshToken, Integer refreshTokenExpireIn, String scope, String tokenType, String uid, String openId, String accessCode, String unionId) {
        sysSocialUser.setAccessToken(accessToken);
        sysSocialUser.setExpireIn(expireIn);
        sysSocialUser.setRefreshToken(refreshToken);
        sysSocialUser.setRefreshTokenExpireIn(refreshTokenExpireIn);
        sysSocialUser.setScope(scope);
        sysSocialUser.setTokenType(tokenType);
        sysSocialUser.setUid(uid);
        sysSocialUser.setOpenId(openId);
        sysSocialUser.setAccessCode(accessCode);
        sysSocialUser.setUnionId(unionId);
    }

}
