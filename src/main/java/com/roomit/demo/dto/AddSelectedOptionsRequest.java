package com.roomit.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddSelectedOptionsRequest {
    private String userId;
    private List<Long> optionValueIds;
}
