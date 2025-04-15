package com.roomit.demo.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class AddSelectedOptionsRequest {
    private String userId;
    private List<Long> selectedOptionIds;
}
