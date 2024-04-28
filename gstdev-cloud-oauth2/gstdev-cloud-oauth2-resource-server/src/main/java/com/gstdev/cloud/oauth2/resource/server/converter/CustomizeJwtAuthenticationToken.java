package com.gstdev.cloud.oauth2.resource.server.converter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import javax.security.auth.Subject;
import java.util.Collection;
import java.util.Map;

@Transient
public class CustomizeJwtAuthenticationToken extends AbstractOAuth2TokenAuthenticationToken<Jwt> {
    private static final long serialVersionUID = 620L;
    private final String name;

    public CustomizeJwtAuthenticationToken(Jwt jwt) {
        super(jwt);
        this.name = jwt.getSubject();
    }

    public CustomizeJwtAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities) {
        super(jwt, authorities);
        this.setAuthenticated(true);
        this.name = jwt.getSubject();
    }

    public CustomizeJwtAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities, String name) {
        super(jwt, authorities);
        this.setAuthenticated(true);
        this.name = name;
    }

    public CustomizeJwtAuthenticationToken(Jwt jwt, Object principal, Collection<? extends GrantedAuthority> authorities, String name) {
        super(jwt, principal, jwt, authorities);
        this.setAuthenticated(true);
        this.name = name;
    }


    public Map<String, Object> getTokenAttributes() {
        return ((Jwt) this.getToken()).getClaims();
    }

    public String getName() {
        return this.name;
    }
}
