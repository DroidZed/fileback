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
    @Column(name="id_act")
    private Long idAct;

    @Column(name="profession")
    private String profession;

    @Column(name="description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    mappedBy = "activites")
    private Utilisateur utilisateur;

    public Activites(String profession,
                     String description) {
        this.profession = profession;
        this.description = description;
    }


}
