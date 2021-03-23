package com.wevioo.fileback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
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
