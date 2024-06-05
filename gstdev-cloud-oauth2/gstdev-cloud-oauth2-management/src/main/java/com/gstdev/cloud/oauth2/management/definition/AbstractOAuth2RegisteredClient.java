package com.gstdev.cloud.oauth2.management.definition;

import com.gstdev.cloud.oauth2.core.enums.SignatureJwsAlgorithm;
import com.gstdev.cloud.oauth2.core.enums.TokenFormat;
import com.gstdev.cloud.oauth2.core.enums.AllJwsAlgorithm;
import com.gstdev.cloud.oauth2.data.jpa.definition.domain.AbstractRegisteredClient;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Scope;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import org.dromara.hutool.core.data.id.IdUtil;

import java.time.Duration;
import java.util.Set;

/**
 * <p>Description: 应用对象转 RegisteredClient 共性属性 </p>
 *
 * @author : cc
 * @date : 2023/5/21 17:46
 */
@MappedSuperclass
public abstract class AbstractOAuth2RegisteredClient extends AbstractRegisteredClient {

    @Schema(title = "客户端Id", description = "默认为系统自动生成")
    @Column(name = "client_id", length = 100)
    private String clientId = IdUtil.fastSimpleUUID();

    @Schema(title = "客户端秘钥", description = "这里存储的客户端秘钥是明文，方便使用。默认为系统自动生成")
    @Column(name = "client_secret", length = 100)
    private String clientSecret = IdUtil.fastSimpleUUID();

    /* --- ClientSettings Begin --- */
    @Schema(title = "是否需要证明Key", description = "如果客户端在执行授权码授予流时需要提供验证密钥质询和验证器, 默认False")
    @Column(name = "require_proof_key")
    private Boolean requireProofKey = Boolean.FALSE;

    @Schema(title = "是否需要认证确认", description = "如果客户端在执行授权码授予流时需要提供验证密钥质询和验证器, 默认False")
    @Column(name = "require_authorization_consent")
    private Boolean requireAuthorizationConsent = Boolean.TRUE;

    @Schema(title = "客户端JSON Web密钥集的URL", description = "客户端JSON Web密钥集的URL")
    @Column(name = "jwk_set_url", length = 1000)
    private String jwkSetUrl;

    @Schema(title = "JWT 签名算法", description = "仅在 clientAuthenticationMethods 为 private_key_jwt 和 client_secret_jwt 方法下使用")
    @Column(name = "signing_algorithm")
    @Enumerated(EnumType.ORDINAL)
    private AllJwsAlgorithm authenticationSigningAlgorithm;
    /* --- ClientSettings End --- */


    /* --- TokenSettings Begin --- */
    @Schema(title = "授权码有效时间", description = "默认5分钟，使用 Duration 时间格式")
    @Column(name = "authorization_code_validity")
    private Duration authorizationCodeValidity = Duration.ofMinutes(5);

    @Schema(title = "激活码有效时间", description = "默认5分钟，使用 Duration 时间格式")
    @Column(name = "device_code_validity")
    private Duration deviceCodeValidity = Duration.ofMinutes(5);

    @Schema(title = "AccessToken 有效时间", description = "默认5分钟，使用 Duration 时间格式")
    @Column(name = "access_token_validity")
    private Duration accessTokenValidity = Duration.ofMinutes(5);

    @Schema(title = "RefreshToken 有效时间", description = "默认60分钟，使用 Duration 时间格式")
    @Column(name = "refresh_token_validity")
    private Duration refreshTokenValidity = Duration.ofMinutes(60);

    @Schema(title = "Access Token 格式", description = "OAuth 2.0令牌的标准数据格式")
    @Column(name = "access_token_format")
    @Enumerated(EnumType.ORDINAL)
    private TokenFormat accessTokenFormat = TokenFormat.REFERENCE;

    @Schema(title = "是否重用 Refresh Token", description = "默认值 True")
    @Column(name = "reuse_refresh_tokens")
    private Boolean reuseRefreshTokens = Boolean.TRUE;

    @Schema(title = "IdToken 签名算法", description = "JWT 算法用于签名 ID Token， 默认值 RS256")
    @Column(name = "signature_algorithm")
    @Enumerated(EnumType.ORDINAL)
    private SignatureJwsAlgorithm idTokenSignatureAlgorithmJwsAlgorithm = SignatureJwsAlgorithm.RS256;
    /* --- TokenSettings End --- */

    public abstract Set<OAuth2Scope> getScopes();

    @Override
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public Boolean getRequireProofKey() {
        return requireProofKey;
    }

    public void setRequireProofKey(Boolean requireProofKey) {
        this.requireProofKey = requireProofKey;
    }

    public Boolean getRequireAuthorizationConsent() {
        return requireAuthorizationConsent;
    }

    public void setRequireAuthorizationConsent(Boolean requireAuthorizationConsent) {
        this.requireAuthorizationConsent = requireAuthorizationConsent;
    }

    public String getJwkSetUrl() {
        return jwkSetUrl;
    }

    public void setJwkSetUrl(String jwkSetUrl) {
        this.jwkSetUrl = jwkSetUrl;
    }

    public AllJwsAlgorithm getAuthenticationSigningAlgorithm() {
        return authenticationSigningAlgorithm;
    }

    public void setAuthenticationSigningAlgorithm(AllJwsAlgorithm authenticationSigningAlgorithm) {
        this.authenticationSigningAlgorithm = authenticationSigningAlgorithm;
    }

    public Duration getAuthorizationCodeValidity() {
        return authorizationCodeValidity;
    }

    public void setAuthorizationCodeValidity(Duration authorizationCodeValidity) {
        this.authorizationCodeValidity = authorizationCodeValidity;
    }

//    public void setAuthorizationCodeValidity(String authorizationCodeValidity) {
//        this.authorizationCodeValidity = Duration.parse(authorizationCodeValidity);
//    }

    public Duration getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Duration accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

//    public void setAccessTokenValidity(String accessTokenValidity) {
//        this.accessTokenValidity = Duration.parse(accessTokenValidity);
//    }

    public Duration getDeviceCodeValidity() {
        return deviceCodeValidity;
    }

    public void setDeviceCodeValidity(Duration deviceCodeValidity) {
        this.deviceCodeValidity = deviceCodeValidity;
    }

//    public void setDeviceCodeValidity(String deviceCodeValidity) {
//        this.deviceCodeValidity = Duration.parse(deviceCodeValidity);
//    }

    public Duration getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Duration refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }
//    public void setRefreshTokenValidity(String refreshTokenValidity) {
//        this.refreshTokenValidity = Duration.parse(refreshTokenValidity);
//    }

    public TokenFormat getAccessTokenFormat() {
        return accessTokenFormat;
    }

    public void setAccessTokenFormat(TokenFormat accessTokenFormat) {
        this.accessTokenFormat = accessTokenFormat;
    }

    public Boolean getReuseRefreshTokens() {
        return reuseRefreshTokens;
    }

    public void setReuseRefreshTokens(Boolean reuseRefreshTokens) {
        this.reuseRefreshTokens = reuseRefreshTokens;
    }

    public SignatureJwsAlgorithm getIdTokenSignatureAlgorithm() {
        return idTokenSignatureAlgorithmJwsAlgorithm;
    }

    public void setIdTokenSignatureAlgorithm(SignatureJwsAlgorithm idTokenSignatureAlgorithmJwsAlgorithm) {
        this.idTokenSignatureAlgorithmJwsAlgorithm = idTokenSignatureAlgorithmJwsAlgorithm;
    }
}
