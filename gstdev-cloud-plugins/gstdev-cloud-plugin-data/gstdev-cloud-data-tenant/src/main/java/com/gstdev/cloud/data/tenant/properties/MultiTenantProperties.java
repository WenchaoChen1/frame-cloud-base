//package com.gstdev.cloud.data.tenant.properties;
//
//import com.gstdev.cloud.data.core.constants.DataConstants;
//import com.gstdev.cloud.data.core.enums.MultiTenantApproach;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
///**
// * <p>Description: 自定义 JPA 配置 </p>
// *
// * @author : cc
// * @date : 2022/9/8 18:22
// */
//@ConfigurationProperties(prefix = DataConstants.PROPERTY_PREFIX_MULTI_TENANT)
//public class MultiTenantProperties {
//
//    /**
//     * 多租户数据源扫描包
//     */
//    private String[] packageToScan = new String[]{"com.gstdev.cloud", "cn.herodotus.professional"};
//
//    /**
//     * 多租户模式，默认：discriminator
//     */
//    private MultiTenantApproach approach = MultiTenantApproach.DISCRIMINATOR;
//
//    public String[] getPackageToScan() {
//        return packageToScan;
//    }
//
//    public void setPackageToScan(String[] packageToScan) {
//        this.packageToScan = packageToScan;
//    }
//
//    public MultiTenantApproach getApproach() {
//        return approach;
//    }
//
//    public void setApproach(MultiTenantApproach approach) {
//        this.approach = approach;
//    }
//}
