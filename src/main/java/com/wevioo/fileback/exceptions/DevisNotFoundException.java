package com.wevioo.fileback.exceptions;

import java.io.Serial;

public class DevisNotFoundException extends RuntimeException {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 895388186389715620L;

    public DevisNotFoundException(Long id)
    {
        super("Devis num : "+id+" could not be found !");
    }
}
