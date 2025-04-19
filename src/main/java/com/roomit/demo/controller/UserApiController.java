package com.roomit.demo.controller;

import com.roomit.demo.dto.*;
import com.roomit.demo.service.UserService;
import com.roomit.demo.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final UserProfileService userProfileService;

    // 회원가입
    @PostMapping("/user")
    public String register(@RequestBody AddUserRequest request) {
        userService.register(request);
        return "회원가입 완료";
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        userService.login(request);
        return "로그인 성공";
    }

    // 단일 유저 전체 정보 조회
    @GetMapping("/user/{userId}/full")
    public UserFullInfoResponse getFullUserInfo(@PathVariable String userId) {
        return userService.getFullUserInfo(userId);
    }

    // 모든 유저 전체 정보 조회
    @GetMapping("/user/all/full")
    public List<UserFullInfoResponse> getAllUserInfo() {
        return userService.getAllUserInfoList();
    }

    // 프로필 저장
    @PostMapping("/profile")
    public String saveProfile(@RequestBody AddUserProfileRequest request) {
        userProfileService.saveProfile(request);
        return "프로필 저장 완료";
    }

    // 관심사 저장
    @PostMapping("/interests")
    public String saveInterests(@RequestBody AddInterestsRequest request) {
        userProfileService.saveInterests(request);
        return "관심사 저장 완료";
    }

    // 선택 옵션 저장
    @PostMapping("/selected-options")
    public String saveSelectedOptions(@RequestBody AddSelectedOptionsRequest request) {
        userProfileService.saveSelectedOptions(request);
        return "선택 옵션 저장 완료";
    }
}
