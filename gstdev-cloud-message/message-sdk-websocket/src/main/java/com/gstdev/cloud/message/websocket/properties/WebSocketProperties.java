package com.gstdev.cloud.message.websocket.properties;

import com.gstdev.cloud.base.definition.constants.SymbolConstants;
import com.gstdev.cloud.base.core.utils.http.HeaderUtils;
import com.gstdev.cloud.message.core.constants.MessageConstants;
import com.gstdev.cloud.message.websocket.enums.InstanceMode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

/**
 * <p>Description: Web Socket 配置 </p>
 *
 * @author : cc
 * @date : 2021/10/24 18:38
 */
@ConfigurationProperties(prefix = MessageConstants.PROPERTY_PREFIX_WEBSOCKET)
public class WebSocketProperties {

  /**
   * WebSocket 实例模式，单实例还是多实例。默认为单实例
   */
  private InstanceMode mode = InstanceMode.SINGLE;
  /**
   * 客户端尝试连接端点
   */
  private String endpoint = "stomp/ws";

  /**
   * 全局使用的消息前缀
   */
  private List<String> applicationDestinationPrefixes = Collections.singletonList("/app");

  /**
   * 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
   */
  private String userDestinationPrefix = "/user";

  /**
   * 集群模式下，信息同步消息队列Topic
   */
  private String topic = "ws";

  /**
   * 请求中传递的用户身份标识属性名
   */
  private String principalHeader = HeaderUtils.X_FRAME_OPEN_ID;

  public InstanceMode getMode() {
    return mode;
  }

  public void setMode(InstanceMode mode) {
    this.mode = mode;
  }

  private String format(String endpoint) {
    if (StringUtils.isNotBlank(endpoint) && !StringUtils.startsWith(endpoint, SymbolConstants.FORWARD_SLASH)) {
      return SymbolConstants.FORWARD_SLASH + endpoint;
    } else {
      return endpoint;
    }
  }

  public String getEndpoint() {
    return format(endpoint);
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public List<String> getApplicationDestinationPrefixes() {
    return applicationDestinationPrefixes;
  }

  public void setApplicationDestinationPrefixes(List<String> applicationDestinationPrefixes) {
    this.applicationDestinationPrefixes = applicationDestinationPrefixes;
  }

  public String[] getApplicationPrefixes() {
    List<String> prefixes = this.getApplicationDestinationPrefixes();
    if (CollectionUtils.isNotEmpty(prefixes)) {
      List<String> wellFormed = prefixes.stream().map(this::format).toList();
      String[] result = new String[wellFormed.size()];
      return wellFormed.toArray(result);
    } else {
      return new String[]{};
    }
  }

  public String getUserDestinationPrefix() {
    return format(userDestinationPrefix);
  }

  public void setUserDestinationPrefix(String userDestinationPrefix) {
    this.userDestinationPrefix = userDestinationPrefix;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getPrincipalHeader() {
    return principalHeader;
  }

  public void setPrincipalHeader(String principalHeader) {
    this.principalHeader = principalHeader;
  }
}
