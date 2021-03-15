package com.wevioo.fileback.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@Proxy(lazy = false)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user")
    private Long idUser;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password_user", length= 60)
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
    private Boolean etat = false;

    @Column(name = "image", length = 1000)
    private byte[] pic;

    @Column(name = "is_admin")
    private Boolean isAdmin = false;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_act")
    private Activity activity;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Needs> needs ;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_loc")
    private Locations location;
}
