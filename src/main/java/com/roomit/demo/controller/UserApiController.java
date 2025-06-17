package com.roomit.demo.controller;

import com.roomit.demo.domain.User;
import com.roomit.demo.dto.*;
import com.roomit.demo.service.UserProfileService;
import com.roomit.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User API", description = "회원가입, 로그인 및 유저 정보 관리 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final UserProfileService userProfileService;

    @Operation(summary = "회원가입", description = "닉네임, 이메일, 비밀번호로 새로운 유저를 등록합니다.")
    @PostMapping("/user")
    public String register(@RequestBody AddUserRequest request) {
        userService.register(request);
        return "회원가입 완료";
    }

    @Operation(summary = "로그인", description = "닉네임과 비밀번호로 로그인합니다. (성공 시 JWT 토큰 반환)")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @Operation(summary = "유저 전체 정보 조회", description = "특정 유저의 프로필, 관심사, 선택 옵션 등을 모두 조회합니다.")
    @GetMapping("/user/{userId}/full")
    public UserFullInfoResponse getFullUserInfo(@PathVariable String userId) {
        return userService.getFullUserInfo(userId);
    }

    @Operation(summary = "모든 유저 전체 정보 조회", description = "모든 유저의 전체 정보를 리스트로 반환합니다.")
    @GetMapping("/user/all/full")
    public List<UserFullInfoResponse> getAllUserInfo() {
        return userService.getAllUserInfoList();
    }

    @Operation(
            summary = "프로필 저장",
            description = "유저 ID 기준으로 기본 프로필을 저장합니다. (JWT 인증 필요)",
            security = @SecurityRequirement(name = "Authorization")
    )
    @PostMapping("/secure/profile")
    public String saveProfile(@RequestBody AddUserProfileRequest request, HttpServletRequest httpRequest) {
        String userId = (String) httpRequest.getAttribute("userId");
        request.setUserId(userId);
        userProfileService.saveProfile(request);
        return "프로필 저장 완료";
    }

    @Operation(
            summary = "관심사 저장",
            description = "자유롭게 작성한 관심사를 저장합니다. (JWT 인증 필요)",
            security = @SecurityRequirement(name = "Authorization")
    )
    @PostMapping("/secure/interests")
    public String saveInterests(@RequestBody AddInterestsRequest request, HttpServletRequest httpRequest) {
        String userId = (String) httpRequest.getAttribute("userId");
        request.setUserId(userId);
        userProfileService.saveInterests(request);
        return "관심사 저장 완료";
    }

    @Operation(
            summary = "선택 옵션 저장",
            description = "카테고리에서 선택한 옵션들을 저장합니다. (JWT 인증 필요)",
            security = @SecurityRequirement(name = "Authorization")
    )
    @PostMapping("/secure/selected-options")
    public String saveSelectedOptions(@RequestBody AddSelectedOptionsRequest request, HttpServletRequest httpRequest) {
        String userId = (String) httpRequest.getAttribute("userId");
        request.setUserId(userId);
        userProfileService.saveSelectedOptions(request);
        return "선택 옵션 저장 완료";
    }
}
