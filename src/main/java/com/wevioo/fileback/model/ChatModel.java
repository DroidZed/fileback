package com.wevioo.fileback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "chat_model")
@AllArgsConstructor
@NoArgsConstructor
@Proxy(lazy = false)
public class ChatModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "message")
    private String message;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;
}
