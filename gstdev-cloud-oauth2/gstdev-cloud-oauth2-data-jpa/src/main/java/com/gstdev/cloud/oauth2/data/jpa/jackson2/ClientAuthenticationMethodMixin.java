package com.gstdev.cloud.oauth2.data.jpa.jackson2;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * <p>Description: ClientAuthenticationMethodMixin </p>
 *
 * @author : cc
 * @date : 2022/10/24 15:50
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
public class ClientAuthenticationMethodMixin {

    @JsonCreator
    ClientAuthenticationMethodMixin(@JsonProperty("value") String value) {
    }
}
