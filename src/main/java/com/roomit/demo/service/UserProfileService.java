package com.roomit.demo.service;

import com.roomit.demo.domain.*;
import com.roomit.demo.dto.*;
import com.roomit.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserInterestRepository userInterestRepository;
    private final UserSelectedOptionRepository userSelectedOptionRepository;
    private final OptionValueRepository optionValueRepository;

    @Transactional
    public void saveProfile(AddUserProfileRequest request) {
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        UserProfile profile = userProfileRepository.findByUser(user)
                .orElse(UserProfile.builder()
                        .user(user)
                        .createdAt(LocalDateTime.now())
                        .build());

        profile.setName(request.getName());
        profile.setAge(request.getAge());
        profile.setGender(request.getGender());
        profile.setLocation(request.getLocation());
        profile.setJob(request.getJob());
        profile.setIntroduction(request.getIntroduction());
        profile.setIdealRoommate(request.getIdealRoommate());
        profile.setMbti(request.getMbti());
        profile.setWakeUpTime(LocalTime.parse(request.getWakeUpTime())); // ⚠️ String → LocalTime
        profile.setSleepTime(LocalTime.parse(request.getSleepTime()));   // ⚠️ String → LocalTime
        profile.setDayNightType(request.getDayNightType());
        profile.setCleanLevel(request.getCleanLevel());
        profile.setNoise(request.getNoise());
        profile.setSmoking(request.getSmoking());
        profile.setDrinking(request.getDrinking());
        profile.setAvatar(request.getAvatar());

        userProfileRepository.save(profile);
    }

    @Transactional
    public void saveInterests(AddInterestsRequest request) {
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        UserProfile profile = user.getProfile();
        if (profile == null) throw new IllegalStateException("프로필 먼저 등록 필요");

        request.getInterests().forEach(name -> {
            UserInterest interest = UserInterest.builder()
                    .profile(profile)
                    .name(name)
                    .build();
            userInterestRepository.save(interest);
        });
    }

    @Transactional
    public void saveSelectedOptions(AddSelectedOptionsRequest request) {
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        UserProfile profile = user.getProfile();
        if (profile == null) throw new IllegalStateException("프로필 먼저 등록 필요");

        request.getOptionValueIds().forEach(optionId -> {
            OptionValue option = optionValueRepository.findById(optionId)
                    .orElseThrow(() -> new IllegalArgumentException("옵션 없음"));
            UserSelectedOption selected = UserSelectedOption.builder()
                    .profile(profile)
                    .optionValue(option)
                    .build();
            userSelectedOptionRepository.save(selected);
        });
    }
}
