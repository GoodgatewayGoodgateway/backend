package com.roomit.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/secure")
public class SecureTestController {

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        return "안녕하세요, " + userId + "님! JWT 인증이 성공했습니다.";
    }
}
