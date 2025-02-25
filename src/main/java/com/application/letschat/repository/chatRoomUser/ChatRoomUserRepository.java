package com.application.letschat.repository.chatRoomUser;

import com.application.letschat.model.chatRoomUser.ChatRoomUser;
import com.application.letschat.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Long> {


    List<ChatRoomUser> findByUser(User user);

    @Query("SELECT cru FROM ChatRoomUser cru WHERE cru.chatRoom.chatRoomId = :chatRoomId")
    Optional<List<ChatRoomUser>> findByChatRoomId(@Param("chatRoomId") Long chatRoomId);


}
