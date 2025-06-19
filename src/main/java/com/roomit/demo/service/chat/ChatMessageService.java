package com.roomit.demo.service.chat;

import com.roomit.demo.domain.User;
import com.roomit.demo.domain.chat.ChatMessage;
import com.roomit.demo.domain.chat.ChatRoom;
import com.roomit.demo.repository.UserRepository;
import com.roomit.demo.repository.chat.ChatMessageRepository;
import com.roomit.demo.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public ChatMessage sendMessage(Long roomId, String senderId, String content) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));

        User sender = userRepository.findByUserId(senderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        ChatMessage message = ChatMessage.builder()
                .chatRoom(chatRoom)
                .sender(sender)
                .content(content)
                .sentAt(LocalDateTime.now())
                .build();

        return chatMessageRepository.save(message);
    }

    public List<ChatMessage> getMessages(Long roomId) {
        return chatMessageRepository.findByChatRoom_Id(roomId);
    }
}
