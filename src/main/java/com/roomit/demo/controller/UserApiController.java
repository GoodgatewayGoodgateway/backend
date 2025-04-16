package com.roomit.demo.controller;

import com.roomit.demo.domain.User;
import com.roomit.demo.dto.AddUserRequest;
import com.roomit.demo.dto.LoginRequest;
import com.roomit.demo.dto.UserFullInfoResponse;
import com.roomit.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String register(@RequestBody AddUserRequest request) {
        userService.register(request);
        return "회원가입 완료";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        userService.login(request);
        return "로그인 성공";
    }

    @GetMapping("/user/{userId}/full")
    public UserFullInfoResponse getFullUserInfo(@PathVariable String userId) {
        return userService.getFullUserInfo(userId);
    }
}
