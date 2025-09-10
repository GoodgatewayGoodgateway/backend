package com.roomit.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "preference_noise")
@Getter @Setter
public class PreferenceNoise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true)
    private Long userId;

    @Column(name = "sound_sensitivity")
    private String soundSensitivity;

    @Column(name = "music_tv_pref")
    private String musicTvPref;
}
