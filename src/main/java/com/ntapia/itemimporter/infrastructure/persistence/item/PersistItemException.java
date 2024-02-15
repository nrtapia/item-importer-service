package com.ntapia.itemimporter.infrastructure.persistence.item;


public class PersistItemException extends RuntimeException {
    public PersistItemException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
