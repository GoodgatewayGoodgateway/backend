package com.roomit.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserProfileRequest {
    private String userId;
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
}
