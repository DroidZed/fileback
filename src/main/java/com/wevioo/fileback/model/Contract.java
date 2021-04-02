package com.wevioo.fileback.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {

    private String contractId;

    private LocalDateTime createdAt;

    private Needs need;

    private Category category;

    private Devis devis;

    private User userDetails;

    private User jobberDetails;
}