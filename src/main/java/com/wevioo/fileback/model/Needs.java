package com.wevioo.fileback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Getter
@Setter
@ToString
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "id_user")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "id_cat")
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Needs needs = (Needs) o;

        return idNeed != null && idNeed.equals(needs.idNeed);
    }

    @Override
    public int hashCode() {
        return 1283357623;
    }
}
