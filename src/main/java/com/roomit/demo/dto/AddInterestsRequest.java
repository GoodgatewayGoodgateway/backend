package com.roomit.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddInterestsRequest {
    private String userId; // JWT에서 설정됨
    private List<String> interests;
}
