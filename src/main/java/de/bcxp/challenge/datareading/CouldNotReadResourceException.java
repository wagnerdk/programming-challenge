package de.bcxp.challenge.datareading;

public class CouldNotReadResourceException extends RuntimeException{
    public CouldNotReadResourceException(String errorMessage){
        super(errorMessage);
    }

}
