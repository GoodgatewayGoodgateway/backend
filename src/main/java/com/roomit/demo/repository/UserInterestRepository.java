package com.roomit.demo.repository;

import com.roomit.demo.domain.UserInterest;
import com.roomit.demo.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInterestRepository extends JpaRepository<UserInterest, Long> {
    List<UserInterest> findByProfile(UserProfile profile);
    void deleteAllByProfile(UserProfile profile); // 기존 관심사 삭제용
}
