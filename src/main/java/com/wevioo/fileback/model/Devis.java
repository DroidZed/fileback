package com.wevioo.fileback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevioo.fileback.enums.EtatDevis;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "devis")
@Proxy(lazy = false)
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long devisId;

    private Integer prestation;

    @Transient
    private User needOwner;

    private String commentaire;

    @Enumerated(EnumType.STRING)
    private EtatDevis etat = EtatDevis.PUBLISHED;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "id_need")
    private Needs need;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "id_user")
    private User jobber;
}
