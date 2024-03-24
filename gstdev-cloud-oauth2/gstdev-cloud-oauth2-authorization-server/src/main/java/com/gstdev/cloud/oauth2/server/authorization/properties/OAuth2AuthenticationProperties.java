package com.gstdev.cloud.oauth2.server.authorization.properties;


import com.google.common.base.MoreObjects;
import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gstdev.cloud.oauth2.authentication")
public class OAuth2AuthenticationProperties {
  private SignInFailureLimited signInFailureLimited = new SignInFailureLimited();
  private SignInEndpointLimited signInEndpointLimited = new SignInEndpointLimited();
  private SignInKickOutLimited signInKickOutLimited = new SignInKickOutLimited();
  private FormLogin formLogin = new FormLogin();

  public OAuth2AuthenticationProperties() {
  }

  public SignInEndpointLimited getSignInEndpointLimited() {
    return this.signInEndpointLimited;
  }

  public void setSignInEndpointLimited(SignInEndpointLimited signInEndpointLimited) {
    this.signInEndpointLimited = signInEndpointLimited;
  }

  public SignInFailureLimited getSignInFailureLimited() {
    return this.signInFailureLimited;
  }

  public void setSignInFailureLimited(SignInFailureLimited signInFailureLimited) {
    this.signInFailureLimited = signInFailureLimited;
  }

  public SignInKickOutLimited getSignInKickOutLimited() {
    return this.signInKickOutLimited;
  }

  public void setSignInKickOutLimited(SignInKickOutLimited signInKickOutLimited) {
    this.signInKickOutLimited = signInKickOutLimited;
  }

  public FormLogin getFormLogin() {
    return this.formLogin;
  }

  public void setFormLogin(FormLogin formLogin) {
    this.formLogin = formLogin;
  }

  public String toString() {
    return MoreObjects.toStringHelper(this).add("signInEndpointLimited", this.signInEndpointLimited).add("signInFailureLimited", this.signInFailureLimited).add("signInKickOutLimited", this.signInKickOutLimited).toString();
  }

  public static class SignInFailureLimited {
    private Boolean enabled = true;
    private Integer maxTimes = 5;
    private Boolean autoUnlock = true;
    private Duration expire = Duration.ofHours(2L);

    public SignInFailureLimited() {
    }

    public Boolean getEnabled() {
      return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
      this.enabled = enabled;
    }

    public Integer getMaxTimes() {
      return this.maxTimes;
    }

    public void setMaxTimes(Integer maxTimes) {
      this.maxTimes = maxTimes;
    }

    public Duration getExpire() {
      return this.expire;
    }

    public void setExpire(Duration expire) {
      this.expire = expire;
    }

    public Boolean getAutoUnlock() {
      return this.autoUnlock;
    }

    public void setAutoUnlock(Boolean autoUnlock) {
      this.autoUnlock = autoUnlock;
    }

    public String toString() {
      return MoreObjects.toStringHelper(this).add("enabled", this.enabled).add("maxTimes", this.maxTimes).add("autoUnlock", this.autoUnlock).add("expire", this.expire).toString();
    }
  }

  public static class SignInEndpointLimited {
    private Boolean enabled = false;
    private Integer maximum = 1;

    public SignInEndpointLimited() {
    }

    public Boolean getEnabled() {
      return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
      this.enabled = enabled;
    }

    public Integer getMaximum() {
      return this.maximum;
    }

    public void setMaximum(Integer maximum) {
      this.maximum = maximum;
    }

    public String toString() {
      return MoreObjects.toStringHelper(this).add("enabled", this.enabled).add("maximum", this.maximum).toString();
    }
  }

  public static class SignInKickOutLimited {
    private Boolean enabled = true;

    public SignInKickOutLimited() {
    }

    public Boolean getEnabled() {
      return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
      this.enabled = enabled;
    }

    public String toString() {
      return MoreObjects.toStringHelper(this).add("enabled", this.enabled).toString();
    }
  }

  public static class FormLogin {
    private String usernameParameter = "username";
    private String passwordParameter = "password";
    private String rememberMeParameter = "remember-me";
    private String captchaParameter = "captcha";
    private String loginPageUrl = "/login";
    private String failureForwardUrl;
    private String successForwardUrl;
    private Boolean closeCaptcha;
    private String category;

    public FormLogin() {
      this.failureForwardUrl = this.loginPageUrl + "?error";
      this.closeCaptcha = false;
      this.category = "HUTOOL_GIF";
    }

    public String getUsernameParameter() {
      return this.usernameParameter;
    }

    public void setUsernameParameter(String usernameParameter) {
      this.usernameParameter = usernameParameter;
    }

    public String getPasswordParameter() {
      return this.passwordParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
      this.passwordParameter = passwordParameter;
    }

    public String getRememberMeParameter() {
      return this.rememberMeParameter;
    }

    public void setRememberMeParameter(String rememberMeParameter) {
      this.rememberMeParameter = rememberMeParameter;
    }

    public String getCaptchaParameter() {
      return this.captchaParameter;
    }

    public void setCaptchaParameter(String captchaParameter) {
      this.captchaParameter = captchaParameter;
    }

    public String getLoginPageUrl() {
      return this.loginPageUrl;
    }

    public void setLoginPageUrl(String loginPageUrl) {
      this.loginPageUrl = loginPageUrl;
    }

    public String getFailureForwardUrl() {
      return this.failureForwardUrl;
    }

    public void setFailureForwardUrl(String failureForwardUrl) {
      this.failureForwardUrl = failureForwardUrl;
    }

    public String getSuccessForwardUrl() {
      return this.successForwardUrl;
    }

    public void setSuccessForwardUrl(String successForwardUrl) {
      this.successForwardUrl = successForwardUrl;
    }

    public Boolean getCloseCaptcha() {
      return this.closeCaptcha;
    }

    public void setCloseCaptcha(Boolean closeCaptcha) {
      this.closeCaptcha = closeCaptcha;
    }

    public String getCategory() {
      return this.category;
    }

    public void setCategory(String category) {
      this.category = category;
    }

    public String toString() {
      return MoreObjects.toStringHelper(this).add("usernameParameter", this.usernameParameter).add("passwordParameter", this.passwordParameter).add("rememberMeParameter", this.rememberMeParameter).add("captchaParameter", this.captchaParameter).add("loginPageUrl", this.loginPageUrl).add("failureForwardUrl", this.failureForwardUrl).add("successForwardUrl", this.successForwardUrl).add("closeCaptcha", this.closeCaptcha).add("category", this.category).toString();
    }
  }
}
