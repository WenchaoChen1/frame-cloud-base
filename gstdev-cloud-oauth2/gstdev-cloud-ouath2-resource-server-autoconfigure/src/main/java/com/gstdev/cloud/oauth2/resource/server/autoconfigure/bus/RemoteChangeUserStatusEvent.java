package com.gstdev.cloud.oauth2.resource.server.autoconfigure.bus;


/**
 * <p>Description: 修改用户状态远程事件 </p>
 *
 * @author : cc
 * @date : 2022/7/10 16:13
 */
//public class RemoteChangeUserStatusEvent extends RemoteApplicationEvent {
public class RemoteChangeUserStatusEvent {

  private String data;

  public RemoteChangeUserStatusEvent() {
    super();
  }

  public RemoteChangeUserStatusEvent(String data, String originService, String destinationService) {
//        super(data, originService, DEFAULT_DESTINATION_FACTORY.getDestination(destinationService));
    this.data = data;
  }

  public String getData() {
    return data;
  }
}
