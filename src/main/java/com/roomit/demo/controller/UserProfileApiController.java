package com.roomit.demo.controller;

import com.roomit.demo.dto.*;
import com.roomit.demo.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserProfileApiController {

    private final UserProfileService profileService;

    @PostMapping("/profile")
    public String saveProfile(@RequestBody AddUserProfileRequest request) {
        profileService.saveProfile(request);
        return "프로필 저장 완료";
    }

    @PostMapping("/interests")
    public String saveInterests(@RequestBody AddInterestsRequest request) {
        profileService.saveInterests(request);
        return "관심사 저장 완료";
    }

    @PostMapping("/selected-options")
    public String saveSelectedOptions(@RequestBody AddSelectedOptionsRequest request) {
        profileService.saveSelectedOptions(request);
        return "선택 옵션 저장 완료";
    }
}
