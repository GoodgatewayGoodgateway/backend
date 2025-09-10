package com.roomit.demo.repository;

import com.roomit.demo.domain.PreferenceClean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreferenceCleanRepository extends JpaRepository<PreferenceClean, Long> {
    Optional<PreferenceClean> findByUserId(Long userId);
}
