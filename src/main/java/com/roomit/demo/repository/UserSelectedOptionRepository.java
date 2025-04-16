package com.roomit.demo.repository;

import com.roomit.demo.domain.UserProfile;
import com.roomit.demo.domain.UserSelectedOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSelectedOptionRepository extends JpaRepository<UserSelectedOption, Long> {
    List<UserSelectedOption> findByProfile(UserProfile profile);
}
