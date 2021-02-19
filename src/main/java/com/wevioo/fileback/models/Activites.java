package com.wevioo.fileback.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name="activites")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Activites {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_infos")
    private Long id_infos;

    @Column(name="profession")
    private String profession;

    @Column(name="description")
    private String description;

    public Activites(String profession,
                     String description) {
        this.profession = profession;
        this.description = description;
    }

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_infos", referencedColumnName = "id_user")
    private Utilisateur utilisateurs;
}
