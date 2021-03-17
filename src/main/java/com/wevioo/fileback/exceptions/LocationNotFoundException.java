package com.wevioo.fileback.exceptions;

public class LocationNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 423782342L;

    public LocationNotFoundException(Long id)
    {
        super("Location with id "+id+" could not be found !");
    }
    
}
