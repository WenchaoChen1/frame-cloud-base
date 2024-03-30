//package com.gstdev.cloud.oauth2.authorization.converter;
//
//import com.gstdev.cloud.commons.constant.BaseConstants;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//
///**
// * @program: frame-cloud-base
// * @description: 扩展的 JwtAuthenticationConverter
// * @author: wenchao.chen
// * @create: 2024/03/28 11:13
// **/
//public class CustomizeJwtAuthenticationConverter extends JwtAuthenticationConverter {
//
//  public CustomizeJwtAuthenticationConverter() {
//    CustomizeJwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new CustomizeJwtGrantedAuthoritiesConverter();
//    grantedAuthoritiesConverter.setAuthoritiesClaimName(BaseConstants.AUTHORITIES);
//
//    this.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
//  }
//}
