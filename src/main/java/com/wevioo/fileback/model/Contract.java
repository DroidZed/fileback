package com.wevioo.fileback.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {

    private String contractId;

    private String createdAt;

    private Needs need;

    private Category category;

    private Devis devis;

    private User userDetails;

    private User jobberDetails;
}
