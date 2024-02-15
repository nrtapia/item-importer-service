package com.ntapia.itemimporter.application.item.importer;

public class ItemImporterConcurrentException extends RuntimeException {

    public ItemImporterConcurrentException(Throwable throwable) {
        super(throwable);
    }
}
