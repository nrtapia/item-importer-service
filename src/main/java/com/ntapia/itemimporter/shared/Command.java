package com.ntapia.itemimporter.shared;

public interface Command<T> {

    void execute(T t);
}
