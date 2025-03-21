package com.application.letschat.dto.chatRoom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDTO {

    private Long chatRoomId;
    private String chatRoomName;
    private Integer senderId;

}
