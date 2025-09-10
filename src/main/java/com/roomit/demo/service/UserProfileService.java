package com.roomit.demo.service;

import com.roomit.demo.domain.*;
import com.roomit.demo.dto.AddInterestsRequest;
import com.roomit.demo.dto.AddSelectedOptionsRequest;
import com.roomit.demo.dto.AddUserProfileRequest;
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

    private final PreferenceCleanRepository preferenceCleanRepository;
    private final PreferenceMealRepository preferenceMealRepository;
    private final PreferenceNoiseRepository preferenceNoiseRepository;
    private final PreferencePetRepository preferencePetRepository;

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

        if (request.getWakeUpTime() != null && !request.getWakeUpTime().isBlank()) {
            profile.setWakeUpTime(LocalTime.parse(request.getWakeUpTime()));
        }
        if (request.getSleepTime() != null && !request.getSleepTime().isBlank()) {
            profile.setSleepTime(LocalTime.parse(request.getSleepTime()));
        }

        profile.setDayNightType(request.getDayNightType());
        profile.setCleanLevel(request.getCleanLevel());
        profile.setNoise(request.getNoise());
        profile.setSmoking(request.getSmoking());
        profile.setDrinking(request.getDrinking());
        profile.setAvatar(request.getAvatar());

        userProfileRepository.save(profile);

        final Long uid = user.getId();

        // preference_clean
        PreferenceClean clean = preferenceCleanRepository.findByUserId(uid)
                .orElseGet(PreferenceClean::new);
        clean.setUserId(uid);
        clean.setTidyLevel(request.getTidyLevel());          // 정리정돈 수준
        clean.setCleanFreq(request.getCleanFreq());          // 청소 주기
        clean.setCommonAreaMgmt(request.getCommonAreaMgmt()); // 공용공간 관리
        // 참고: self 평가 cleanLevel은 user_profiles.clean_level에 이미 들어감
        preferenceCleanRepository.save(clean);

        // preference_meal
        PreferenceMeal meal = preferenceMealRepository.findByUserId(uid)
                .orElseGet(PreferenceMeal::new);
        meal.setUserId(uid);
        if (request.getMealTime() != null && !request.getMealTime().isBlank()) {
            meal.setMealTime(LocalTime.parse(request.getMealTime())); // "HH:mm:ss"
        } else {
            meal.setMealTime(null);
        }
        meal.setKitchenUsage(request.getKitchenUsage());     // 주방 사용 빈도
        meal.setCookingFreq(request.getCookingFreq());       // 요리 빈도
        preferenceMealRepository.save(meal);

        // preference_noise
        PreferenceNoise noise = preferenceNoiseRepository.findByUserId(uid)
                .orElseGet(PreferenceNoise::new);
        noise.setUserId(uid);
        noise.setSoundSensitivity(request.getSoundSensitivity()); // 소음 민감도
        noise.setMusicTvPref(request.getMusicTvPref());           // 음악/TV 볼륨/선호
        preferenceNoiseRepository.save(noise);

        // preference_pet
        PreferencePet pet = preferencePetRepository.findByUserId(uid)
                .orElseGet(PreferencePet::new);
        pet.setUserId(uid);
        pet.setPetAllowed(request.getPetAllowed());          // 반려동물 허용 여부
        pet.setPreferredPet(request.getPreferredPet());      // 선호 동물
        pet.setPetAllergy(request.getPetAllergy());          // 알레르기 여부
        preferencePetRepository.save(pet);
    }

    @Transactional
    public void saveInterests(AddInterestsRequest request) {
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        UserProfile profile = user.getProfile();
        if (profile == null) throw new IllegalStateException("프로필 먼저 등록 필요");

        // 기존 관심사 전부 삭제
        userInterestRepository.deleteAllByUser(user);

        // 중복 제거 후 새로 저장
        request.getInterests().stream()
                .distinct()
                .forEach(name -> {
                    UserInterest interest = UserInterest.builder()
                            .user(user)
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
