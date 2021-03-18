package com.wevioo.fileback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevioo.fileback.enums.DaysOfWork;
import com.wevioo.fileback.enums.RenumerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_serv")
    private Long idServ;

    @Column(name="description")
    private String description;

    @Column(name="horaire")
    private String horaire;

    @Column(name="speciality")
    private String speciality;

    @Column(name="days_of_work")
    @Enumerated(EnumType.STRING)
    private DaysOfWork daysOfWork;

    @Column(name="renumeration")
    @Enumerated(EnumType.STRING)
    private RenumerationType renumeration;

    @Column(name="prix_heure")
    private Float prixHeure;

    @Column(name="prix_jour")
    private Float prixJour;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy ="services" )
    private User user;

    public Services(String description,
                    String horaire,
                    String speciality,
                    DaysOfWork daysOfWork,
                    RenumerationType renumeration,
                    Float prixHeure,
                    Float prixJour) {
        this.description = description;
        this.horaire = horaire;
        this.speciality = speciality;
        this.daysOfWork = daysOfWork;
        this.renumeration = renumeration;
        this.prixHeure = prixHeure;
        this.prixJour = prixJour;
    }
}
