package com.roomit.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProfileRequest {
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
    private String wakeUpTime; // "HH:mm:ss"
    private String sleepTime;  // "HH:mm:ss"

    // preference_clean
    private String tidyLevel;
    private String cleanFreq;
    private String commonAreaMgmt;

    // preference_meal
    private String mealTime;       // "HH:mm:ss"
    private String kitchenUsage;
    private String cookingFreq;

    // preference_noise
    private String soundSensitivity;
    private String musicTvPref;

    // preference_pet
    private Boolean petAllowed;
    private String preferredPet;
    private Boolean petAllergy;
}
