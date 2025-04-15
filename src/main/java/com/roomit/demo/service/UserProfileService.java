package com.roomit.demo.service;

import com.roomit.demo.domain.*;
import com.roomit.demo.dto.*;
import com.roomit.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        UserProfile profile = UserProfile.builder()
                .user(user)
                .age(request.getAge())
                .gender(request.getGender())
                .location(request.getLocation())
                .job(request.getJob())
                .introduction(request.getIntroduction())
                .idealRoommate(request.getIdealRoommate())
                .mbti(request.getMbti())
                .wakeUpTime(request.getWakeUpTime())
                .sleepTime(request.getSleepTime())
                .dayNightType(request.getDayNightType())
                .cleanLevel(request.getCleanLevel())
                .noise(request.getNoise())
                .smoking(request.getSmoking())
                .drinking(request.getDrinking())
                .build();

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

        request.getSelectedOptionIds().forEach(optionId -> {
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
