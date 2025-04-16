package com.roomit.demo.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class AddInterestsRequest {
    private String userId;
    private List<String> interests;
}
