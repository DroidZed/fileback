package com.wevioo.fileback.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private Boolean status;
    private Long id;
    private Boolean isAdmin;
    private String token;
}
