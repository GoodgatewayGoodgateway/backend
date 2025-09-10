package com.roomit.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ProfileResponse {
    private Long userId;

    // user_profiles
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
    private String wakeUpTime;
    private String sleepTime;

    // preferences
    private String tidyLevel;
    private String cleanFreq;
    private String commonAreaMgmt;

    private String mealTime;
    private String kitchenUsage;
    private String cookingFreq;

    private String soundSensitivity;
    private String musicTvPref;

    private Boolean petAllowed;
    private String preferredPet;
    private Boolean petAllergy;
}
