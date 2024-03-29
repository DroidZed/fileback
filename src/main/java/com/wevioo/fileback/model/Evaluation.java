package com.wevioo.fileback.model;

import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="evaluation")
@Proxy(lazy = false)
public class Evaluation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idEval;

    private Integer evaluation;

    private String comment;

    private Long evaluatorId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "id_user")
    private User evaluated;
}
