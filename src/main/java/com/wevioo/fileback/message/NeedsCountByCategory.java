package com.wevioo.fileback.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevisCountByCategory {

    private Integer count;
    private String nom;
}
