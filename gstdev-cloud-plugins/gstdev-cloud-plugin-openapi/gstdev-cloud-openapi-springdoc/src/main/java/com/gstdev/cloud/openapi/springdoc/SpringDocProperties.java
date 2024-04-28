package com.gstdev.cloud.openapi.springdoc;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "gstdev.cloud.swagger")
public class SpringDocProperties {

    private String title = SpringDocDefaults.TITLE;

    private String description = SpringDocDefaults.DESCRIPTION;

    private String version = SpringDocDefaults.VERSION;

    private String termsOfServiceUrl = SpringDocDefaults.TERMS_OF_SERVICE_URL;

    private String contactName = SpringDocDefaults.CONTACT_NAME;

    private String contactUrl = SpringDocDefaults.CONTACT_URL;

    private String contactEmail = SpringDocDefaults.CONTACT_EMAIL;

    private String license = SpringDocDefaults.LICENSE;

    private String licenseUrl = SpringDocDefaults.LICENSE_URL;

    private String defaultIncludePattern = SpringDocDefaults.DEFAULT_INCLUDE_PATTERN;

    private String host = SpringDocDefaults.HOST;

    private String[] protocols = SpringDocDefaults.PROTOCOLS;

    private boolean useDefaultResponseMessages = SpringDocDefaults.USE_DEFAULT_RESPONSE_MESSAGES;

    private boolean enabled = SpringDocDefaults.ENABLED;
}
