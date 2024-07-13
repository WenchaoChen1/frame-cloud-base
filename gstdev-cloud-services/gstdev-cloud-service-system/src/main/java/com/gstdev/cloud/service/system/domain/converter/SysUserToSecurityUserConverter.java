package com.gstdev.cloud.service.system.domain.converter;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.oauth2.core.definition.domain.FrameGrantedAuthority;
import com.gstdev.cloud.service.system.domain.entity.SysAccount;
import com.gstdev.cloud.service.system.domain.entity.SysUser;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * <p>Description: SysUser 转 SecurityUser 转换器 </p>
 */
public class SysUserToSecurityUserConverter implements Converter<SysUser, DefaultSecurityUser> {
    @Override
    public DefaultSecurityUser convert(SysUser sysUser) {
        return convert(sysUser, new HashSet<>());
    }
    public DefaultSecurityUser convert(SysUser sysUser,Set<FrameGrantedAuthority> authorities) {

        Set<String> roles = new HashSet<>();

//        List<SysRole> accountRoles = sysUser.getAccount().stream()
//                .flatMap(account -> account.getRoles().stream())
//                .collect(Collectors.toList());
//        for (SysAccount sysAccount : sysUser.getAccount()) {
//            if (sysAccount.getType().equals(SysAccountType.SUPER)) {
//                authorities.add(new FrameGrantedAuthority("5ef5ef0364b6939c4ca61f34b393f7b368d1be8619647aaf83d5b395919ab629"));
//            }
//        }

//        for (SysRole sysRole : accountRoles) {
//            roles.add(sysRole.getCode());
//            authorities.add(new FrameGrantedAuthority(SecurityUtils.wellFormRolePrefix(sysRole.getCode())));
//            Set<SysPermission> sysPermissions = sysRole.getPermissions();
//            if (CollectionUtils.isNotEmpty(sysPermissions)) {
//                sysPermissions.forEach(sysAuthority -> authorities.add(new FrameGrantedAuthority((sysAuthority.getPermissionCode()))));
//            }
//        }
        Optional<SysAccount> firstAccount = sysUser.getAccount().stream().findFirst();
        DefaultSecurityUser defaultSecurityUser = new DefaultSecurityUser(
                sysUser.getUserId(),
                sysUser.getUsername(),
                firstAccount.get().getAccountId(),
                firstAccount.get().getName(),
                sysUser.getPassword(),
                isEnabled(sysUser),
                isAccountNonExpired(sysUser),
                isCredentialsNonExpired(sysUser),
                isNonLocked(sysUser),
                authorities, roles, "null", sysUser.getAvatar());
        return defaultSecurityUser;
    }

    private boolean isEnabled(SysUser sysUser) {
        return sysUser.getStatus() != DataItemStatus.FORBIDDEN;
    }

    private boolean isNonLocked(SysUser sysUser) {
        return !(sysUser.getStatus() == DataItemStatus.LOCKING);
    }

    private boolean isNonExpired(Instant localDateTime) {
        if (ObjectUtils.isEmpty(localDateTime)) {
            return true;
        } else {
            return localDateTime.isAfter(localDateTime);
        }
    }

    private boolean isAccountNonExpired(SysUser sysUser) {
        if (sysUser.getStatus() == DataItemStatus.EXPIRED) {
            return false;
        }

        return isNonExpired(sysUser.getAccountExpireAt());
    }

    private boolean isCredentialsNonExpired(SysUser sysUser) {
        return isNonExpired(sysUser.getCredentialsExpireAt());
    }
}
