package com.wevioo.fileback.exceptions;

public class DevisNotFoundException extends RuntimeException {

    public DevisNotFoundException(Long id)
    {
        super("Devis num : "+id+" could not be found !");
    }
}
