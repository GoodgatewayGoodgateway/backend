package com.roomit.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "유저 프로필 요청 DTO", example = """
{
  "name": "홍길동",
  "age": 25,
  "gender": "남성",
  "location": "서울시 강남구",
  "job": "학생",
  "introduction": "안녕하세요. 저는 조용한 성격의 사람을 찾고 있어요.",
  "idealRoommate": "청결하고 예의 바른 분이 좋습니다.",
  "mbti": "INTJ",
  "dayNightType": "야행성",
  "cleanLevel": "높음",
  "noise": "조용한 환경 선호",
  "smoking": "비흡연",
  "drinking": "가끔",
  "avatar": "https://example.com/profile.jpg",
  "wakeUpTime": "07:30:00",
  "sleepTime": "23:30:00",
  "tidyLevel": "보통",
  "cleanFreq": "주 2-3회",
  "commonAreaMgmt": "가끔 정리",
  "mealTime": "19:00:00",
  "kitchenUsage": "매일",
  "cookingFreq": "매일",
  "soundSensitivity": "둔감",
  "musicTvPref": "중간 볼륨",
  "petAllowed": false,
  "preferredPet": "고양이",
  "petAllergy": true
}
""")
public class AddUserProfileRequest {
    private String userId;

    // ==== user_profiles ====
    private String name;
    private Integer age;
    private String gender;
    private String location;
    private String job;
    private String introduction;
    private String idealRoommate;
    private String mbti;
    private String dayNightType;
    private String cleanLevel;
    private String noise;
    private String smoking;
    private String drinking;
    private String avatar;

    @Schema(type = "string", format = "time", example = "07:30:00")
    private String wakeUpTime;

    @Schema(type = "string", format = "time", example = "23:30:00")
    private String sleepTime;

    // ==== preference_clean ====
    private String tidyLevel;
    private String cleanFreq;
    private String commonAreaMgmt;

    // ==== preference_meal ====
    private String mealTime;       // "HH:mm:ss"
    private String kitchenUsage;
    private String cookingFreq;

    // ==== preference_noise ====
    private String soundSensitivity;
    private String musicTvPref;

    // ==== preference_pet ====
    private Boolean petAllowed;
    private String preferredPet;
    private Boolean petAllergy;
}
