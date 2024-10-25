package com.example.apitrocatinesql.exception;

public class ErrorCreatingUser extends RuntimeException {
    public ErrorCreatingUser(String message) {
        super(message);
    }
}
