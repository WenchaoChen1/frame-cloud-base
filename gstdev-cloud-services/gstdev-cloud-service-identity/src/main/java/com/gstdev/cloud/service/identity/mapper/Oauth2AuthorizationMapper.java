package com.gstdev.cloud.service.identity.mapper;

import com.gstdev.cloud.oauth2.data.jpa.entity.FrameAuthorization;
import com.gstdev.cloud.service.identity.domain.pojo.authorization.AuthorizationManagePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface Oauth2AuthorizationMapper  {

    List<AuthorizationManagePageVO> toAuthorizationManagePageVO(List<FrameAuthorization> oAuth2Authorization);

    default Page<AuthorizationManagePageVO> toAuthorizationManagePageVO(Page<FrameAuthorization> page) {
        List<AuthorizationManagePageVO> responses = this.toAuthorizationManagePageVO(page.getContent());
        return new PageImpl(responses, page.getPageable(), page.getTotalElements());
    }

}

