package com.gstdev.cloud.oauth2.data.jpa.jackson2;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.gstdev.cloud.commons.ass.core.json.jackson2.Jackson2Constants;
import com.gstdev.cloud.oauth2.core.definition.details.FormLoginWebAuthenticationDetails;
import com.gstdev.cloud.oauth2.core.definition.domain.HerodotusGrantedAuthority;
import com.gstdev.cloud.oauth2.core.definition.domain.HerodotusUser;
import org.springframework.security.jackson2.SecurityJackson2Modules;

/**
 * <p>Description: 自定义 User Details Module </p>
 *
 * @author : cc
 * @date : 2022/2/17 23:39
 */
public class HerodotusJackson2Module extends SimpleModule {

    public HerodotusJackson2Module() {
        super(HerodotusJackson2Module.class.getName(), Jackson2Constants.VERSION);
    }

    @Override
    public void setupModule(SetupContext context) {
        SecurityJackson2Modules.enableDefaultTyping(context.getOwner());
        context.setMixInAnnotations(HerodotusUser.class, HerodotusUserMixin.class);
        context.setMixInAnnotations(HerodotusGrantedAuthority.class, HerodotusGrantedAuthorityMixin.class);
        context.setMixInAnnotations(FormLoginWebAuthenticationDetails.class, FormLoginWebAuthenticationDetailsMixin.class);
    }
}
