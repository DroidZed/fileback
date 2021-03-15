package com.wevioo.fileback.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "needs")
public class Needs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNeed")
    private Long id_need ;

    @Column(name="needTiltle")
    private String need_title ;

    @Column(name="nbJobber")
    private int nb_jobber ;

    @Column(name = "vehicleInfos")
    private String vehicle_infos ;

    @Column(name = "budget")
    private int budget;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user ;

    @ManyToOne(fetch = FetchType.EAGER,cascade =CascadeType.ALL)
    @JoinColumn(name = "id_cat")
    private Category category;


}
