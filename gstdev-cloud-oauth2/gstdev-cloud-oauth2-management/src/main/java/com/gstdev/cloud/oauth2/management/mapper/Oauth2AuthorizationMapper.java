//package com.gstdev.cloud.oauth2.management.mapper;
//
//
//import com.gstdev.template.common.base.BaseMapper;
//import com.gstdev.cloud.oauth2.management.domain.entity.Oauth2Authorization;
//import com.gstdev.cloud.oauth2.management.domain.base.Oauth2AuthorizationDto;
//import com.gstdev.cloud.oauth2.management.domain.base.Oauth2AuthorizationInsertInput;
//import com.gstdev.cloud.oauth2.management.domain.base.Oauth2AuthorizationUpdateInput;
//import org.mapstruct.Mapper;
//import org.mapstruct.NullValueCheckStrategy;
//import org.mapstruct.NullValuePropertyMappingStrategy;
//import org.mapstruct.ReportingPolicy;
//
//
//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
//    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
//    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
//public interface Oauth2AuthorizationMapper extends BaseMapper<Oauth2Authorization, Oauth2AuthorizationDto, Oauth2AuthorizationInsertInput, Oauth2AuthorizationUpdateInput> {
//}
//
