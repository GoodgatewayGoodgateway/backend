package com.roomit.demo.dto;

import lombok.Getter;

@Getter
public class AddUserRequest {
    private String userId;
    private String email;
    private String password;
}
