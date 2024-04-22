package com.gstdev.cloud.oauth2.authorization.server.properties;

import com.google.common.base.MoreObjects;
import com.gstdev.cloud.base.definition.constants.DefaultConstants;
import com.gstdev.cloud.base.definition.constants.SymbolConstants;
import com.gstdev.cloud.oauth2.core.constants.OAuth2Constants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;

import java.time.Duration;

/**
 * @program: frame-cloud-base
 * @description: OAuth2 合规性配置参数
 * @author: wenchao.chen
 * @create: 2024/03/25 13:12
 **/

@ConfigurationProperties(prefix = OAuth2Constants.PROPERTY_OAUTH2_AUTHENTICATION)
public class OAuth2AuthenticationProperties {
  /**
   * 开启登录失败限制
   */
  private SignInFailureLimited signInFailureLimited = new SignInFailureLimited();

  /**
   * 同一终端登录限制
   */
  private SignInEndpointLimited signInEndpointLimited = new SignInEndpointLimited();

  /**
   * 账户踢出限制
   */
  private SignInKickOutLimited signInKickOutLimited = new SignInKickOutLimited();

  private FormLogin formLogin = new FormLogin();

  private AuthorizationServerSettings authorizationServerSettings = new AuthorizationServerSettings();

  public SignInEndpointLimited getSignInEndpointLimited() {
    return signInEndpointLimited;
  }

  public void setSignInEndpointLimited(SignInEndpointLimited signInEndpointLimited) {
    this.signInEndpointLimited = signInEndpointLimited;
  }

  public SignInFailureLimited getSignInFailureLimited() {
    return signInFailureLimited;
  }

  public void setSignInFailureLimited(SignInFailureLimited signInFailureLimited) {
    this.signInFailureLimited = signInFailureLimited;
  }

  public SignInKickOutLimited getSignInKickOutLimited() {
    return signInKickOutLimited;
  }

  public void setSignInKickOutLimited(SignInKickOutLimited signInKickOutLimited) {
    this.signInKickOutLimited = signInKickOutLimited;
  }

  public FormLogin getFormLogin() {
    return formLogin;
  }

  public void setFormLogin(FormLogin formLogin) {
    this.formLogin = formLogin;
  }

  public AuthorizationServerSettings getAuthorizationServerSettings() {
    return authorizationServerSettings;
  }

  public void setAuthorizationServerSettings(AuthorizationServerSettings authorizationServerSettings) {
    this.authorizationServerSettings = authorizationServerSettings;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("signInEndpointLimited", signInEndpointLimited)
      .add("signInFailureLimited", signInFailureLimited)
      .add("signInKickOutLimited", signInKickOutLimited)
      .toString();
  }

  public static class SignInFailureLimited {
    /**
     * 是否开启登录失败检测，默认开启
     */
    private Boolean enabled = true;

    /**
     * 允许允许最大失败次数
     */
    private Integer maxTimes = 5;

    /**
     * 是否自动解锁被锁定用户，默认开启
     */
    private Boolean autoUnlock = true;

    /**
     * 记录失败次数的缓存过期时间，默认：2小时。
     */
    private Duration expire = Duration.ofHours(2);

    public Boolean getEnabled() {
      return enabled;
    }

    public void setEnabled(Boolean enabled) {
      this.enabled = enabled;
    }

    public Integer getMaxTimes() {
      return maxTimes;
    }

    public void setMaxTimes(Integer maxTimes) {
      this.maxTimes = maxTimes;
    }

    public Duration getExpire() {
      return expire;
    }

    public void setExpire(Duration expire) {
      this.expire = expire;
    }

    public Boolean getAutoUnlock() {
      return autoUnlock;
    }

    public void setAutoUnlock(Boolean autoUnlock) {
      this.autoUnlock = autoUnlock;
    }

    @Override
    public String toString() {
      return MoreObjects.toStringHelper(this)
        .add("enabled", enabled)
        .add("maxTimes", maxTimes)
        .add("autoUnlock", autoUnlock)
        .add("expire", expire)
        .toString();
    }
  }

  public static class SignInEndpointLimited {
    /**
     * 同一终端登录限制是否开启，默认开启。
     */
    private Boolean enabled = false;

    /**
     * 统一终端，允许同时登录的最大数量
     */
    private Integer maximum = 1;

    public Boolean getEnabled() {
      return enabled;
    }

    public void setEnabled(Boolean enabled) {
      this.enabled = enabled;
    }

    public Integer getMaximum() {
      return maximum;
    }

    public void setMaximum(Integer maximum) {
      this.maximum = maximum;
    }

    @Override
    public String toString() {
      return MoreObjects.toStringHelper(this)
        .add("enabled", enabled)
        .add("maximum", maximum)
        .toString();
    }
  }

  public static class SignInKickOutLimited {
    /**
     * 是否开启 Session 踢出功能，默认开启
     */
    private Boolean enabled = true;

    public Boolean getEnabled() {
      return enabled;
    }

    public void setEnabled(Boolean enabled) {
      this.enabled = enabled;
    }

    @Override
    public String toString() {
      return MoreObjects.toStringHelper(this)
        .add("enabled", enabled)
        .toString();
    }
  }

  public static class FormLogin {
    /**
     * UI 界面用户名标输入框 name 属性值
     */
    private String usernameParameter = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;
    /**
     * UI 界面密码标输入框 name 属性值
     */
    private String passwordParameter = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
    /**
     * UI 界面Remember Me name 属性值
     */
    private String rememberMeParameter = AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY;
    /**
     * UI 界面验证码 name 属性值
     */
    private String captchaParameter = "captcha";
    /**
     * 登录页面地址
     */
    private String loginPageUrl = DefaultLoginPageGeneratingFilter.DEFAULT_LOGIN_PAGE_URL;
    /**
     * 登录失败重定向地址
     */
    private String failureForwardUrl = loginPageUrl + SymbolConstants.QUESTION + DefaultLoginPageGeneratingFilter.ERROR_PARAMETER_NAME;
    /**
     * 登录成功重定向地址
     */
    private String successForwardUrl;
    /**
     * 关闭验证码显示，默认 false，显示
     */
    private Boolean closeCaptcha = false;
    /**
     * 验证码类别，默认为 Hutool Gif 类型
     */
    private String category = "HUTOOL_GIF";

    public String getUsernameParameter() {
      return usernameParameter;
    }

    public void setUsernameParameter(String usernameParameter) {
      this.usernameParameter = usernameParameter;
    }

    public String getPasswordParameter() {
      return passwordParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
      this.passwordParameter = passwordParameter;
    }

    public String getRememberMeParameter() {
      return rememberMeParameter;
    }

    public void setRememberMeParameter(String rememberMeParameter) {
      this.rememberMeParameter = rememberMeParameter;
    }

    public String getCaptchaParameter() {
      return captchaParameter;
    }

    public void setCaptchaParameter(String captchaParameter) {
      this.captchaParameter = captchaParameter;
    }

    public String getLoginPageUrl() {
      return loginPageUrl;
    }

    public void setLoginPageUrl(String loginPageUrl) {
      this.loginPageUrl = loginPageUrl;
    }

    public String getFailureForwardUrl() {
      return failureForwardUrl;
    }

    public void setFailureForwardUrl(String failureForwardUrl) {
      this.failureForwardUrl = failureForwardUrl;
    }

    public String getSuccessForwardUrl() {
      return successForwardUrl;
    }

    public void setSuccessForwardUrl(String successForwardUrl) {
      this.successForwardUrl = successForwardUrl;
    }

    public Boolean getCloseCaptcha() {
      return closeCaptcha;
    }

    public void setCloseCaptcha(Boolean closeCaptcha) {
      this.closeCaptcha = closeCaptcha;
    }

    public String getCategory() {
      return category;
    }

    public void setCategory(String category) {
      this.category = category;
    }

    @Override
    public String toString() {
      return MoreObjects.toStringHelper(this)
        .add("usernameParameter", usernameParameter)
        .add("passwordParameter", passwordParameter)
        .add("rememberMeParameter", rememberMeParameter)
        .add("captchaParameter", captchaParameter)
        .add("loginPageUrl", loginPageUrl)
        .add("failureForwardUrl", failureForwardUrl)
        .add("successForwardUrl", successForwardUrl)
        .add("closeCaptcha", closeCaptcha)
        .add("category", category)
        .toString();
    }
  }


  public static class AuthorizationServerSettings {
    //AuthorizationServerSettings
    private String issuerUri;
    private String authorizationEndpoint = DefaultConstants.DEFAULT_AUTHORIZATION_ENDPOINT;
    private String deviceAuthorizationEndpoint = DefaultConstants.DEFAULT_DEVICE_AUTHORIZATION_ENDPOINT;
    private String deviceVerificationEndpoint = DefaultConstants.DEFAULT_DEVICE_VERIFICATION_ENDPOINT;
    private String tokenEndpoint = DefaultConstants.DEFAULT_TOKEN_ENDPOINT;
    private String tokenIntrospectionEndpoint = DefaultConstants.DEFAULT_TOKEN_REVOCATION_ENDPOINT;
    private String tokenRevocationEndpoint = DefaultConstants.DEFAULT_TOKEN_INTROSPECTION_ENDPOINT;
    private String jwkSetEndpoint = DefaultConstants.DEFAULT_JWK_SET_ENDPOINT;
    private String oidcLogoutEndpoint = DefaultConstants.DEFAULT_OIDC_LOGOUNT_ENDPOINT;
    private String oidcUserInfoEndpoint = DefaultConstants.DEFAULT_OIDC_USER_INFO_ENDPOINT;
    private String oidcClientRegistrationEndpoint = DefaultConstants.DEFAULT_OIDC_CLIENT_REGISTRATION_ENDPOINT;
    private String jksJksPath = DefaultConstants.DEFAULT_JKS_JKS_PATH;
    private String jksCerPath = DefaultConstants.DEFAULT_JKS_CER_PATH;
    private String jksAlias = DefaultConstants.DEFAULT_JKS_ALIAS;
    private String jksPass = DefaultConstants.DEFAULT_JKS_PASS;
    public AuthorizationServerSettings() {

    }

    public String getIssuerUri() {
      return issuerUri;
    }

    public void setIssuerUri(String issuerUri) {
      this.issuerUri = issuerUri;
    }

    public String getAuthorizationEndpoint() {
      return authorizationEndpoint;
    }

    public void setAuthorizationEndpoint(String authorizationEndpoint) {
      this.authorizationEndpoint = authorizationEndpoint;
    }

    public String getDeviceAuthorizationEndpoint() {
      return deviceAuthorizationEndpoint;
    }

    public void setDeviceAuthorizationEndpoint(String deviceAuthorizationEndpoint) {
      this.deviceAuthorizationEndpoint = deviceAuthorizationEndpoint;
    }

    public String getDeviceVerificationEndpoint() {
      return deviceVerificationEndpoint;
    }

    public void setDeviceVerificationEndpoint(String deviceVerificationEndpoint) {
      this.deviceVerificationEndpoint = deviceVerificationEndpoint;
    }

    public String getTokenEndpoint() {
      return tokenEndpoint;
    }

    public void setTokenEndpoint(String tokenEndpoint) {
      this.tokenEndpoint = tokenEndpoint;
    }

    public String getTokenIntrospectionEndpoint() {
      return tokenIntrospectionEndpoint;
    }

    public void setTokenIntrospectionEndpoint(String tokenIntrospectionEndpoint) {
      this.tokenIntrospectionEndpoint = tokenIntrospectionEndpoint;
    }

    public String getTokenRevocationEndpoint() {
      return tokenRevocationEndpoint;
    }

    public void setTokenRevocationEndpoint(String tokenRevocationEndpoint) {
      this.tokenRevocationEndpoint = tokenRevocationEndpoint;
    }

    public String getJwkSetEndpoint() {
      return jwkSetEndpoint;
    }

    public void setJwkSetEndpoint(String jwkSetEndpoint) {
      this.jwkSetEndpoint = jwkSetEndpoint;
    }

    public String getOidcLogoutEndpoint() {
      return oidcLogoutEndpoint;
    }

    public void setOidcLogoutEndpoint(String oidcLogoutEndpoint) {
      this.oidcLogoutEndpoint = oidcLogoutEndpoint;
    }

    public String getOidcUserInfoEndpoint() {
      return oidcUserInfoEndpoint;
    }

    public void setOidcUserInfoEndpoint(String oidcUserInfoEndpoint) {
      this.oidcUserInfoEndpoint = oidcUserInfoEndpoint;
    }

    public String getOidcClientRegistrationEndpoint() {
      return oidcClientRegistrationEndpoint;
    }

    public void setOidcClientRegistrationEndpoint(String oidcClientRegistrationEndpoint) {
      this.oidcClientRegistrationEndpoint = oidcClientRegistrationEndpoint;
    }

    public String getJksJksPath() {
      return jksJksPath;
    }

    public void setJksJksPath(String jksJksPath) {
      this.jksJksPath = jksJksPath;
    }

    public String getJksCerPath() {
      return jksCerPath;
    }

    public void setJksCerPath(String jksCerPath) {
      this.jksCerPath = jksCerPath;
    }

    public String getJksAlias() {
      return jksAlias;
    }

    public void setJksAlias(String jksAlias) {
      this.jksAlias = jksAlias;
    }

    public String getJksPass() {
      return jksPass;
    }

    public void setJksPass(String jksPass) {
      this.jksPass = jksPass;
    }
  }

}
