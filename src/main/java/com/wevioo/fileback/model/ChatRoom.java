package com.wevioo.fileback.model;

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

    @Column(name = "user_1_id")
    private Long user1Id;

    @Column(name = "user_2_id")
    private Long user2Id;

    @OneToMany(fetch=FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH}, mappedBy = "chatRoom")
    List<ChatModel> chatMessages;

    public ChatRoom(Long user1Id, Long user2Id) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
    }
}
