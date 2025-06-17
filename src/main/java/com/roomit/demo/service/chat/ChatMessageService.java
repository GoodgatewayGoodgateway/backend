package com.roomit.demo.service.chat;

import com.roomit.demo.domain.User;
import com.roomit.demo.domain.chat.ChatMessage;
import com.roomit.demo.domain.chat.ChatRoom;
import com.roomit.demo.repository.UserRepository;
import com.roomit.demo.repository.chat.ChatMessageRepository;
import com.roomit.demo.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    @Transactional
    public ChatMessage sendMessage(Long roomId, String senderUserId, String content) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));
        User sender = userRepository.findByUserId(senderUserId)
                .orElseThrow(() -> new IllegalArgumentException("보내는 유저 없음"));

        ChatMessage message = ChatMessage.builder()
                .chatRoom(chatRoom)
                .sender(sender)
                .content(content)
                .sentAt(LocalDateTime.now())
                .build();

        return chatMessageRepository.save(message);
    }

    public List<ChatMessage> getMessages(Long roomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방 없음"));
        return chatMessageRepository.findByChatRoomOrderBySentAtAsc(chatRoom);
    }
}
