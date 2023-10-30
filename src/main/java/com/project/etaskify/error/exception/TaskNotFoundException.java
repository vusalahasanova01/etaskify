package com.project.etaskify.error.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
