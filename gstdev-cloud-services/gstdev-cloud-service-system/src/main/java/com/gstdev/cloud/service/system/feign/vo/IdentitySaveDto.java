package com.gstdev.cloud.service.system.feign.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: zcy
 * @date: 2022/12/7
 * @description:
 */
@Getter
@Setter
public class IdentitySaveDto {
    private String password;

    private String username;

    private String email;
    private String userId;

    public void setId(String id) {
        this.userId = id;
    }
}
