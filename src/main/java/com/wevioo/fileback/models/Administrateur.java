package com.wevioo.fileback.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name="administrateur")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Administrateur {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_admin")
    private Long id_admin;

    @Column(name="username_admin")
    private String usernameAdmin;

    @Column(name="password_admin")
    private String passwordAdmin;

    public Administrateur(String usernameAdmin,
                          String passwordAdmin) {
        this.usernameAdmin = usernameAdmin;
        this.passwordAdmin = passwordAdmin;
    }
}
