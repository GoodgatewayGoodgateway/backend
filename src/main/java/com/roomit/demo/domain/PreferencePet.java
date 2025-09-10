package com.roomit.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "preference_pet")
@Getter @Setter
public class PreferencePet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true)
    private Long userId;

    @Column(name = "pet_allowed")
    private Boolean petAllowed;

    @Column(name = "preferred_pet")
    private String preferredPet;

    @Column(name = "pet_allergy")
    private Boolean petAllergy;
}
