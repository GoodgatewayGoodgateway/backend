package com.roomit.demo.controller;

import com.roomit.demo.domain.chat.ChatMessage;
import com.roomit.demo.domain.chat.ChatRoom;
import com.roomit.demo.dto.chat.SendMessageRequest;
import com.roomit.demo.service.chat.ChatMessageService;
import com.roomit.demo.service.chat.ChatRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Chat API", description = "1:1 채팅방 생성, 메시지 전송 및 조회 API")
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    @Operation(
            summary = "채팅방 생성",
            description = "자신의 userId와 대화할 상대 userId를 받아 채팅방을 생성합니다.\n쿼리 파라미터로 userId와 targetUserId를 전달하세요."
    )
    @PostMapping("/room")
    public ChatRoom createRoom(
            @RequestParam String userId,
            @RequestParam String targetUserId
    ) {
        return chatRoomService.createRoom(userId, targetUserId);
    }

    @Operation(
            summary = "내 채팅방 목록 조회",
            description = "특정 유저(userId)가 속한 모든 채팅방 목록을 반환합니다."
    )
    @GetMapping("/rooms")
    public List<ChatRoom> getMyRooms(@RequestParam String userId) {
        return chatRoomService.getRoomsByUser(userId);
    }

    @Operation(
            summary = "채팅 메시지 전송",
            description = "roomId를 경로로, userId를 쿼리로, content를 JSON Body로 전달하여 메시지를 보냅니다."
    )
    @PostMapping("/room/{roomId}/message")
    public ChatMessage sendMessage(
            @PathVariable Long roomId,
            @RequestParam String userId,
            @RequestBody SendMessageRequest requestDto
    ) {
        return chatMessageService.sendMessage(roomId, userId, requestDto.getContent());
    }

    @Operation(
            summary = "채팅 메시지 목록 조회",
            description = "특정 채팅방(roomId)의 모든 메시지를 반환합니다."
    )
    @GetMapping("/room/{roomId}/messages")
    public List<ChatMessage> getMessages(@PathVariable Long roomId) {
        return chatMessageService.getMessages(roomId);
    }
}
