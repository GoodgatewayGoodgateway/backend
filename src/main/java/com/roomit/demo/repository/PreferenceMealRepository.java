package com.roomit.demo.repository;

import com.roomit.demo.domain.PreferenceMeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreferenceMealRepository extends JpaRepository<PreferenceMeal, Long> {
    Optional<PreferenceMeal> findByUserId(Long userId);
}
