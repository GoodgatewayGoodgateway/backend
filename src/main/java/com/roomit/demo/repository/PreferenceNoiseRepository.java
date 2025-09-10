package com.roomit.demo.repository;

import com.roomit.demo.domain.PreferenceNoise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreferenceNoiseRepository extends JpaRepository<PreferenceNoise, Long> {
    Optional<PreferenceNoise> findByUserId(Long userId);
}
