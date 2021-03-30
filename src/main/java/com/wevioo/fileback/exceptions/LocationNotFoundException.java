package com.wevioo.fileback.exceptions;

import java.io.Serial;

public class LocationNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 423782342L;

    public LocationNotFoundException(Long id)
    {
        super("Location with id "+id+" could not be found !");
    }
    
}
