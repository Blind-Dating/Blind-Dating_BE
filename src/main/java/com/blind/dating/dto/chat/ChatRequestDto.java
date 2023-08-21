package com.blind.dating.dto.chat;

import com.blind.dating.domain.Chat;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatRequestDto {
    private String chatRoomId;
    private String writerId;
    private String message;

    public ChatRequestDto(String chatRoomId, String writerId, String message) {
        this.chatRoomId = chatRoomId;
        this.writerId = writerId;
        this.message = message;
    }

    public Chat toEntity(){
        return Chat.of(Long.valueOf(chatRoomId)
        ,Long.valueOf(writerId)
        ,message);
    }
}