package com.gstdev.cloud.oauth2.data.jpa.converter;

import com.gstdev.cloud.oauth2.data.jpa.definition.converter.AbstractRegisteredClientConverter;
import com.gstdev.cloud.oauth2.data.jpa.entity.FrameRegisteredClient;
import com.gstdev.cloud.oauth2.data.jpa.jackson2.OAuth2JacksonProcessor;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * <p>Description: FrameRegisteredClient 转 换适配器 </p>
 *
 * @author : cc
 * @date : 2023/5/12 23:56
 */
public class FrameToOAuth2RegisteredClientConverter extends AbstractRegisteredClientConverter<FrameRegisteredClient> {

  public FrameToOAuth2RegisteredClientConverter(OAuth2JacksonProcessor jacksonProcessor) {
    super(jacksonProcessor);
  }

  @Override
  public Set<String> getScopes(FrameRegisteredClient details) {
    return StringUtils.commaDelimitedListToSet(details.getScopes());
  }

  @Override
  public ClientSettings getClientSettings(FrameRegisteredClient details) {
    Map<String, Object> clientSettingsMap = parseMap(details.getClientSettings());
    return ClientSettings.withSettings(clientSettingsMap).build();
  }

  @Override
  public TokenSettings getTokenSettings(FrameRegisteredClient details) {
    Map<String, Object> tokenSettingsMap = parseMap(details.getTokenSettings());
    return TokenSettings.withSettings(tokenSettingsMap).build();
  }
}
