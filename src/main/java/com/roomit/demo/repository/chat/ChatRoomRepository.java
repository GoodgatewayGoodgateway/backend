package com.roomit.demo.repository.chat;

import com.roomit.demo.domain.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("""
        SELECT crm.chatRoom FROM ChatRoomMember crm
        WHERE crm.user.userId IN (:userId1, :userId2)
        GROUP BY crm.chatRoom
        HAVING COUNT(DISTINCT crm.user.userId) = 2
    """)
    Optional<ChatRoom> findChatRoomByUsers(
            @Param("userId1") String userId1,
            @Param("userId2") String userId2
    );
}
