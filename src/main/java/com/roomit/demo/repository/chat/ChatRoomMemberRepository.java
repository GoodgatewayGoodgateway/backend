package com.roomit.demo.repository.chat;

import com.roomit.demo.domain.chat.ChatRoom;
import com.roomit.demo.domain.chat.ChatRoomMember;
import com.roomit.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, Long> {
    List<ChatRoomMember> findByUser(User user);
    List<ChatRoomMember> findByChatRoom(ChatRoom chatRoom);
    boolean existsByChatRoomAndUser(ChatRoom chatRoom, User user);
}
