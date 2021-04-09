package com.wevioo.fileback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "chat_room")
@AllArgsConstructor
@NoArgsConstructor
@Proxy(lazy = false)
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long chatRoomId;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "receiver_id")
    private Long receiverId;

    @OneToMany(mappedBy = "chatRoom")
    List<ChatModel> chatMessages;

    public ChatRoom(Long sender, Long receiver) {

        this.senderId = sender;
        this.receiverId = receiver;

    }
}
