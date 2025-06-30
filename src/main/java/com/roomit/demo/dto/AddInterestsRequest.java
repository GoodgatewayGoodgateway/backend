package com.roomit.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddInterestsRequest {
    private String userId;
    private List<String> interests;
}
