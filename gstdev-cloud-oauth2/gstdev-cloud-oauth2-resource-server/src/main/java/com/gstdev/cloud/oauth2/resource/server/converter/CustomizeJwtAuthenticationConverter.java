package com.gstdev.cloud.oauth2.resource.server.converter;

import com.gstdev.cloud.base.definition.constants.BaseConstants;
import com.gstdev.cloud.base.definition.domain.oauth2.PrincipalDetails;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.oauth2.core.utils.PrincipalUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * @program: frame-cloud-base
 * @description: 扩展的 JwtAuthenticationConverter
 * @author: wenchao.chen
 * @create: 2024/03/28 11:13
 **/
//public class CustomizeJwtAuthenticationConverter extends JwtAuthenticationConverter {
//
//    public CustomizeJwtAuthenticationConverter() {
//        CustomizeJwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new CustomizeJwtGrantedAuthoritiesConverter();
//        grantedAuthoritiesConverter.setAuthoritiesClaimName(BaseConstants.AUTHORITIES);
//
//        this.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
//    }
public class CustomizeJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    private String principalClaimName = "sub";

    public CustomizeJwtAuthenticationConverter() {
        CustomizeJwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new CustomizeJwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName(BaseConstants.AUTHORITIES);

        this.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
    }

    public final AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = this.jwtGrantedAuthoritiesConverter.convert(jwt);
        String principalClaimValue = jwt.getClaimAsString(this.principalClaimName);
        return new CustomizeJwtAuthenticationToken(jwt, PrincipalUtils.toPrincipalDetails(jwt),authorities, principalClaimValue);
    }

    public void setJwtGrantedAuthoritiesConverter(Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter) {
        Assert.notNull(jwtGrantedAuthoritiesConverter, "jwtGrantedAuthoritiesConverter cannot be null");
        this.jwtGrantedAuthoritiesConverter = jwtGrantedAuthoritiesConverter;
    }

    public void setPrincipalClaimName(String principalClaimName) {
        Assert.hasText(principalClaimName, "principalClaimName cannot be empty");
        this.principalClaimName = principalClaimName;
    }
}
