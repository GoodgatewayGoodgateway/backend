package com.roomit.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "preference_meal")
@Getter @Setter
public class PreferenceMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true)
    private Long userId;

    @Column(name = "meal_time")
    private LocalTime mealTime;

    @Column(name = "kitchen_usage")
    private String kitchenUsage;

    @Column(name = "cooking_freq")
    private String cookingFreq;
}
