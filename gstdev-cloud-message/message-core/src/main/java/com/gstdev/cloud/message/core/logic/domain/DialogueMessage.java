package com.gstdev.cloud.message.core.logic.domain;

import com.google.common.base.MoreObjects;
import com.gstdev.cloud.data.core.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 对话消息传递对象 </p>
 *
 * @author : cc
 * @date : 2023/3/11 18:38
 */
public class DialogueMessage extends BaseEntity {

  @Schema(name = "对话详情ID")
  private String detailId;

  @Schema(name = "接收人ID")
  private String receiverId;

  @Schema(name = "接收人名称", title = "冗余信息，增加该字段减少重复查询")
  private String receiverName;

  @Schema(name = "发送人头像")
  private String receiverAvatar;

  @Schema(name = "公告内容")
  private String content;

  @Schema(name = "对话ID")
  private String dialogueId;

  @Schema(name = "发送人ID")
  private String senderId;

  @Schema(name = "发送人名称", title = "冗余信息，增加该字段减少重复查询")
  private String senderName;

  @Schema(name = "发送人头像")
  private String senderAvatar;

  public String getDetailId() {
    return detailId;
  }

  public void setDetailId(String detailId) {
    this.detailId = detailId;
  }

  public String getReceiverId() {
    return receiverId;
  }

  public void setReceiverId(String receiverId) {
    this.receiverId = receiverId;
  }

  public String getReceiverName() {
    return receiverName;
  }

  public void setReceiverName(String receiverName) {
    this.receiverName = receiverName;
  }

  public String getReceiverAvatar() {
    return receiverAvatar;
  }

  public void setReceiverAvatar(String receiverAvatar) {
    this.receiverAvatar = receiverAvatar;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getDialogueId() {
    return dialogueId;
  }

  public void setDialogueId(String dialogueId) {
    this.dialogueId = dialogueId;
  }

  public String getSenderId() {
    return senderId;
  }

  public void setSenderId(String senderId) {
    this.senderId = senderId;
  }

  public String getSenderName() {
    return senderName;
  }

  public void setSenderName(String senderName) {
    this.senderName = senderName;
  }

  public String getSenderAvatar() {
    return senderAvatar;
  }

  public void setSenderAvatar(String senderAvatar) {
    this.senderAvatar = senderAvatar;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("detailId", detailId)
      .add("receiverId", receiverId)
      .add("receiverName", receiverName)
      .add("receiverAvatar", receiverAvatar)
      .add("content", content)
      .add("dialogueId", dialogueId)
      .add("senderId", senderId)
      .add("senderName", senderName)
      .add("senderAvatar", senderAvatar)
      .toString();
  }
}
