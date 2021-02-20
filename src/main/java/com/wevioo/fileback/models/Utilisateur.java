package com.wevioo.fileback.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name="utilisateur")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_user")
    private Long idUser;

    @Column(name="username")
    private String username;

    @Column(name="password_user")
    private String passwordUser;

    @Column(name="full_name")
    private String fullName;

    @Column(name="email")
    private String email;

    @Column(name="adresse")
    private String adresse;

    @Column(name="travailleur")
    private Boolean travailleur;

    @Column(name="tel")
    private String tel;

    @Column(name="etat")
    private Boolean etat;

    @Column(name="pic",length = 1000)
    private byte[] pic;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_act")
    private Activites activites;


    public Utilisateur(String username,
                       String passwordUser,
                       String fullName,
                       String email,
                       String adresse,
                       Boolean travailleur,
                       String tel,
                       Boolean etat,
                       byte[] pic,
                       Activites activites) {
        this.username = username;
        this.passwordUser = passwordUser;
        this.fullName = fullName;
        this.email = email;
        this.adresse = adresse;
        this.travailleur = travailleur;
        this.tel = tel;
        this.etat = etat;
        this.pic = pic;
    }
}
