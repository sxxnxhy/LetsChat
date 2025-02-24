package com.application.letschat.service.chatList;


import com.application.letschat.dto.chatList.ChatListDTO;
import com.application.letschat.model.chatRoom.ChatRoom;
import com.application.letschat.model.chatRoomUser.ChatRoomUser;
import com.application.letschat.model.message.Message;
import com.application.letschat.model.user.User;
import com.application.letschat.repository.chatRoomUser.ChatRoomUserRepository;
import com.application.letschat.repository.message.MessageRepository;
import com.application.letschat.repository.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatListService {

    private final ChatRoomUserRepository chatRoomUserRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;


    public List<ChatListDTO> getChatList(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        List<ChatRoomUser> chatRoomUsers = chatRoomUserRepository.findByUser(user);

        return chatRoomUsers.stream()
                .map(cru -> {
                    ChatRoom chatRoom = cru.getChatRoom();
                    String lastMessage = messageRepository.findLastMessageByChatRoom(chatRoom)
                            .map(Message::getContent) // Extract content if present
                            .orElse("No messages yet");   // Default if absent
                    return new ChatListDTO(chatRoom.getChatRoomId(), chatRoom.getChatRoomName(), lastMessage);
                })
                .collect(Collectors.toList());
    }

}

