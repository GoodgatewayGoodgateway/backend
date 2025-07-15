package com.roomit.demo.service;

import com.roomit.demo.domain.*;
import com.roomit.demo.dto.*;
import com.roomit.demo.repository.*;
import com.roomit.demo.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserInterestRepository userInterestRepository;
    private final UserSelectedOptionRepository userSelectedOptionRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public void register(AddUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        if (userRepository.findByUserId(request.getUserId()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        User user = User.builder()
                .userId(request.getUserId())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUserId(request.getUserId())
                .filter(u -> u.getPassword().equals(request.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        String token = jwtUtil.createToken(user.getUserId());
        return new LoginResponse(token, user.getUserId(), user.getEmail());
    }

    @Transactional(readOnly = true)
    public UserFullInfoResponse getFullUserInfo(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        return buildFullInfo(user);
    }

    @Transactional(readOnly = true)
    public List<UserFullInfoResponse> getAllUserInfoList() {
        return userRepository.findAll().stream()
                .map(this::buildFullInfo)
                .toList();
    }

    private UserFullInfoResponse buildFullInfo(User user) {
        UserProfile profile = userProfileRepository.findByUser(user).orElse(null);
        List<UserInterest> interests = userInterestRepository.findByUser(user);
        List<UserSelectedOption> selectedOptions = profile == null ? List.of() : userSelectedOptionRepository.findByProfile(profile);


        return UserFullInfoResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .profile(profile == null ? null :
                        UserFullInfoResponse.UserProfileInfo.builder()
                                .name(nullToInfo(profile.getName()))
                                .age(profile.getAge())
                                .gender(nullToInfo(profile.getGender()))
                                .location(nullToInfo(profile.getLocation()))
                                .job(nullToInfo(profile.getJob()))
                                .introduction(nullToInfo(profile.getIntroduction()))
                                .idealRoommate(nullToInfo(profile.getIdealRoommate()))
                                .mbti(nullToInfo(profile.getMbti()))
                                .wakeUpTime(profile.getWakeUpTime() == null ? "정보없음" : String.valueOf(profile.getWakeUpTime()))
                                .sleepTime(profile.getSleepTime() == null ? "정보없음" : String.valueOf(profile.getSleepTime()))
                                .dayNightType(nullToInfo(profile.getDayNightType()))
                                .cleanLevel(nullToInfo(profile.getCleanLevel()))
                                .noise(nullToInfo(profile.getNoise()))
                                .smoking(nullToInfo(profile.getSmoking()))
                                .drinking(nullToInfo(profile.getDrinking()))
                                .avatar(nullToInfo(profile.getAvatar()))
                                .build())
                .interests(interests.stream().map(i -> nullToInfo(i.getName())).toList())
                .selectedOptions(
                        selectedOptions.stream()
                                .map(s -> {
                                    OptionValue opt = s.getOptionValue();
                                    return nullToInfo(opt == null ? null : opt.getLabel());
                                })
                                .toList())
                .build();
    }

    private String nullToInfo(String value) {
        return value == null ? "정보없음" : value;
    }
}
