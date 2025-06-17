package com.roomit.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @Schema(description = "로그인 시 사용하는 유저 닉네임", example = "string")
    private String userId;

    @Schema(description = "비밀번호", example = "string")
    private String password;
}
