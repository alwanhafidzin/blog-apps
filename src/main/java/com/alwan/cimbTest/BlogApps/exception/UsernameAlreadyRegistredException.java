package com.alwan.cimbTest.BlogApps.exception;

public class UsernameAlreadyRegistredException  extends RuntimeException{
    public UsernameAlreadyRegistredException(String message) {
        super(message);
    }
}
