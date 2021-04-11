package com.wevioo.fileback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
@Proxy(lazy = false)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String comment;

    private Long senderId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "id_need")
    private Needs besoin;
}
