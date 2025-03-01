package com.application.letschat.repository.chatRoomUser;

import com.application.letschat.model.chatRoomUser.ChatRoomUser;
import com.application.letschat.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Long> {


    List<ChatRoomUser> findByUser(User user);

    @Query("SELECT cru FROM ChatRoomUser cru WHERE cru.chatRoom.chatRoomId = :chatRoomId")
    Optional<List<ChatRoomUser>> findByChatRoomId(@Param("chatRoomId") Long chatRoomId);


    List<ChatRoomUser> findByUserUserId(Integer userId);

    @Query("SELECT cru.user.userId FROM ChatRoomUser cru WHERE cru.chatRoom.chatRoomId = :chatRoomId")
    List<Integer> findUserIdsByChatRoomId(@Param("chatRoomId") Long chatRoomId);


    @Query("SELECT cru.chatRoom.chatRoomId FROM ChatRoomUser cru WHERE cru.user.userId = :userId")
    List<Long> findChatRoomIdsByUserId(@Param("userId") Integer userId);


}
