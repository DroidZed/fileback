package com.wevioo.fileback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "needs")
@Proxy(lazy = false)
public class Needs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_need")
    private Long idNeed;

    @Column(name="need_title")
    private String needTitle;

    @Column(name="need_description")
    private String needDescription;

    @Column(name="nb_jobber")
    private int nbJobber;

    @Column(name = "vehicle_infos")
    private String vehicleInfos;

    @Column(name = "budget")
    private int budget;

    @Column(name = "address")
    private String address;

    @Column(name = "etat_besoin")
    private Boolean etatBesoin;

    @Column(name = "image_a")
    private String imageA;

    @Column(name = "image_b")
    private String imageB;

    @Column(name = "image_c")
    private String imageC;

    @Column(name = "image_d")
    private String imageD;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "id_user")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "id_cat")
    private Category category;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_loc")
    private NeedLocation needLocation;

}
