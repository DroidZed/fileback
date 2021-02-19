package com.wevioo.fileback.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name="categorie")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Categorie {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_cat")
    private Long id_cat;

    @Column(name="description")
    private String description;

    @Column(name="image")
    private byte[] image;

    public Categorie(String description, byte[] image) {
        this.description = description;
        this.image = image;
    }
}
