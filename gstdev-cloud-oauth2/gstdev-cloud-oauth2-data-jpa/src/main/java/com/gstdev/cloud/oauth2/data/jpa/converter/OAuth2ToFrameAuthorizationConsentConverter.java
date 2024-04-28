package com.gstdev.cloud.oauth2.data.jpa.converter;

import com.gstdev.cloud.oauth2.data.jpa.entity.FrameAuthorizationConsent;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: OAuth2AuthorizationConsent 转 FrameAuthorizationConsent 转换器</p>
 *
 * @author : cc
 * @date : 2023/5/21 21:05
 */
public class OAuth2ToFrameAuthorizationConsentConverter implements Converter<OAuth2AuthorizationConsent, FrameAuthorizationConsent> {
    @Override
    public FrameAuthorizationConsent convert(OAuth2AuthorizationConsent authorizationConsent) {
        FrameAuthorizationConsent entity = new FrameAuthorizationConsent();
        entity.setRegisteredClientId(authorizationConsent.getRegisteredClientId());
        entity.setPrincipalName(authorizationConsent.getPrincipalName());

        Set<String> authorities = new HashSet<>();
        for (GrantedAuthority authority : authorizationConsent.getAuthorities()) {
            authorities.add(authority.getAuthority());
        }
        entity.setAuthorities(StringUtils.collectionToCommaDelimitedString(authorities));

        return entity;
    }
}
