package com.wevioo.fileback.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String message;
}
