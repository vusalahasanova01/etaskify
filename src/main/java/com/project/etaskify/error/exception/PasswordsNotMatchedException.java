package com.project.etaskify.error.exception;

public class PasswordsNotMatchedException extends RuntimeException {

    public PasswordsNotMatchedException(String errorMessage) {
        super(errorMessage);
    }

}
