package com.roomit.demo.repository;

import com.roomit.demo.domain.PreferencePet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreferencePetRepository extends JpaRepository<PreferencePet, Long> {
    Optional<PreferencePet> findByUserId(Long userId);
}
