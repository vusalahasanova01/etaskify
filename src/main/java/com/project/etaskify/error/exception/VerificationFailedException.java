package com.project.etaskify.error.exception;

public class VerificationFailedException extends RuntimeException{

    public VerificationFailedException(String errorMessage) {
        super(errorMessage);
    }

}
