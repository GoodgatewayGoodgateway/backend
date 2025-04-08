package com.roomit.demo.service;

import com.roomit.demo.domain.User;
import com.roomit.demo.dto.AddUserRequest;
import com.roomit.demo.dto.LoginRequest;
import com.roomit.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void register(AddUserRequest request) {
        User user = User.builder()
                .userId(request.getUserId())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        userRepository.save(user);
    }

    public void login(LoginRequest request) {
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
