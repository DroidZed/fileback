package com.wevioo.fileback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="need_location")
public class NeedLocation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idLoc;

    @Column(name = "lng")
    private Float lng;

    @Column(name = "lat")
    private Float lat;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "needLocation")
    private Needs needs;

    public NeedLocation(Float lng, Float lat) {
        this.lng = lng;
        this.lat = lat;
    }
}
