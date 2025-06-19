package com.roomit.demo.service.chat;

import com.roomit.demo.domain.chat.ChatMessage;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChatMessageDto {
    private Long id;
    private String content;
    private String senderId;
    private LocalDateTime sentAt;

    public static ChatMessageDto from(ChatMessage message) {
        return ChatMessageDto.builder()
                .id(message.getId())
                .content(message.getContent())
                .senderId(message.getSender().getUserId()) // sender의 userId 사용
                .sentAt(message.getSentAt())
                .build();
    }
}
