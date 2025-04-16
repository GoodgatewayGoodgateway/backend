package com.roomit.demo.dto;

import lombok.Getter;
import java.time.LocalTime;

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
    private LocalTime wakeUpTime;  // ← 여기!
    private LocalTime sleepTime;   // ← 여기!
    private String dayNightType;
    private String cleanLevel;
    private String noise;
    private String smoking;
    private String drinking;
}
