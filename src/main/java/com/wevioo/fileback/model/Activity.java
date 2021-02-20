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
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_act;

    @Column(name = "profession")
    private String profession;
    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy ="activity" )
    private User user;
}
