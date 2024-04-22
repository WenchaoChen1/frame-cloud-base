// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================
package com.gstdev.cloud.oauth2.core.definition.details;

import com.gstdev.cloud.base.core.utils.http.SessionUtils;
import com.gstdev.cloud.oauth2.core.utils.SymmetricUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * <p>Description: 表单登录 Details 定义 </p>
 *
 * @author : cc
 * @date : 2022/4/12 10:32
 */
public class FormLoginWebAuthenticationDetails extends WebAuthenticationDetails {

  /**
   * 验证码是否关闭
   */
  private final Boolean closed;
  /**
   * 请求中，验证码对应的表单参数名。对应UI Properties 里面的 captcha parameter
   */
  private final String parameterName;
  /**
   * 验证码分类
   */
  private final String category;
  private String code = null;
  private String identity = null;

  public FormLoginWebAuthenticationDetails(String remoteAddress, String sessionId, Boolean closed, String parameterName, String category, String code, String identity) {
    super(remoteAddress, sessionId);
    this.closed = closed;
    this.parameterName = parameterName;
    this.category = category;
    this.code = code;
    this.identity = identity;
  }

  public FormLoginWebAuthenticationDetails(HttpServletRequest request, boolean closed, String parameterName, String category) {
    super(request);
    this.closed = closed;
    this.parameterName = parameterName;
    this.category = category;
    this.init(request);
  }

  private void init(HttpServletRequest request) {
    String encryptedCode = request.getParameter(parameterName);
    String key = request.getParameter("symmetric");

    this.identity = SessionUtils.analyseSessionId(request);

    if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(encryptedCode)) {
      byte[] byteKey = SymmetricUtils.getDecryptedSymmetricKey(key);
      this.code = SymmetricUtils.decrypt(encryptedCode, byteKey);
    }
  }

  public Boolean getClosed() {
    return closed;
  }

  public String getParameterName() {
    return parameterName;
  }

  public String getCategory() {
    return category;
  }

  public String getCode() {
    return code;
  }

  public String getIdentity() {
    return identity;
  }
}
