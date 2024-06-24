package com.gstdev.cloud.oauth2.data.jpa.jackson2;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.gstdev.cloud.base.core.json.jackson2.Jackson2Constants;
import com.gstdev.cloud.oauth2.core.definition.details.FormLoginWebAuthenticationDetails;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.oauth2.core.definition.domain.FrameGrantedAuthority;
import org.springframework.security.jackson2.SecurityJackson2Modules;

/**
 * <p>Description: 自定义 User Details Module </p>
 *
 * @author : cc
 * @date : 2022/2/17 23:39
 */
public class FrameJackson2Module extends SimpleModule {

    public FrameJackson2Module() {
        super(FrameJackson2Module.class.getName(), Jackson2Constants.VERSION);
    }

    @Override
    public void setupModule(SetupContext context) {
        SecurityJackson2Modules.enableDefaultTyping(context.getOwner());
        context.setMixInAnnotations(DefaultSecurityUser.class, FrameUserMixin.class);
        context.setMixInAnnotations(FrameGrantedAuthority.class, FrameGrantedAuthorityMixin.class);
        context.setMixInAnnotations(FormLoginWebAuthenticationDetails.class, FormLoginWebAuthenticationDetailsMixin.class);
    }
}
