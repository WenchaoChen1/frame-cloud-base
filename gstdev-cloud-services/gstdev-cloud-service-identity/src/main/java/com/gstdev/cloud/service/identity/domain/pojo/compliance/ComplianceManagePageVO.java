package com.gstdev.cloud.service.identity.domain.pojo.compliance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ComplianceManagePageVO {
    private String complianceId;
    private String principalName;
    private String clientId;
    private String ip;
    private String osName;
    private Boolean mobile;
    private String browserName;
    private Boolean mobileBrowser;
    private String engineName;
    private Boolean mobilePlatform;
    private Boolean iphoneOrIpod;
    private Boolean ipad;
    private Boolean ios;
    private Boolean android;
    private String operation;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;
}
