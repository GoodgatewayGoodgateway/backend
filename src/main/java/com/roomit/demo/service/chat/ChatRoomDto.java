package com.roomit.demo.service.chat;

import com.roomit.demo.domain.chat.ChatRoom;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
public class ChatRoomDto {
    private Long id;
    private LocalDateTime createdAt;
    private List<String> memberUserIds;

    public static ChatRoomDto from(ChatRoom room) {
        return ChatRoomDto.builder()
                .id(room.getId())
                .createdAt(room.getCreatedAt())
                .memberUserIds(
                        Optional.ofNullable(room.getMembers())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(member -> member.getUser().getUserId())
                                .collect(Collectors.toList())
                )
                .build();
    }
}
