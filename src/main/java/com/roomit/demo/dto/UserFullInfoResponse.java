package com.roomit.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class UserFullInfoResponse {
    private String userId;
    private String email;

    private UserProfileInfo profile;
    private List<String> interests;
    private List<String> selectedOptions;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class UserProfileInfo {
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
        private String avatar;
    }
}
