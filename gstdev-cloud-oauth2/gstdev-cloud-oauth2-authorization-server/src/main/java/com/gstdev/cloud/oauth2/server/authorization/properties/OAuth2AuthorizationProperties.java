package com.gstdev.cloud.oauth2.server.authorization.properties;


import java.util.ArrayList;
import java.util.List;

import com.google.common.base.MoreObjects;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "gstdev.cloud.oauth2.authorization")
public class OAuth2AuthorizationProperties {
  private Target validate;
  private Boolean strict;
  private Jwk jwk;
  private Matcher matcher;

  public OAuth2AuthorizationProperties() {
//    this.validate = Target.REMOTE;
    this.strict = true;
    this.jwk = new Jwk();
    this.matcher = new Matcher();
  }
  @Setter
  @Getter
  public static class Jwk {
    private Certificate certificate;
    private String jksKeyStore;
    private String jksKeyPassword;
    private String jksStorePassword;
    private String jksKeyAlias;

    public Jwk() {
      this.certificate = Certificate.CUSTOM;
      this.jksKeyStore = "classpath*:certificate/herodotus-cloud.jks";
      this.jksKeyPassword = "Herodotus-Cloud";
      this.jksStorePassword = "Herodotus-Cloud";
      this.jksKeyAlias = "herodotus-cloud";
    }

    public String toString() {
      return MoreObjects.toStringHelper(this).add("certificate", this.certificate).add("jksKeyStore", this.jksKeyStore).add("jksKeyPassword", this.jksKeyPassword).add("jksStorePassword", this.jksStorePassword).add("jksKeyAlias", this.jksKeyAlias).toString();
    }

    private static enum Strategy {
      STANDARD,
      CUSTOM;

      private Strategy() {
      }
    }
  }

  @Setter
  @Getter
  public static class Matcher {
    private List<String> staticResources;
    private List<String> permitAll;
    private List<String> hasAuthenticated;
    // TODO
    private List<String> whitelist = new ArrayList<>();
    public Matcher() {
    }

  }
}
