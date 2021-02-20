package com.wevioo.fileback.model;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password_user")
    private String passwordUser;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name="adresse")
    private String adresse;

    @Column(name="travailleur")
    private Boolean travailleur;

    @Column(name = "tel")
    private String tel;

    @Column(name = "etat")
    private Boolean etat;

    @Column(name = "image")
    private byte[] pic;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_act")
    private Activity activity;
}
