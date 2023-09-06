package com.blind.dating.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class ReadChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ChatRoom chatRoom;

    private Long userId;

    private Long chatId;

    private ReadChat(ChatRoom chatRoom, Long userId, Long chatId) {
        this.chatRoom = chatRoom;
        this.userId = userId;
        this.chatId = chatId;
    }

    public static ReadChat of(ChatRoom chatRoom, Long userId, Long chatId) {
        return new ReadChat(chatRoom, userId, chatId);
    }
}
