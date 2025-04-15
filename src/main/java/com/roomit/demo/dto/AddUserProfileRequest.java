package com.roomit.demo.dto;

import lombok.Getter;

@Getter
public class AddUserProfileRequest {
    private String userId;
    private Integer age;
    private String gender;
    private String location;
    private String job;
    private String introduction;
    private String idealRoommate;
    private String mbti;
    private String wakeUpTime;
    private String sleepTime;
    private String dayNightType;
    private String cleanLevel;
    private String noise;
    private String smoking;
    private String drinking;
}