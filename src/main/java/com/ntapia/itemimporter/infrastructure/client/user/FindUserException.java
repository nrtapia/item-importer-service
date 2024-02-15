package com.ntapia.itemimporter.infrastructure.client.user;


public class FindUserException extends RuntimeException {
    public FindUserException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
