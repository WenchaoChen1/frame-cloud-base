package com.gstdev.cloud.oauth2.data.jpa.definition.converter;

import com.gstdev.cloud.oauth2.core.definition.domain.RegisteredClientDetails;
import com.gstdev.cloud.oauth2.data.jpa.jackson2.OAuth2JacksonProcessor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

/**
 * <p>Description: RegisteredClient 转换器</p>
 *
 * @author : cc
 * @date : 2023/5/12 23:07
 */
public abstract class AbstractRegisteredClientConverter<S extends RegisteredClientDetails> extends AbstractOAuth2EntityConverter<S, RegisteredClient> implements RegisteredClientConverter<S> {

  public AbstractRegisteredClientConverter(OAuth2JacksonProcessor jacksonProcessor) {
    super(jacksonProcessor);
  }

  @Override
  public RegisteredClient convert(S details) {
    return RegisteredClientConverter.super.convert(details);
  }
}
