package com.wevioo.fileback.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sub_notification")
public class SubNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notif_id")
    private Long notifId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "read")
    private Boolean read = false;

    @Column(name = "id_jobber")
    private Long idJobber;

    @Column(name = "id_user")
    private Long idUser;
}
