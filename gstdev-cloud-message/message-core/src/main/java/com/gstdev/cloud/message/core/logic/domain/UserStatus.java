package com.gstdev.cloud.message.core.logic.domain;

import com.gstdev.cloud.base.definition.domain.base.AbstractEntity;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * <p>Description: 用户状态变更实体 </p>
 *
 * @author : cc
 * @date : 2022/7/10 16:15
 */
public class UserStatus extends AbstractEntity {


  private String userId;

  private String status;

  public UserStatus() {
  }

  public UserStatus(String userId, String status) {
    this.userId = userId;
    this.status = status;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserStatus that = (UserStatus) o;
    return Objects.equal(userId, that.userId) && Objects.equal(status, that.status);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(userId, status);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("userId", userId)
      .add("status", status)
      .toString();
  }
}
