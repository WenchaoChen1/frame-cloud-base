package com.gstdev.cloud.oauth2.data.jpa.converter;

import com.gstdev.cloud.oauth2.data.jpa.definition.converter.AbstractRegisteredClientConverter;
import com.gstdev.cloud.oauth2.data.jpa.entity.HerodotusRegisteredClient;
import com.gstdev.cloud.oauth2.data.jpa.jackson2.OAuth2JacksonProcessor;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * <p>Description: HerodotusRegisteredClient 转 换适配器 </p>
 *
 * @author : cc
 * @date : 2023/5/12 23:56
 */
public class HerodotusToOAuth2RegisteredClientConverter extends AbstractRegisteredClientConverter<HerodotusRegisteredClient> {

  public HerodotusToOAuth2RegisteredClientConverter(OAuth2JacksonProcessor jacksonProcessor) {
    super(jacksonProcessor);
  }

  @Override
  public Set<String> getScopes(HerodotusRegisteredClient details) {
    return StringUtils.commaDelimitedListToSet(details.getScopes());
  }

  @Override
  public ClientSettings getClientSettings(HerodotusRegisteredClient details) {
    Map<String, Object> clientSettingsMap = parseMap(details.getClientSettings());
    return ClientSettings.withSettings(clientSettingsMap).build();
  }

  @Override
  public TokenSettings getTokenSettings(HerodotusRegisteredClient details) {
    Map<String, Object> tokenSettingsMap = parseMap(details.getTokenSettings());
    return TokenSettings.withSettings(tokenSettingsMap).build();
  }
}
