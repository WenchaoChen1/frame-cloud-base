//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev <support@gstdev.com>
//// Copyright (c) 2020-2025 gstdev.com
////
//// ====================================================
//package com.gstdev.cloud.oauth2.core.definition.domain;
//
//import com.google.common.base.MoreObjects;
//import com.google.common.base.Objects;
//
//import java.io.Serializable;
//
///**
// * <p>Description: Security Metadata 传输数据实体 </p>
// *
// * @author : cc
// * @date : 2021/8/8 15:51
// */
//public class SecurityAttribute implements Serializable {
//
//    private String attributeId;
//
//    private String attributeCode;
//
//    private String attributeName;
//
//    private String webExpression;
//
//    private String permissions;
//
//    private String url;
//
//    private String requestMethod;
//
//    private String serviceId;
//
//    public SecurityAttribute() {
//    }
//
//    public String getAttributeId() {
//        return attributeId;
//    }
//
//    public void setAttributeId(String attributeId) {
//        this.attributeId = attributeId;
//    }
//
//    public String getAttributeCode() {
//        return attributeCode;
//    }
//
//    public void setAttributeCode(String attributeCode) {
//        this.attributeCode = attributeCode;
//    }
//
//    public String getAttributeName() {
//        return attributeName;
//    }
//
//    public void setAttributeName(String attributeName) {
//        this.attributeName = attributeName;
//    }
//
//    public String getWebExpression() {
//        return webExpression;
//    }
//
//    public void setWebExpression(String webExpression) {
//        this.webExpression = webExpression;
//    }
//
//    public String getPermissions() {
//        return permissions;
//    }
//
//    public void setPermissions(String permissions) {
//        this.permissions = permissions;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getRequestMethod() {
//        return requestMethod;
//    }
//
//    public void setRequestMethod(String requestMethod) {
//        this.requestMethod = requestMethod;
//    }
//
//    public String getServiceId() {
//        return serviceId;
//    }
//
//    public void setServiceId(String serviceId) {
//        this.serviceId = serviceId;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        SecurityAttribute that = (SecurityAttribute) o;
//        return Objects.equal(attributeId, that.attributeId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(attributeId);
//    }
//
//    @Override
//    public String toString() {
//        return MoreObjects.toStringHelper(this)
//                .add("attributeId", attributeId)
//                .add("attributeCode", attributeCode)
//                .add("attributeName", attributeName)
//                .add("authorities", webExpression)
//                .add("permissions", permissions)
//                .add("url", url)
//                .add("requestMethod", requestMethod)
//                .add("serviceId", serviceId)
//                .toString();
//    }
//}
