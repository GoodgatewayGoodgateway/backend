package com.roomit.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "preference_clean")
@Getter @Setter
public class PreferenceClean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true)
    private Long userId;

    @Column(name = "clean_level")
    private String cleanLevel;

    @Column(name = "tidy_level")
    private String tidyLevel;

    @Column(name = "clean_freq")
    private String cleanFreq;

    @Column(name = "common_area_mgmt")
    private String commonAreaMgmt;
}
