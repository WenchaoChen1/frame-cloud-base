package com.gstdev.cloud.oauth2.data.jpa.jackson2;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gstdev.cloud.oauth2.core.jackson2.FrameUserDeserializer;

/**
 * <p>Description: 自定义 UserDetails Mixin </p>
 * <p>
 * This mixin class helps in serialize/deserialize {@link org.springframework.security.core.userdetails.User}. This class also register a custom deserializer UserDeserializer to deserialize User object successfully. In order to use this mixin you need to register two more mixin classes in your ObjectMapper configuration.
 * SimpleGrantedAuthorityMixin
 * UnmodifiableSetMixin
 * ObjectMapper mapper = new ObjectMapper();
 * mapper.registerModule(new CoreJackson2Module());
 * <p>
 * See Also: UserDeserializer, CoreJackson2Module, SecurityJackson2Modules
 *
 * @author : cc
 * @date : 2022/2/17 21:57
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonDeserialize(using = FrameUserDeserializer.class)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class FrameUserMixin {
}
