package com.roomit.demo.repository;

import com.roomit.demo.domain.User;
import com.roomit.demo.domain.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInterestRepository extends JpaRepository<UserInterest, Long> {

    List<UserInterest> findByUser(User user);
    void deleteAllByUser(User user); // 새 메서드 정의

}
