package com.wevioo.fileback.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "id_cat")
    private Category category;
}
