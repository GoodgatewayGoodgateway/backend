package com.roomit.demo.service.chat;

import com.roomit.demo.domain.User;
import com.roomit.demo.domain.chat.ChatRoom;
import com.roomit.demo.domain.chat.ChatRoomMember;
import com.roomit.demo.repository.UserRepository;
import com.roomit.demo.repository.chat.ChatRoomMemberRepository;
import com.roomit.demo.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final UserRepository userRepository;

    @Transactional
    public ChatRoom createRoom(String userId1, String userId2) {
        User user1 = userRepository.findByUserId(userId1)
                .orElseThrow(() -> new IllegalArgumentException("유저1 없음"));
        User user2 = userRepository.findByUserId(userId2)
                .orElseThrow(() -> new IllegalArgumentException("유저2 없음"));

        ChatRoom room = chatRoomRepository.save(new ChatRoom());

        chatRoomMemberRepository.save(new ChatRoomMember(room, user1));
        chatRoomMemberRepository.save(new ChatRoomMember(room, user2));

        return room;
    }

    public List<ChatRoom> getRoomsByUser(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
        List<ChatRoomMember> memberships = chatRoomMemberRepository.findByUser(user);
        return memberships.stream().map(ChatRoomMember::getChatRoom).toList();
    }
}
