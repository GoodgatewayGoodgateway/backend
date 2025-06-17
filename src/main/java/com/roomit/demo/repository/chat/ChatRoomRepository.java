package com.roomit.demo.repository.chat;

import com.roomit.demo.domain.chat.ChatRoom;
import com.roomit.demo.domain.chat.ChatRoomMember;
import com.roomit.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
