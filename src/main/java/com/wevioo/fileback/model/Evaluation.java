package com.wevioo.fileback.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idEval;

    private Integer evaluation;

    private String comment;
}
