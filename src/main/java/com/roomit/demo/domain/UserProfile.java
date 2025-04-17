package com.roomit.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Integer age;
    private String gender;
    private String location;
    private String job;
    private String introduction;
    private String idealRoommate;
    private String mbti;

    private LocalTime wakeUpTime;
    private LocalTime sleepTime;

    private String dayNightType;
    private String cleanLevel;
    private String noise;
    private String smoking;
    private String drinking;

    @Column(length = 255)
    private String avatar;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
