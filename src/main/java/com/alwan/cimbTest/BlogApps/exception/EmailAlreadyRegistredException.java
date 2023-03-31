package com.alwan.cimbTest.BlogApps.exception;

public class EmailAlreadyRegistredException  extends RuntimeException{
    public EmailAlreadyRegistredException(String message) {
        super(message);
    }
}
