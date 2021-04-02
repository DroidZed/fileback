package com.wevioo.fileback.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "devis")
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long devisId;

    private Integer prestation;

    private Long userId;

    private Long jobberId;

    private Long needId;

    private String commentaire;

    private LocalDate dateTravail;

    private Boolean etat = Boolean.FALSE;
}
